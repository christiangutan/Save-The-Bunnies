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
import savethebunniesclient.controller.gui.ErrorPopUpController;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.util.Resources;

/**
 * PopUp window. Error window: you can set the text of error
 * @author christian_gutan
 *
 */
public class ErrorPopUpWindow extends Application {
	private String text;
	private Stage stage;
	private OnActionData onAction;
	
	public ErrorPopUpWindow(String text) {
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
				String view = "ErrorPopUp.fxml";
				String css = "css-PopUpWindow.css";
				Scene scene = null;
				
				// Load root layout from fxml file.
				String urlString = "file:" +  System.getProperty("user.dir") + "\\src\\savethebunniesclient\\view\\" + view;
				URL url = null;
				try {
					url = new URL(urlString.replace("\\", "/"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				final FXMLLoader loader = new FXMLLoader();  
				loader.setLocation(url);	
				try {
					rootLayout = (Region) loader.load();
					scene = new Scene(rootLayout);
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				scene.getStylesheets().add(Resources.CSS + css);
				
				final ErrorPopUpController controller = loader.getController();
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
