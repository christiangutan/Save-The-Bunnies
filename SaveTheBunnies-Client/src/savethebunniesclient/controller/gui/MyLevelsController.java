package savethebunniesclient.controller.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.game.Level;
import savethebunniesclient.model.game.LevelDifficulty;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.ErrorPopUpWindow;
import savethebunniesclient.model.view.InformationPopUpWindow;
import savethebunniesclient.util.CreateFile;
import savethebunniesclient.util.OnActionData;

/**
 * Controller of the view where you can modify, delete and create your own levels
 * @author christian_gutan
 *
 */
public class MyLevelsController {
	
	@FXML
	private Text username;
	@FXML
	private Text name;
	@FXML
	private TableView<Level> table;
	@FXML
	private TableColumn<Level, String> nameColumn;
	
	@FXML
	private Button buttonNewLevel;
	@FXML
	private Button buttonModifyLevel;
	@FXML
	private Button buttonDeleteLevel;
	
	@FXML
	private Circle circleImageProfile;
	
	@FXML
    public void initialize() {	
		GuiApp.setPlaying(false);

		
		buttonModifyLevel.setDisable(true);
		buttonDeleteLevel.setDisable(true);
		username.setText(User.getUsername().toUpperCase());
	    name.setText(User.getName().toUpperCase());
	    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
	    table.getItems().clear();
	    ObservableList<Level> levelData = table.getItems();
	    
	    ConnectionServer.getAvailableLevels(User.getUsername());
	    
	    String s = ConnectionServer.getMyLevels(User.getUsername());
	    
	    levelData.addAll(User.getMyLevels());
	    if(s.equals("No levels") || s.equals("")) {
	    	 
	    } else {
	    	 ErrorPopUpWindow window = new ErrorPopUpWindow("Internal Error. Restart the application");
	    	 window.setOnAction(new OnActionData() {
				@Override
				public void onAction() {
				}
	    	 });
	    	 window.createView();
	    }	  
	    
	    circleImageProfile.setStroke(Color.SEAGREEN);
        Image im = User.getImageProfile();
        circleImageProfile.setFill(new ImagePattern(im));
        circleImageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
   
	}	
	
	@FXML
	public void actionButtonPower() {
		Music.playSound(SoundType.BUTTON);
		DoubleOptionPopUpWindow window = new DoubleOptionPopUpWindow("ARE YOU SURE?");
		window.setTextButton1("YES");
		window.setTextButton2("NO");
		window.setOnAction1(new OnActionData() {
			@Override
			public void onAction() {
				System.exit(0);
			}
		});
		window.setOnAction2(new OnActionData() {
			@Override
			public void onAction() {
			}
		});
		window.createView();
	}
	
	@FXML
	public void actionLinkProjectSaveTheBunnies() {
		Music.playSound(SoundType.BUTTON);
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/christiangutan/Save-The-Bunnies"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionButtonConfiguration() {
		Music.playSound(SoundType.BUTTON);
		ConfigurationPopUpWindow window = new ConfigurationPopUpWindow();
		window.createView();
	}
	
	@FXML
	public void actionButtonInformation() {
		Music.playSound(SoundType.BUTTON);
		InformationPopUpWindow window = new InformationPopUpWindow();
		window.createView();
	}
	
	@FXML
	public void actionButtonBack() {
		Music.playSound(SoundType.BUTTON);
		try{
			GuiApp.main.createView("Welcome.fxml","css-Welcome.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@FXML
	public void newLevel() {
		Music.playSound(SoundType.BUTTON);
		
		Level.createNewFileOfLevel();
		InfoController.setCurrentLevelId(-1);
		InfoController.setTestedLevel(false);
		try{
			GuiApp.main.createView("CreateLevel.fxml","css-CreateLevel.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void deleteLevel() {
		if(!(table.getSelectionModel().getSelectedItem() == null)) {
			ConnectionServer.deleteLevel(User.getUsername(), table.getSelectionModel().getSelectedItem().getId());
			try{
				GuiApp.main.createView("MyLevels.fxml","css-MyLevels.css");
			}catch(IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	@FXML
	public void modifyLevel() {
		Level level = table.getSelectionModel().getSelectedItem(); 
		if(!( level == null)) {
			InfoController.setCurrentLevelId(level.getId());
			InfoController.setTesting(true);
			try{
				GuiApp.main.createView("CreateLevel.fxml","css-CreateLevel.css");
			}catch(IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	@FXML
	public void updateButtons() {
		Level level = table.getSelectionModel().getSelectedItem(); 
		if(level != null) {
			buttonNewLevel.setDisable(false);
			buttonModifyLevel.setDisable(false);
			buttonDeleteLevel.setDisable(false);
		} else {
			buttonNewLevel.setDisable(true);
			buttonModifyLevel.setDisable(true);
			buttonDeleteLevel.setDisable(true);
		}
	}
	
}