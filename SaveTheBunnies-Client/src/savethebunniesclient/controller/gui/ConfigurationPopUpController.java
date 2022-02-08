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
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;

/**
 * Controller of PopUp where you can modify music and logout
 * @author christian_gutan
 *
 */
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
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		Music.playSound(SoundType.BUTTON);
		try{
			GuiApp.main.createView("Login.fxml","css-Login-Registration.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void actionBack(ActionEvent event) {
		Music.playSound(SoundType.BUTTON);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public JFXSlider getSliderMusic() {
		return sliderMusic;
	}
	
	public JFXSlider getSliderEffects() {
		return sliderEffects;
	}
	
	public ImageView getVolumeIconMusic() {
		return volumeIconMusic;
	}
	
	public ImageView getMuteIconMusic() {
		return muteIconMusic;
	}
	
	public ImageView getVolumeIconEffects() {
		return volumeIconEffects;
	}
	
	public ImageView getMuteIconEffects() {
		return muteIconEffects;
	}
	
	public Stage getStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
	
}