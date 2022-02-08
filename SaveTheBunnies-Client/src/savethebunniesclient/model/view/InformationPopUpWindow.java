package savethebunniesclient.model.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.util.Resources;

/**
 * PopUp View. Shows information about the proyect
 * @author christian_gutan
 *
 */
public class InformationPopUpWindow extends Application{

	private Stage stage;

	public InformationPopUpWindow() {
		
		
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
	}

	private Stage getStage() {
		return stage;
	}
	private void initStage() {
		this.stage = new Stage();
	}
	
	public void createView() {
		Platform.runLater(new Runnable() {  
			@Override
			public void run() {
				initStage();
				Region rootLayout;
				String view = "InformationPopUp.fxml";
				String css = "css-PopUpWindow.css";
				Scene scene = null;
				
				// Load root layout from fxml file.
				final FXMLLoader loader = new FXMLLoader();  
				
				String urlString = "file:" +  System.getProperty("user.dir") + "\\src\\savethebunniesclient\\view\\" + view;
				URL url = null;
				try {
					url = new URL(urlString.replace("\\", "/"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				
				loader.setLocation(url);
				try {
					rootLayout = (Region) loader.load();
					scene = new Scene(rootLayout);
					scene.getStylesheets().add(Resources.CSS + css);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				getStage().initModality(Modality.APPLICATION_MODAL);
				getStage().initOwner(GuiApp.main.getStage());
				getStage().initStyle(StageStyle.UNDECORATED);
				scene.setFill(Color.TRANSPARENT);
				getStage().initStyle(StageStyle.TRANSPARENT);
				getStage().setScene(scene);	    
				getStage().show(); 
			   
			}
			
		});
	}
	
	@FXML
	public void actionLinkProjectSaveTheBunnies() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/christiangutan/Save-The-Bunnies"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}