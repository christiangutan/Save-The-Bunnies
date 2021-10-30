package savethebunniesserver.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import savethebunniesserver.util.Resources;

public class GuiApp extends Application {

	private Region rootLayout;	
	private Stage stage;
	
	@FXML
	private Pane tankPane;
	
	public static GuiApp main;
	
	@Override
	public void start(Stage primaryStage) throws IOException {		
		main = this;
		stage = primaryStage;
		stage.setTitle("Save the Bunnies - Server");
		
		stage.setResizable(false);      
		createView("MainView.fxml", "");    	
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
        
               
        stage.setScene(scene);	    
        stage.show();    
	}	 
}