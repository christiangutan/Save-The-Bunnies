package savethebunniesclient.controller.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.User;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.InformationPopUpWindow;
import savethebunniesclient.util.OnActionData;

public class WelcomeController {
	@FXML
	private Button buttonStory;
	@FXML
	private Text username;
	@FXML
	private Text name;
	
	@FXML
    public void initialize() {
        username.setText(User.getUsername());
        name.setText(User.getName());
    }
	
	@FXML
	public void actionButtonStory() {		
		try{
			GuiApp.main.createView("LevelsStory.fxml","css-LevelsStory.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void actionButtonOnline() {
		try{
			GuiApp.main.createView("LevelsOnline.fxml","css-LevelsOnline.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void actionButtonMyLevels() {
		try{
			GuiApp.main.createView("MyLevels.fxml","css-MyLevels.css");
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