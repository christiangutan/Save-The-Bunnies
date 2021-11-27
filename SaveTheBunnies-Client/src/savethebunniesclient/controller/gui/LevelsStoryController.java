package savethebunniesclient.controller.gui;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.User;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.view.model.DoubleOptionPopUpWindow;
import savethebunniesclient.view.model.MenuStoryLevel;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    public void initialize() {
		menuStoryLevel1 = new MenuStoryLevel(paneChooseLevel1);
		menuStoryLevel2 = new MenuStoryLevel(paneChooseLevel2);
		menuStoryLevel3 = new MenuStoryLevel(paneChooseLevel3);
		menuStoryLevel4 = new MenuStoryLevel(paneChooseLevel4);
		menuStoryLevel5 = new MenuStoryLevel(paneChooseLevel5);
		menuStoryLevel6 = new MenuStoryLevel(paneChooseLevel6);
		menuStoryLevel7 = new MenuStoryLevel(paneChooseLevel7);
		menuStoryLevel8 = new MenuStoryLevel(paneChooseLevel8);
		menuStoryLevel9 = new MenuStoryLevel(paneChooseLevel9);
		
	    username.setText(User.getUsername());
	    name.setText(User.getName());
	    
    }
		
	@FXML
	public void actionButtonBack() {
		try{
			GuiApp.main.createView("Welcome.fxml","css-Welcome.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void actionButtonPower() {
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
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/christiangutan/Save-The-Bunnies"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}

