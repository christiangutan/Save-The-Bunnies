package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageRegistrationUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4062376905890824628L;
	private String username;
	private String name;
	private String email;
	private String password;
	private String ip;

	public DataPackageRegistrationUser(String username, String name, String email, String password) {
		this.setUsername(username);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		try {
			this.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "DataPackageRegistrationUser [username=" + username + ", name=" + name + ", email=" + email
				+ ", password=" + password + "]";
	}	
}
