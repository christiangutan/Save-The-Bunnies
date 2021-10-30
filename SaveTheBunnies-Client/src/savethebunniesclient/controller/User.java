package savethebunniesclient.controller;

import javafx.scene.image.Image;

public class User {
	
	private static String username;
	private static String name;
	private static String email;
	private static String password;
	private static int[] levelsBuilt;
	private static int[] levelsPassed; 
	private static int lastLevelStory;
	private static int idImageProfile;
	
	
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
	public static int[] getLevelsPassed() {
		return levelsPassed;
	}
	public static int getLastLevelStory() {
		return lastLevelStory;
	}
	public static void setLastLevelStory(int lastLevelStory) {
		User.lastLevelStory = lastLevelStory;
	}
	public static int getIdImageProfile() {
		return idImageProfile;
	}
	public static void setIdImageProfile(int idImageProfile) {
		User.idImageProfile = idImageProfile;
	}	
}
