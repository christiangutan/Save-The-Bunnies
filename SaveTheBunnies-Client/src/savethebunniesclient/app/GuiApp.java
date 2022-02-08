package savethebunniesclient.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

/**
 * Main class
 * @author christian_gutan
 *
 */
public class GuiApp extends Application {

	private Region rootLayout;	
	private Stage stage;
	private Image icon = new Image(/*Resources.IMG*/ "file:src/savethebunniesclient/util/img/"+ "Icon_bunny.png");
	private double x1;
    private double y1;
    private double x_stage;
    private double y_stage;
	public static boolean playing;

	@FXML
	private Pane tankPane;
	
	public static GuiApp main;
	
	@Override
	public void start(Stage primaryStage) throws IOException {		
		main = this;
		stage = primaryStage;
		stage.setTitle("Save the Bunnies - Login");
		stage.getIcons().add(icon);
		
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
		String urlString = "file:" +  System.getProperty("user.dir") + "\\src\\savethebunniesclient\\view\\" + view;
		URL url = new URL(urlString.replace("\\", "/"));
		loader.setLocation(url);
	    rootLayout = (Region) loader.load();  
	    
        // Show the scene containing the root layout.	    	    
        Scene scene = new Scene(rootLayout);  
        scene.setFill(Color.TRANSPARENT);
        if (!playing) {	        
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
        }
        
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
	public static void setPlaying(boolean isPlaying) {
		playing = isPlaying;
	}
	
	public static boolean isPlaying() {
		return playing;
	}
}