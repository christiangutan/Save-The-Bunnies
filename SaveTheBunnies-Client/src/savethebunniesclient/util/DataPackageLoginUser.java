package savethebunniesclient.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageLoginUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8463912710344224703L;
	private String username;
	private String password;
	private String ip;
	
	public DataPackageLoginUser(String username, String password) {
		setUsername(username);
		setPassword(password);
		setIp();
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
	public void setIp() {
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public String getIp() {
		return ip;
	}
	
	@Override
	public String toString() {
		return "DataPackageLoginUser [username=" + username + ", password=" + password + ", ip=" + ip + "]";
	}

	
}
