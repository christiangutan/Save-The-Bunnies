package savethebunniesclient.model.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.gui.InfoPopUpController;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.util.Resources;

/**
 * PopUp Window. Shows info about sth like the error view
 * @author christian_gutan
 *
 */
public class InfoPopUpWindow extends Application{
	private String text;
	private Stage stage;
	private OnActionData onAction;
	
	public InfoPopUpWindow(String text) {
		setText(text);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
	}
		
	private String getText() {
		return text;
	}
	private void setText(String text) {
		this.text = text;
	}
	private Stage getStage() {
		return stage;
	}
	private void initStage() {
		this.stage = new Stage();
	}
	public void setOnAction(OnActionData onAction) {
		this.onAction = onAction;
	}
	
	public void createView() {
		Platform.runLater(new Runnable() {  
			@Override
			public void run() {
				initStage();
				Region rootLayout;
				String view = "InfoPopUp.fxml";
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
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				scene.getStylesheets().add(Resources.CSS + css);
				
				final InfoPopUpController controller = loader.getController();
				controller.setOnAction(onAction);	
				controller.setText(getText());
				
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
}