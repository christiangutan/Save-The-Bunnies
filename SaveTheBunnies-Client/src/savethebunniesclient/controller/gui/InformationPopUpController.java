package savethebunniesclient.controller.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;

public class InformationPopUpController{
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button buttonBack;

	
	@FXML
	public void actionBack(ActionEvent event) {
		Music.playSound(SoundType.BUTTON);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public Stage getStage() {
		return (Stage) this.pane.getScene().getWindow();
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
}