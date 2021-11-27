package savethebunniesclient.controller;

import java.io.File;
import java.io.FileNotFoundException;

import savethebunniesclient.model.Level;
import savethebunniesclient.model.LevelException;

public class InfoController {
	
	private static final String FILENAMESSTORYLEVELS = "src/savethebunniesclient/util/storylevels/";

	public static final int NUMLEVELSTORY = new File(FILENAMESSTORYLEVELS).list().length - 1;
	
	private static Level[] storyLevels = new Level[NUMLEVELSTORY];
	
	//private static String nameCurrentLevel; 
	private static int currentLevelId;
	
	//private static ustilizar un Tad con el que pueda buscar los niveles por su id y ., no es necesario orden
	
	public static void loadMainInformation() throws FileNotFoundException, IllegalArgumentException, LevelException {
		loadLevelsStory();
	}
	
	private static void loadLevelsStory() throws FileNotFoundException, IllegalArgumentException, LevelException {
		for(int i = 0; i < NUMLEVELSTORY; i++) {
			storyLevels[i] = new Level(FILENAMESSTORYLEVELS + "level" + (i+1) + ".txt");
		}
	}

	/*public static String getNameCurrentLevel() {
		return nameCurrentLevel;
	}
	public static void setNameCurrentLevel(String nameCurrentLevel) {
		InfoController.nameCurrentLevel = nameCurrentLevel;
	}*/
	public static int getCurrentLevelId() {
		return currentLevelId;
	}
	public static void setCurrentLevelId(int currentLevelId) {
		InfoController.currentLevelId = currentLevelId;
	}
	public static Level[] getStoryLevels() {
		return storyLevels;
	}
	public static int getNumlevelstory() {
		return NUMLEVELSTORY;
	}
	
	
	
}
