package savethebunniesclient.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
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
		createView("login.fxml", "css-Login-Registration.css");    	
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
        
        if(css!=null && css!="") scene.getStylesheets().add(Resources.CSS + css);
        
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon_bunny.png")));
        //stage.getIcons().add(new Image("file: Icon_bunny(1).png"));
               
        stage.setScene(scene);	    
        stage.show();    
	}	 
	
	public Stage getStage() {
		return stage;
	}
}