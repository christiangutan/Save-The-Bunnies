package savethebunniesclient.controller;

import java.io.File;
import java.io.FileNotFoundException;

import savethebunniesclient.model.game.Level;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.util.CreateFile;
import savethebunniesclient.util.Resources;

public class InfoController {

	public static final int NUMLEVELSTORY = new File(Resources.STORYLEVELS).list().length - 1;
	
	private static Level[] storyLevels = new Level[NUMLEVELSTORY];
	
	private static Level[] onlineLevels;
	
	private static Level[] myLevels;
	
	private static int currentLevelId;
	private static boolean testedLevel;	
	private static boolean testing;
	
	
	
	public static void loadMainInformation() throws FileNotFoundException, IllegalArgumentException, LevelException {
		loadLevelsStory();
	}
	private static void loadLevelsStory() throws FileNotFoundException, IllegalArgumentException, LevelException {
		for(int i = 0; i < NUMLEVELSTORY; i++) {
			storyLevels[i] = new Level(Resources.STORYLEVELS + "level" + (i+1) + ".txt");
		}
	}
	public static Level getCurrentLevel() throws FileNotFoundException, IllegalArgumentException, LevelException {
		//System.out.println((new Level(Resources.TEMPFILES + currentLevelId + ".txt")).toString());
		//System.out.println("Current Id: " + currentLevelId);
		return new Level(Resources.TEMPFILES + currentLevelId + ".txt");
	}
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
	public static boolean isTestedLevel() {
		return testedLevel;
	}
	public static void setTestedLevel(boolean bool) {
		testedLevel = bool;
	}
	public static void UpdateMyLevels() throws FileNotFoundException, IllegalArgumentException, LevelException {
		//int numLevels = new File(Resources.TEMPFILES).list().length - 1;
		File folder = new File(Resources.MYLEVELS);
		File[] files = folder.listFiles();
		myLevels = null;
		myLevels = new Level[files.length];
		for(int i = 0; i < files.length; i++) {
			String str = files[i].getName();
			int pos = str.lastIndexOf(".");
			String nameWithoutExtension = str.substring(0, pos);

			if(!nameWithoutExtension.equals("-1")) {
				myLevels[i] = new Level(Resources.MYLEVELS + nameWithoutExtension + ".txt");
				myLevels[i].setId(Integer.parseInt(nameWithoutExtension));
			}
		}
	}
	
	public static void UpdateOnlineLevels() throws FileNotFoundException, IllegalArgumentException, LevelException {
		//int numLevels = new File(Resources.TEMPFILES).list().length - 1;
		File folder = new File(Resources.TEMPFILES);
		File[] files = folder.listFiles();
		onlineLevels = null;
		onlineLevels = new Level[files.length];
		for(int i = 0; i < files.length; i++) {
			String str = files[i].getName();
			int pos = str.lastIndexOf(".");
			String nameWithoutExtension = str.substring(0, pos);

			if(!nameWithoutExtension.equals("-1")) {
				onlineLevels[i] = new Level(Resources.TEMPFILES + nameWithoutExtension + ".txt");
				onlineLevels[i].setId(Integer.parseInt(nameWithoutExtension));
			}
		}
	}
	public static Level[] getOnlineLevels() {
		return onlineLevels;
	}
	public static boolean isTesting() {
		return testing;
	}
	public static void setTesting(boolean testing) {
		InfoController.testing = testing;
	}
	
}
