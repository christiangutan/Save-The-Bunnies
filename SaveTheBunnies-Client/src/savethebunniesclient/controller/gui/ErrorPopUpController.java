package savethebunniesclient.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.util.OnActionData;

/**
 * Controller of error view
 * @author christian_gutan
 *
 */
public class ErrorPopUpController {
	
	private OnActionData actionButton;

	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button buttonOkey;
	
	@FXML 
	private Text text;
	
	@FXML
	public void actionButtonOkey(ActionEvent event) {
		Music.playSound(SoundType.BUTTON);
		this.getActionButton().onAction();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void setText(String text) {
		this.text.setText(text);
	}
	public void setTextButtonOkey(String text) {
		this.buttonOkey.setText(text);
	}
	
	public void setOnAction (OnActionData action) {
		this.actionButton = action;
	}
	public OnActionData getActionButton() {
		return actionButton;
	}
	
	//sino funciona lo otro para cerrar utilizamos este m�todo stage.close();
	public Stage getStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
}