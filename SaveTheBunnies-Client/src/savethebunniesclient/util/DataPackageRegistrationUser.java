package savethebunniesclient.util;

import java.io.Serializable;

public class DataPackageRegistrationUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6021308765090642836L;
	private String username;
	private String name;
	private String email;
	private String password;

	public DataPackageRegistrationUser(String username, String name, String email, String password) {
		this.setUsername(username);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
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

	@Override
	public String toString() {
		return "DataPackageRegistrationUser [username=" + username + ", name=" + name + ", email=" + email
				+ ", password=" + password + "]";
	}	
}
