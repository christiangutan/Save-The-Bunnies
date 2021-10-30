package savethebunniesclient.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import savethebunniesclient.app.GuiApp;

public class WelcomeController {
	@FXML
	private Button buttonStory;
	
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
	public void actionButtonPower() {
		System.exit(0);
	}
}