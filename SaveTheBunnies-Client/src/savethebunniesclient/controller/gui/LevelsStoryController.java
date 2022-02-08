package savethebunniesclient.controller.gui;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.InformationPopUpWindow;
import savethebunniesclient.model.view.MenuStoryLevel;
import savethebunniesclient.util.OnActionData;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Controller of view where you can play the default levels
 * @author christian_gutan
 *
 */
public class LevelsStoryController {
	
	@FXML 
	private AnchorPane paneChooseLevel1;
	private MenuStoryLevel menuStoryLevel1; 
	
	@FXML 
	private AnchorPane paneChooseLevel2;
	private MenuStoryLevel menuStoryLevel2;
	
	@FXML 
	private AnchorPane paneChooseLevel3;
	private MenuStoryLevel menuStoryLevel3;
	
	@FXML 
	private AnchorPane paneChooseLevel4;
	private MenuStoryLevel menuStoryLevel4;
	
	@FXML 
	private AnchorPane paneChooseLevel5;
	private MenuStoryLevel menuStoryLevel5;
	
	@FXML 
	private AnchorPane paneChooseLevel6;
	private MenuStoryLevel menuStoryLevel6;
	
	@FXML 
	private AnchorPane paneChooseLevel7;
	private MenuStoryLevel menuStoryLevel7;
	
	@FXML 
	private AnchorPane paneChooseLevel8;
	private MenuStoryLevel menuStoryLevel8;
	
	@FXML 
	private AnchorPane paneChooseLevel9;
	private MenuStoryLevel menuStoryLevel9;
	
	@FXML
	private Text username;
	@FXML
	private Text name;
	
	@FXML
	private Circle circleImageProfile;
	
	@FXML
    public void initialize() {
		circleImageProfile.setStroke(Color.SEAGREEN);
        Image im = User.getImageProfile();
        circleImageProfile.setFill(new ImagePattern(im));
        circleImageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
		
		GuiApp.setPlaying(false);
		
		menuStoryLevel1 = new MenuStoryLevel(paneChooseLevel1);
		menuStoryLevel2 = new MenuStoryLevel(paneChooseLevel2);
		menuStoryLevel3 = new MenuStoryLevel(paneChooseLevel3);
		menuStoryLevel4 = new MenuStoryLevel(paneChooseLevel4);
		menuStoryLevel5 = new MenuStoryLevel(paneChooseLevel5);
		menuStoryLevel6 = new MenuStoryLevel(paneChooseLevel6);
		menuStoryLevel7 = new MenuStoryLevel(paneChooseLevel7);
		menuStoryLevel8 = new MenuStoryLevel(paneChooseLevel8);
		menuStoryLevel9 = new MenuStoryLevel(paneChooseLevel9);
		
	    username.setText(User.getUsername().toUpperCase());
	    name.setText(User.getName().toUpperCase());
	    
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
}

