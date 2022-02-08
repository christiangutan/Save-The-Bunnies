package savethebunniesclient.model.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.jfoenix.controls.JFXSlider;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.gui.ConfigurationPopUpController;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.util.Resources;

/**
 * PopUpWindow. Configuration of the application: music and logout
 * @author christian_gutan
 *
 */
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
				String view = "ConfigurationPopUp.fxml";
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
					scene.getStylesheets().add(Resources.CSS + css);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			
				final ConfigurationPopUpController controller = loader.getController();
				JFXSlider sliderMusic = controller.getSliderMusic();
				JFXSlider sliderEffects = controller.getSliderEffects();
				ImageView volumeIconMusic = controller.getVolumeIconMusic();
				ImageView muteIconMusic = controller.getMuteIconMusic();
				ImageView volumeIconEffects = controller.getVolumeIconEffects();
				ImageView muteIconEffects = controller.getMuteIconEffects();
				
				sliderMusic.setValue(Music.getVolumeMusicValue()*100);
				sliderEffects.setValue(Music.getVolumeEffectsValue()*100);
				
				sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
		            @Override
		            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		            	if(Music.getMediaPlayerMusic() != null) {
		            		Music.setVolumeMusic(sliderMusic.getValue()/100);
		            	}
		            	Music.setVolumeMusicValue(sliderMusic.getValue()/100);
		                if(sliderMusic.getValue() == 0) {
		                	muteIconMusic.setVisible(true);
		                	volumeIconMusic.setVisible(false);
		                } else {
		                	muteIconMusic.setVisible(false);
		                	volumeIconMusic.setVisible(true);
		                }
		            }
		        });
				
				sliderEffects.valueProperty().addListener(new ChangeListener<Number>() {
		            @Override
		            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		            	if(Music.getMediaPlayerEffects() != null) {
		            		Music.setVolumeEffects(sliderEffects.getValue()/100);
		            	}
		            	Music.setVolumeEffectsValue(sliderEffects.getValue()/100);
		                if(sliderEffects.getValue() == 0) {
		                	muteIconEffects.setVisible(true);
		                	volumeIconEffects.setVisible(false);
		                } else {
		                	muteIconEffects.setVisible(false);
		                	volumeIconEffects.setVisible(true);
		                }
		            }
		        });				
				
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