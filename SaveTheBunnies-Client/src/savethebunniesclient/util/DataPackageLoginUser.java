package savethebunniesclient.util;

import java.io.Serializable;

public class DataPackageLoginUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8463912710344224703L;
	private String username;
	private String password;
	
	public DataPackageLoginUser(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DataPackageLoginUser [username=" + username + ", password=" + password + "]";
	}
}
