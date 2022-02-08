package savethebunniesclient.controller.gui;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.game.Level;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.InformationPopUpWindow;
import savethebunniesclient.model.view.MenuOnlineLevel;
import savethebunniesclient.util.OnActionData;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Controller of the view where you can play levels of others players
 * @author christian_gutan
 *
 */
public class LevelsOnlineController {
	
	@FXML 
	private AnchorPane paneChooseLevel1;
	private MenuOnlineLevel menuOnlineLevel1; 
	
	@FXML 
	private AnchorPane paneChooseLevel2;
	private MenuOnlineLevel menuOnlineLevel2;
	
	@FXML 
	private AnchorPane paneChooseLevel3;
	private MenuOnlineLevel menuOnlineLevel3;
	
	@FXML 
	private AnchorPane paneChooseLevel4;
	private MenuOnlineLevel menuOnlineLevel4;
	
	@FXML 
	private AnchorPane paneChooseLevel5;
	private MenuOnlineLevel menuOnlineLevel5;
	
	@FXML 
	private AnchorPane paneChooseLevel6;
	private MenuOnlineLevel menuOnlineLevel6;
	
	@FXML 
	private AnchorPane paneChooseLevel7;
	private MenuOnlineLevel menuOnlineLevel7;
	
	@FXML 
	private AnchorPane paneChooseLevel8;
	private MenuOnlineLevel menuOnlineLevel8;
	
	@FXML 
	private AnchorPane paneChooseLevel9;
	private MenuOnlineLevel menuOnlineLevel9;
	
	@FXML
	private Text username;
	@FXML
	private Text name;
	
	@FXML
	private ImageView buttonPreviousPage;
	@FXML
	private ImageView buttonNextPage;
	
	private int numLevels;
	private int countPages = 0;
	
	@FXML
	private Circle circleImageProfile;
	
	@FXML
    public void initialize() {
		circleImageProfile.setStroke(Color.SEAGREEN);
        Image im = User.getImageProfile();
        circleImageProfile.setFill(new ImagePattern(im));
        circleImageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
   
		
		InfoController.setTesting(false);
		GuiApp.setPlaying(false);
			
		menuOnlineLevel1 = new MenuOnlineLevel(paneChooseLevel1);
		menuOnlineLevel2 = new MenuOnlineLevel(paneChooseLevel2);
		menuOnlineLevel3 = new MenuOnlineLevel(paneChooseLevel3);
		menuOnlineLevel4 = new MenuOnlineLevel(paneChooseLevel4);
		menuOnlineLevel5 = new MenuOnlineLevel(paneChooseLevel5);
		menuOnlineLevel6 = new MenuOnlineLevel(paneChooseLevel6);
		menuOnlineLevel7 = new MenuOnlineLevel(paneChooseLevel7);
		menuOnlineLevel8 = new MenuOnlineLevel(paneChooseLevel8);
		menuOnlineLevel9 = new MenuOnlineLevel(paneChooseLevel9);
		
	    username.setText(User.getUsername().toUpperCase());
	    name.setText(User.getName().toUpperCase());
	    
	    ConnectionServer.getAvailableLevels(User.getUsername());
	    
	    numLevels = InfoController.getOnlineLevels().length;	
	    
	    updatePage();
	    
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
	public void actionButtonNextPage() {
		countPages++;
		updatePage();
	}
	
	@FXML
	public void actionButtonPreviousPage() {
		countPages--;
		updatePage();
	}
	
	private void updatePage() {
		if (0 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel1, InfoController.getOnlineLevels()[0 + countPages*9]);
			menuOnlineLevel1.setVisible(true);
		} else menuOnlineLevel1.setVisible(false);
		if (1 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel2, InfoController.getOnlineLevels()[1 + countPages*9]);
			menuOnlineLevel2.setVisible(true);
		} else menuOnlineLevel2.setVisible(false);
		if (2 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel3, InfoController.getOnlineLevels()[2 + countPages*9]);
			menuOnlineLevel3.setVisible(true);
		} else menuOnlineLevel3.setVisible(false);
		if (3 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel4, InfoController.getOnlineLevels()[3 + countPages*9]);
			menuOnlineLevel4.setVisible(true);
		} else menuOnlineLevel4.setVisible(false);
		if (4 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel5, InfoController.getOnlineLevels()[4 + countPages*9]);
			menuOnlineLevel5.setVisible(true);
		} else menuOnlineLevel5.setVisible(false);
		if (5 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel6, InfoController.getOnlineLevels()[5 + countPages*9]);
			menuOnlineLevel6.setVisible(true);
		} else menuOnlineLevel6.setVisible(false);
		if (6 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel7, InfoController.getOnlineLevels()[6 + countPages*9]);
			menuOnlineLevel7.setVisible(true);
		} else menuOnlineLevel7.setVisible(false);
		if (7 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel8, InfoController.getOnlineLevels()[7 + countPages*9]);
			menuOnlineLevel8.setVisible(true);
		} else menuOnlineLevel8.setVisible(false);
		if (8 + countPages*9 < numLevels) {
			updatePane(menuOnlineLevel9, InfoController.getOnlineLevels()[8 + countPages*9]);
			menuOnlineLevel9.setVisible(true);
		} else menuOnlineLevel9.setVisible(false);
		
		updateButtonsPreviousAndNextPage();
	}
	
	private void updatePane(MenuOnlineLevel menu, Level level) {
		menu.setDifficulty(level.getDifficulty());
		menu.setIdLevel(level.getId());
		menu.setNumBunnies(level.getNumTotalBunnies());
		menu.setNumFoxes(level.getNumTotalFoxes());
		menu.setUsername(level.getAutor());
		menu.setLevelName(level.getName());
	}
	
	private void updateButtonsPreviousAndNextPage() {
		if(countPages == 0 ) this.buttonPreviousPage.setVisible(false);
		else this.buttonPreviousPage.setVisible(true);
				
		if(((double)numLevels / (double)((countPages + 1) * 9)) > 1 ) buttonNextPage.setVisible(true); 
		else buttonNextPage.setVisible(false); 
	}
}

