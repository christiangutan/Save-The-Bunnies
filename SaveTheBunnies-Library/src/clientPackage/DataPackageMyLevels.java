package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageMyLevels implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2017024042904267708L;
	private String username;
	private String ip;
	
	public DataPackageMyLevels(String username) {
		setUsername(username);
		setIp();
	}
	
	public String getUsername() {
		return username;
	}	
	public void setUsername(String username) {
		this.username = username;
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
		return "DataPackageMyLevels [username=" + username + ", ip=" + ip + "]";
	}

	
}
