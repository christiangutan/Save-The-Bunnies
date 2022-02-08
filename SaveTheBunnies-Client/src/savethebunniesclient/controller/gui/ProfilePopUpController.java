package savethebunniesclient.controller.gui;

import java.io.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.ProfilePopUpWindow;

/**
 * Controller of popUp where you can change de profile photo
 * @author christian_gutan	
 *
 */
public class ProfilePopUpController{
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private Circle circleImageProfile;
	
	@FXML
	private Button buttonLogOut;
	@FXML
	private Button buttonBack;
	
	@FXML
    public void initialize() {
		circleImageProfile.setStroke(Color.SEAGREEN);
        Image im = User.getImageProfile();
        circleImageProfile.setFill(new ImagePattern(im));
        circleImageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
	}

	@FXML
	public void actionChangeImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll( new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog(null);
		Music.playSound(SoundType.BUTTON);
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(file);
			data = in.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileName = file.getName();
		String fe = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    fe = fileName.substring(i+1);
		}
		
		ConnectionServer.sendNewProfileImage(User.getUsername(), data, fe);
		
		ConnectionServer.getImageProfile(User.getUsername());
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
		ProfilePopUpWindow window = new ProfilePopUpWindow();
		window.createView();
	}
	
	@FXML
	public void actionBack(ActionEvent event) {
		Music.playSound(SoundType.BUTTON);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		try {
			GuiApp.main.createView("Welcome.fxml", "css-Welcome.css");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
	
}