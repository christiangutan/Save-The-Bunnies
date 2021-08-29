package savethebunniesclient.util;

import javafx.scene.image.Image;
import savethebunniesclient.model.Level;

public class DataPackageLoggedUser {
	
	private String username;
	private String name;
	private String email;
	private String password;
	private Image imageProfile;
	private Level[] levelsBuilt;
	private String[] levelsPassed;
	private int lastLevelStory;
	
	public DataPackageLoggedUser(String username, String name, String email, String password, Image imageProfile,
			Level[] levelsBuilt, String[] levelsPassed, int lastLevelStory) {
		setUsername(username);
		setName(name);
		setEmail(email);
		setPassword(password);
		setImageProfile(imageProfile);
		setLevelsBuilt(levelsBuilt);
		setLevelsPassed(levelsPassed);
		setLastLevelStory(lastLevelStory);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Image getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(Image imageProfile) {
		this.imageProfile = imageProfile;
	}
	public Level[] getLevelsBuilt() {
		return levelsBuilt;
	}
	public void setLevelsBuilt(Level[] levelsBuilt) {
		this.levelsBuilt = levelsBuilt;
	}
	public String[] getLevelsPassed() {
		return levelsPassed;
	}
	public void setLevelsPassed(String[] levelsPassed) {
		this.levelsPassed = levelsPassed;
	}
	public int getLastLevelStory() {
		return lastLevelStory;
	}
	public void setLastLevelStory(int lastLevelStory) {
		this.lastLevelStory = lastLevelStory;
	}
	
	
	
}
