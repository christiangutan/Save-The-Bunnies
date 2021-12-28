package savethebunniesclient.model.view;

import java.io.IOException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.ToPlay;
import savethebunniesclient.controller.User;
import savethebunniesclient.model.game.LevelDifficulty;
import savethebunniesclient.util.OnActionData;

public class MenuOnlineLevel {
	private LevelDifficulty difficulty;	
	
	private Button buttonPlay;			
	private Label labelTextBunnies;		
	private Label labelTextFoxes;	
	private ImageView imageDifficulty1;	
	private ImageView imageDifficulty2;	
	private ImageView imageDifficulty3;	
	private Label labelNumBunnies;		
	private Label labelNumFoxes;		
	private Text labelUser;
	private Text labelLevel;	
	private Text labelId;
	
	
	public MenuOnlineLevel(Pane pane) {
		List<?> list = pane.getChildren();
		
		buttonPlay = ((Button) list.get(0));
		labelTextBunnies = (Label) list.get(1);
		labelTextFoxes = (Label) list.get(2);
		imageDifficulty1 = (ImageView) list.get(3);
		imageDifficulty2 = (ImageView) list.get(4);
		imageDifficulty3 = (ImageView) list.get(5);
		labelNumBunnies = (Label) list.get(6);
		labelNumFoxes = (Label) list.get(7);
		labelUser = (Text) list.get(8);
		labelLevel = (Text) list.get(9);
		labelId = (Text) list.get(10);
		
		//coger de los 9 niveles que ya se deberian haber cargado en el programa el nivel correspondiente
		//
		
		
		/*String textLabel = labelLevel.getText();
		labelNumBunnies.setText(String.valueOf(InfoController.getStoryLevels()[level-1].getNumTotalBunnies()));
		labelNumFoxes.setText(String.valueOf(InfoController.getStoryLevels()[level-1].getNumTotalFoxes()));	
		
		setDifficulty(InfoController.getStoryLevels()[level-1].getDifficulty());
			
		
		buttonPlay.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						play(level);
					}
			});
		
		*/
		
		buttonPlay.setId("button");
		pane.setId("pane");	
	}
	
	public LevelDifficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		if(difficulty == 1) {
			this.difficulty = LevelDifficulty.STARTER;
		} else if(difficulty == 2) {
			this.difficulty = LevelDifficulty.JUNIOR;
		} else if(difficulty == 3) {
			this.difficulty = LevelDifficulty.EXPERT;
		}
		setImagesDificulty();
	}
	public void setDifficulty(LevelDifficulty difficulty) {
		this.difficulty = difficulty;
		setImagesDificulty();
	}
	public Button getButtonPlay() {
		return buttonPlay;
	}
	public void setButtonPlay(Button buttonPlay) {
		this.buttonPlay = buttonPlay;
	}
	public ImageView getImageDifficulty1() {
		return imageDifficulty1;
	}
	public void setImageDifficulty1(ImageView imageDifficulty1) {
		this.imageDifficulty1 = imageDifficulty1;
	}
	public ImageView getImageDifficulty2() {
		return imageDifficulty2;
	}
	public void setImageDifficulty2(ImageView imageDifficulty2) {
		this.imageDifficulty2 = imageDifficulty2;
	}
	public ImageView getImageDifficulty3() {
		return imageDifficulty3;
	}
	public void setImageDifficulty3(ImageView imageDifficulty3) {
		this.imageDifficulty3 = imageDifficulty3;
	}

	private void setImagesDificulty() {
		if(difficulty == LevelDifficulty.STARTER) {
			imageDifficulty1.setVisible(true);
			imageDifficulty2.setVisible(false);
			imageDifficulty3.setVisible(false);
		} else if(difficulty == LevelDifficulty.JUNIOR) {
			imageDifficulty1.setVisible(true);
			imageDifficulty2.setVisible(true);
			imageDifficulty3.setVisible(false);
		} else if(difficulty ==  LevelDifficulty.EXPERT) {
			imageDifficulty1.setVisible(true);
			imageDifficulty2.setVisible(true);
			imageDifficulty3.setVisible(true);
		}
	}
	
	private void play(int level) {
		if (level <= User.getLastLevelPassedStory() + 1) {
			InfoController.setCurrentLevelId(level);
			try {
				GuiApp.main.createView("PlayLevel.fxml", "css-PlayLevel.css");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			InfoPopUpWindow window = new InfoPopUpWindow("Access denied");
			window.setOnAction(new OnActionData(){
				@Override
				public void onAction() {
				}
			});
			window.createView();
		}
	}
}
