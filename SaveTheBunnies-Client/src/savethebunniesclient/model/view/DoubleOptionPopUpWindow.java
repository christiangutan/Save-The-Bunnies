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
import savethebunniesclient.controller.gui.DoubleOptionPopUpController;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.util.Resources;

/**
 * PopUp window. You can set the actions of the two buttons with the class OnActionData.
 * @author christian_gutan
 *
 */
public class DoubleOptionPopUpWindow extends Application{
	private String text;
	private String textButton1;
	private String textButton2;
	private Stage stage;
	private OnActionData onAction1;
	private OnActionData onAction2;

	public DoubleOptionPopUpWindow(String text) {
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
	public String getTextButton1() {
		return textButton1;
	}
	public void setTextButton1(String textButton1) {
		this.textButton1 = textButton1;
	}
	public String getTextButton2() {
		return textButton2;
	}
	public void setTextButton2(String textButton2) {
		this.textButton2 = textButton2;
	}
	private Stage getStage() {
		return stage;
	}
	private void initStage() {
		this.stage = new Stage();
	}
	public void setOnAction1(OnActionData onAction1) {
		this.onAction1 = onAction1;
	}
	public void setOnAction2(OnActionData onAction2) {
		this.onAction2 = onAction2;
	}
	
	public void createView() {
		Platform.runLater(new Runnable() {  
			@Override
			public void run() {
				initStage();
				Region rootLayout;
				String view = "DoubleOptionPopUp.fxml";
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
			
				final DoubleOptionPopUpController controller = loader.getController();
				controller.setOnAction1(onAction1);	
				controller.setOnAction2(onAction2);
				controller.setText(text);
				controller.setTextButton1(textButton1);
				controller.setTextButton2(textButton2);
				
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