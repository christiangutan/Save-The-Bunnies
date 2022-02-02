package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageNewLevel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3856595208396818612L;
	private String username;
	private String level;
	private String ip;
	
	public DataPackageNewLevel(String username, String level) {
		setUsername(username);
		setLevel(level);
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "DataPackageNewLevel [username=" + username + ", ip=" + ip + "]";
	}

	
}
