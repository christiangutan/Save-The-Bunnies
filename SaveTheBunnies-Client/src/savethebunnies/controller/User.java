package savethebunnies.controller;

import javafx.scene.image.Image;

public class User {
	
	private static String username;
	private static String name;
	private static String email;
	private static String [][] levels;
	private static int lastLevelStory;
	private static Image imgUser;
	
	
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
	public static String[][] getLevels() {
		return levels;
	}
	public static void setLevels(String[][] levels) {
		User.levels = levels;
	}
	public static int getLastLevelStory() {
		return lastLevelStory;
	}
	public static void setLastLevelStory(int lastLevelStory) {
		User.lastLevelStory = lastLevelStory;
	}
	public static Image getImgUser() {
		return imgUser;
	}
	public static void setImgUser(Image imgUser) {
		User.imgUser = imgUser;
	}	
}
