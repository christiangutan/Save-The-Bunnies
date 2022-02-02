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

public class DoubleOptionPopUpController{
	
	private OnActionData actionButton1;
	private OnActionData actionButton2;
	
	@FXML
	private AnchorPane pane;

	@FXML
	private Button button1;
	
	@FXML
	private Button button2;
	
	@FXML 
	private Text text;
	
	@FXML
	public void actionButton1(ActionEvent event) {
		Music.playSound(SoundType.BUTTON);
		this.getActionButton1().onAction();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void actionButton2(ActionEvent event) {
		Music.playSound(SoundType.BUTTON);
		this.getActionButton2().onAction();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void setText(String text) {
		this.text.setText(text);
	}
	public void setTextButton1(String text) {
		this.button1.setText(text);
	}
	public void setTextButton2(String text) {
		this.button2.setText(text);
	}
	public void setOnAction1 (OnActionData action1) {
		this.actionButton1 = action1;
	}
	public OnActionData getActionButton1() {
		return actionButton1;
	}
	public void setOnAction2 (OnActionData action2) {
		this.actionButton2 = action2;
	}
	public OnActionData getActionButton2() {
		return actionButton2;
	}
	
	public Stage getStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
}