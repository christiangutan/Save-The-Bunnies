package savethebunniesclient.controller.gui;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.User;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    public void initialize() {
		menuOnlineLevel1 = new MenuOnlineLevel(paneChooseLevel1);
		menuOnlineLevel2 = new MenuOnlineLevel(paneChooseLevel2);
		menuOnlineLevel3 = new MenuOnlineLevel(paneChooseLevel3);
		menuOnlineLevel4 = new MenuOnlineLevel(paneChooseLevel4);
		menuOnlineLevel5 = new MenuOnlineLevel(paneChooseLevel5);
		menuOnlineLevel6 = new MenuOnlineLevel(paneChooseLevel6);
		menuOnlineLevel7 = new MenuOnlineLevel(paneChooseLevel7);
		menuOnlineLevel8 = new MenuOnlineLevel(paneChooseLevel8);
		menuOnlineLevel9 = new MenuOnlineLevel(paneChooseLevel9);
		
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
	@FXML
	public void actionButtonConfiguration() {
		ConfigurationPopUpWindow window = new ConfigurationPopUpWindow();
		window.createView();
	}
		
	@FXML
	public void actionButtonInformation() {
		InformationPopUpWindow window = new InformationPopUpWindow();
		window.createView();
	}
}

