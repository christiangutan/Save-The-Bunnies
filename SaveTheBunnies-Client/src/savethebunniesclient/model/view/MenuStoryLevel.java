package savethebunniesclient.model.view;

import java.io.IOException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.ToPlay;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.game.LevelDifficulty;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.util.OnActionData;

/**
 * Menu for being used in storyLevels view
 * @author christian_gutan
 *
 */
public class MenuStoryLevel {
	private int level;
	private LevelDifficulty difficulty;	
	
	private Button buttonPlay;			// 0
	private Label labelLevel;			// 1
	private Label labelTextBunnies;		// 2
	private Label labelTextFoxes;		// 3
	private ImageView imageDifficulty1;	// 4
	private ImageView imageDifficulty2;	// 5
	private ImageView imageDifficulty3;	// 6
	private Label labelNumBunnies;		// 7
	private Label labelNumFoxes;		// 8
	private ImageView imageLock;		// 9	
	
	public MenuStoryLevel(Pane pane) {
		List<?> list = pane.getChildren();
		
		buttonPlay = ((Button) list.get(0));
		labelLevel = (Label) list.get(1);
		labelTextBunnies = (Label) list.get(2);
		labelTextFoxes = (Label) list.get(3);
		imageDifficulty1 = (ImageView) list.get(4);
		imageDifficulty2 = (ImageView) list.get(5);
		imageDifficulty3 = (ImageView) list.get(6);
		labelNumBunnies = (Label) list.get(7);
		labelNumFoxes = (Label) list.get(8);
		imageLock = (ImageView) list.get(9);
		
		String textLabel = labelLevel.getText();

		level = Character.getNumericValue((textLabel.charAt(textLabel.length()-1)));
		labelNumBunnies.setText(String.valueOf(InfoController.getStoryLevels()[level-1].getNumTotalBunnies()));
		labelNumFoxes.setText(String.valueOf(InfoController.getStoryLevels()[level-1].getNumTotalFoxes()));	
		
		setDifficulty(InfoController.getStoryLevels()[level-1].getDifficulty());
		
		if(level <= User.getLastLevelPassedStory() + 1) {
			imageLock.setVisible(false);
		} else {
			imageLock.setVisible(true);
		}
		
		buttonPlay.setId("button");
		pane.setId("pane");		
		
		
		
		buttonPlay.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						Music.playSound(SoundType.BUTTON);
						play(level);
					}
			});
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	
	public ImageView getImageLock() {
		return imageLock;
	}

	public void setImageLock(ImageView imageLock) {
		this.imageLock = imageLock;
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
				Music.playSound(SoundType.MUSICGAME);
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
