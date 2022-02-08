package savethebunniesserver.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savethebunniesserver.util.Resources;

/**
 * Main class server
 * @author christian_gutan
 *
 */
public class GuiApp extends Application {

	private Region rootLayout;	
	private Stage stage;
	private Image icon = new Image(Resources.IMG + "Icon_bunny.png");
	private double x1;
    private double y1;
    private double x_stage;
    private double y_stage;
	
	@FXML
	private Pane tankPane;
	
	public static GuiApp main;
	
	@Override
	public void start(Stage primaryStage) throws IOException {		
		main = this;
		stage = primaryStage;
		stage.setTitle("Save the Bunnies - Server");
		stage.getIcons().add(icon);
		
		stage.setResizable(false);      
		getStage().initStyle(StageStyle.UNDECORATED);
		getStage().initStyle(StageStyle.TRANSPARENT);
		createView("MainView.fxml", "css-MainView.css");    	
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
        
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
        	@Override 
        	public void handle(MouseEvent m) {  
        		stage.setX(x_stage + m.getScreenX() - x1);
        		stage.setY(y_stage + m.getScreenY() - y1);
            }                                                
        });
        scene.setOnDragEntered(null);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent m) {    
            	x1 =m.getScreenX();
           	  	y1 =m.getScreenY();
              	x_stage = stage.getX();
            	y_stage = stage.getY();
            }                
        });    

        if(css!=null && css!="") scene.getStylesheets().add(Resources.CSS + css);
        
               
        stage.setScene(scene);	    
        stage.show();    
	}	
	
	public Stage getStage() {
		return stage;
	}
}