package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageUpdateLevels implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6215534634386905522L;
	private String username;
	private int lastLevel;
	private String ip;
	
	public DataPackageUpdateLevels(String username, int lastLevel) {
		setUsername(username);
		setLastLevel(lastLevel);
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
	public int getLastLevel() {
		return lastLevel;
	}
	public void setLastLevel(int lastLevel) {
		this.lastLevel = lastLevel;
	}

	@Override
	public String toString() {
		return "DataPackageUpdateLevels [username=" + username + ", " + "New Last Level = " + lastLevel + ", ip=" + ip + "]";
	}

	
}
