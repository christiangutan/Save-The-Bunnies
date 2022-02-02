package savethebunniesclient.app;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.model.view.ErrorPopUpWindow;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.util.Resources;

public class GuiApp extends Application {

	private Region rootLayout;	
	private Stage stage;
	private Image icon = new Image("File: Icon_bunny.png");
	
	@FXML
	private Pane tankPane;
	
	public static GuiApp main;
	
	@Override
	public void start(Stage primaryStage) throws IOException {		
		main = this;
		stage = primaryStage;
		stage.setTitle("Save the Bunnies - Login");
		
		stage.setResizable(false);      
		getStage().initStyle(StageStyle.UNDECORATED);
		getStage().initStyle(StageStyle.TRANSPARENT);
		createView("login.fxml", "css-Login-Registration.css"); 
		try {
			InfoController.loadMainInformation();
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {
			ErrorPopUpWindow popUp = new ErrorPopUpWindow("ERROR - CONFIGURATION");
			popUp.setOnAction(new OnActionData(){
				@Override
				public void onAction() {
					System.exit(1);
				}
			});
			popUp.createView();
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void createView(String view, String css) throws IOException {
		// Load root layout from fxml file.
		FXMLLoader loader = new FXMLLoader();         
		loader.setLocation(this.getClass().getResource(Resources.FXML + view));	    
	    rootLayout = (Region) loader.load();  
	    
        // Show the scene containing the root layout.	    	    
        Scene scene = new Scene(rootLayout);  
        scene.setFill(Color.TRANSPARENT);
        
        if(css!=null && css!="") scene.getStylesheets().add(Resources.CSS + css);
               
        stage.setScene(scene);	
        stage.show();    
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}	 
	
	public Stage getStage() {
		return stage;
	}
}