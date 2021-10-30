package savethebunniesclient.view;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import savethebunniesclient.model.LevelDifficulty;

public class MenuStoryLevel {
	private int level;
	private LevelDifficulty difficulty;	
	private Button button;
	private int numBunnies;
	private int numFoxes;
	private ImageView imageDifficulty1;
	private ImageView imageDifficulty2;
	private ImageView imageDifficulty3;
	private ImageView imageLock;
	
	public MenuStoryLevel(Pane pane) {
		List<?> list = pane.getChildren();
		button = (Button) list.get(0);
		String textLabel = ((Label) list.get(1)).getText();
		level = Character.getNumericValue((textLabel.charAt(textLabel.length()-1)));
		imageDifficulty1 = (ImageView) list.get(4);
		imageDifficulty2 = (ImageView) list.get(5);
		imageDifficulty3 = (ImageView) list.get(6);
		numBunnies = Integer.parseInt(((Label) list.get(7)).getText());
		numFoxes = Integer.parseInt(((Label) list.get(8)).getText());
		imageLock = (ImageView) list.get(9);
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
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public int getNumBunnies() {
		return numBunnies;
	}
	public void setNumBunnies(int numBunnies) {
		this.numBunnies = numBunnies;
	}
	public int getNumFoxes() {
		return numFoxes;
	}
	public void setNumFoxes(int numFoxes) {
		this.numFoxes = numFoxes;
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
}
