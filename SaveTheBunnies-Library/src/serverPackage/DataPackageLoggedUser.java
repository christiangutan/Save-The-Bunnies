package serverPackage;


import java.io.Serializable;

public class DataPackageLoggedUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820141681329021383L;
	private String username;
	private String name;
	private String password;
	private String email;
	private int idImageProfile;
	private int[] levelsBuilt;
	private int lastLevelPassedStory;
	private java.util.Date dateRegistration;
	private String messageInfo;
	private boolean stateLogin;
	
	public DataPackageLoggedUser(String username, String name, String password, String email, int lastLevelPassedStory,
			int[] levelsBuilt, int idImageProfile, java.util.Date dateRegistration, String messageInfo, boolean stateLogin) {
		setUsername(username);
		setName(name);
		setPassword(password);
		setEmail(email);
		setLastLevelPassedStory(lastLevelPassedStory);
		setLevelsBuilt(levelsBuilt);
		setIdImageProfile(idImageProfile);	
		setDateRegistration(dateRegistration);
		setMessageInfo(messageInfo);
		setStateLogin(stateLogin);
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
	public int getIdImageProfile() {
		return idImageProfile;
	}
	public void setIdImageProfile(int idImageProfile) {
		this.idImageProfile = idImageProfile;
	}
	public int[] getLevelsBuilt() {
		return levelsBuilt;
	}
	public void setLevelsBuilt(int[] levelsBuilt) {
		this.levelsBuilt = levelsBuilt;
	}
	public int getLastLevelPassedStory() {
		return lastLevelPassedStory;
	}
	public void setLastLevelPassedStory(int lastLevelPassedStory) {
		this.lastLevelPassedStory = lastLevelPassedStory;
	}
	public java.util.Date getDateRegistration() {
		return dateRegistration;
	}
	public void setDateRegistration(java.util.Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateLogin() {
		return stateLogin;
	}
	public void setStateLogin(boolean stateLogin) {
		this.stateLogin = stateLogin;
	}
}
