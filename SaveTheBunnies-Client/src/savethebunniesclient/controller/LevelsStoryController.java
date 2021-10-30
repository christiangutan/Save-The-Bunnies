package savethebunniesclient.controller;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.model.Coordinate;
import savethebunniesclient.model.Grass;
import savethebunniesclient.model.Hole;
import savethebunniesclient.model.LevelDifficulty;
import savethebunniesclient.model.LevelException;
import savethebunniesclient.model.Movable;
import savethebunniesclient.model.Move;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.view.InfoPopUpWindow;
import savethebunniesclient.view.MenuStoryLevel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class LevelsStoryController {
	
	@FXML 
	private AnchorPane paneChooseLevel1;
	private MenuStoryLevel menuStoryLevel1 = new MenuStoryLevel(paneChooseLevel1);
	
	@FXML
	private Button buttonPlay1;
	@FXML
	public void actionButtonPlay1() {
		play(1);
	}
	
	@FXML 
	private AnchorPane paneChooseLevel2;
	private MenuStoryLevel menuStoryLevel2 = new MenuStoryLevel(paneChooseLevel2);
	
	@FXML 
	private AnchorPane paneChooseLevel3;
	private MenuStoryLevel menuStoryLevel3 = new MenuStoryLevel(paneChooseLevel3);
	
	@FXML 
	private AnchorPane paneChooseLevel4;
	private MenuStoryLevel menuStoryLevel4 = new MenuStoryLevel(paneChooseLevel4);
	
	@FXML 
	private AnchorPane paneChooseLevel5;
	private MenuStoryLevel menuStoryLevel5 = new MenuStoryLevel(paneChooseLevel5);
	
	@FXML 
	private AnchorPane paneChooseLevel6;
	private MenuStoryLevel menuStoryLevel6 = new MenuStoryLevel(paneChooseLevel6);
	
	@FXML 
	private AnchorPane paneChooseLevel7;
	private MenuStoryLevel menuStoryLevel7 = new MenuStoryLevel(paneChooseLevel7);
	
	@FXML 
	private AnchorPane paneChooseLevel8;
	private MenuStoryLevel menuStoryLevel8 = new MenuStoryLevel(paneChooseLevel8);
	
	@FXML 
	private AnchorPane paneChooseLevel9;
	private MenuStoryLevel menuStoryLevel9 = new MenuStoryLevel(paneChooseLevel9);
	
	@FXML
    public void initialize() {
		menuStoryLevel1.setDifficulty(LevelDifficulty.STARTER);
		menuStoryLevel2.setDifficulty(LevelDifficulty.STARTER);
		menuStoryLevel3.setDifficulty(LevelDifficulty.STARTER);
		menuStoryLevel4.setDifficulty(LevelDifficulty.JUNIOR);
		menuStoryLevel5.setDifficulty(LevelDifficulty.JUNIOR);
		menuStoryLevel6.setDifficulty(LevelDifficulty.JUNIOR);
		menuStoryLevel7.setDifficulty(LevelDifficulty.EXPERT);
		menuStoryLevel8.setDifficulty(LevelDifficulty.EXPERT);
		menuStoryLevel9.setDifficulty(LevelDifficulty.EXPERT);
    }
		
	@FXML
	public void actionButtonBack() {
		try{
			GuiApp.main.createView("Welcome.fxml","css-Welcome.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void actionButtonPower() {
		System.exit(0);
	}
	
	private void play(int i) {
		if (i <= User.getLastLevelPassedStory() + 1) {
			System.out.println("Ejecutando nivel " + i);
		} else {
			InfoPopUpWindow window = new InfoPopUpWindow("Access denied");
			window.createView();
		}
	}
}

