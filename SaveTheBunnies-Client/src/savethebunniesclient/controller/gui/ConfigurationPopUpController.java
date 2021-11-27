package savethebunniesclient.controller.gui;

import java.io.IOException;

import com.jfoenix.controls.JFXSlider;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import savethebunniesclient.app.GuiApp;

public class ConfigurationPopUpController{
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private JFXSlider sliderMusic;
	@FXML
	private JFXSlider sliderEffects;
	@FXML
	private ImageView volumeIconMusic;
	@FXML
	private ImageView volumeIconEffects;
	@FXML
	private ImageView muteIconMusic;
	@FXML
	private ImageView muteIconEffects;
	@FXML
	private Button buttonLogOut;
	@FXML
	private Button buttonBack;

	@FXML
	public void actionLogOut(ActionEvent event) {
		try{
			GuiApp.main.createView("Welcome.fxml","css-Welcome.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		} 
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
	}
	
	@FXML
	public void actionBack(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public Stage getStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
	
}