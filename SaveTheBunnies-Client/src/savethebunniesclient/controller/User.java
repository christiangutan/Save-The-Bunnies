package savethebunniesclient.controller;

import java.io.File;
import java.io.FileNotFoundException;

import savethebunniesclient.model.game.Level;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.util.Resources;

public class User {
	
	private static String username;
	private static String name;
	private static String email;
	private static String password;
	private static int[] levelsBuilt;
	private static int lastLevelPassedStory;
	private static int idImageProfile;
	
	private static Level[] levels;
	
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		User.username = username;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		User.name = name;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		User.email = email;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password){
		User.password = password;
	}
	public static int[] getLevelsBuilt() {
		return levelsBuilt;
	}
	public static void setLevelsBuilt(int[] levelsBuilt) {
		User.levelsBuilt = levelsBuilt;
	}
	public static int getLastLevelPassedStory() {
		return lastLevelPassedStory;
	}
	public static void setLastLevelPassedStory(int lastLevelPassedStory) {
		User.lastLevelPassedStory = lastLevelPassedStory;
	}
	public static int getIdImageProfile() {
		return idImageProfile;
	}
	public static void setIdImageProfile(int idImageProfile) {
		User.idImageProfile = idImageProfile;
	}	
	public static Level[] getMyLevels() {
		int numLevels = new File(Resources.MYLEVELS).list().length/* - 1*/;
		levels = null;
		levels = new Level[numLevels];
		String nameLevels[] = new File(Resources.MYLEVELS).list();
		
		for(int i = 0; i < numLevels; i++) {
			String nameWithoutExtension = nameLevels[i].substring(0, nameLevels[i].lastIndexOf('.')); 
			try {
				levels[i] = new Level(Resources.MYLEVELS + nameLevels[i]);
			} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {
				e.printStackTrace();
				return null;
			}
			levels[i].setId(Integer.parseInt(nameWithoutExtension));
		}
		return levels;
	}
}
