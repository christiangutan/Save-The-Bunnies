package savethebunniesserver.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Log {
	private static TextFlow textFlow;
	
	public static void setTextFlow(TextFlow textFlow) {
		Log.textFlow = textFlow;
		Log.addInfoCorrect("Correct");
	}

	public static void startServices() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		Font font = Font.font("Ubuntu Mono", FontPosture.REGULAR, 18);
		Text finaltext = new Text( "-------------------------------------------------------------\n" + "[" + dtf.format(LocalDateTime.now()) + "] --> " + "Starting services" + "\n-------------------------------------------------------------\n");
		finaltext.setFont(font);
		finaltext.setFill(Color.SILVER);
		
		Platform.runLater(new Runnable() {                          
            @Override
            public void run() {
            	//System.out.println("Esto se está ejecutando");
            	textFlow.getChildren().add(finaltext);
            }
        });
				
	}

	public static void stopServices() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		Font font = Font.font("Ubuntu Mono", FontPosture.REGULAR, 18);
		Text finaltext = new Text( "-------------------------------------------------------------\n" 
		+ "[" + dtf.format(LocalDateTime.now()) + "] --> " + "Stopping services" 
		+ "\n-------------------------------------------------------------\n");
		finaltext.setFont(font);
		finaltext.setFill(Color.SILVER);
		
		textFlow.getChildren().add(finaltext);
	}
	
	public static void addInfoActivityPanel(String text, Color color) {
		Platform.runLater(() -> {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			
			Font font = Font.font("Ubuntu Mono", FontPosture.REGULAR, 14);
			Text finaltext = new Text( "[" + dtf.format(LocalDateTime.now()) + "] --> " + text + "\n");
			finaltext.setFont(font);
			finaltext.setFill(color);
		
			textFlow.getChildren().add(finaltext);
		});	
	}
	
	public static void addInfoError(String text) {
		Log.addInfoActivityPanel(text, Color.RED);
	}
	
	public static void addInfoCorrect(String text) {
		Log.addInfoActivityPanel(text, Color.GREEN);
	}
	
}
