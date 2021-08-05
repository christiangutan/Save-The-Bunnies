package savethebunniesclient.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import savethebunniesclient.app.GuiApp;

public class GameOverController {

	@FXML
	private Button buttonStart;
	
	@FXML
	public void back() {		
		try{
			GuiApp.main.createView("Welcome.fxml","");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void exit() {		
		System.exit(0);		
	}	
}