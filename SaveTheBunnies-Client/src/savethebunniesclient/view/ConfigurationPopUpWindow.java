package savethebunniesclient.view;

import java.io.IOException;

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
import savethebunniesclient.controller.gui.ConfigurationPopUpController;
import savethebunniesclient.controller.gui.DoubleOptionPopUpController;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.util.Resources;

public class ConfigurationPopUpWindow extends Application{

	private Stage stage;

	public ConfigurationPopUpWindow() {
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
				String view = "DoubleOptionPopUp.fxml";
				String css = "css-PopUpWindow.css";
				Scene scene = null;
				
				// Load root layout from fxml file.
				final FXMLLoader loader = new FXMLLoader();  
				loader.setLocation(this.getClass().getResource(Resources.FXML + view));	
				try {
					rootLayout = (Region) loader.load();
					scene = new Scene(rootLayout);
					scene.getStylesheets().add(Resources.CSS + css);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			
				//final ConfigurationPopUpController controller = loader.getController();
				
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