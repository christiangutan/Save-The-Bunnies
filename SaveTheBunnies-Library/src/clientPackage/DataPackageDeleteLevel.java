package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageDeleteLevel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3856595208396818612L;
	private String username;
	private int idLevel;
	private String ip;
	
	public DataPackageDeleteLevel(String username, int idLevel) {
		setUsername(username);
		setLevel(idLevel);
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
	public int getLevel() {
		return idLevel;
	}
	public void setLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	@Override
	public String toString() {
		return "DataPackageDeleteLevel [username=" + username + ", ip=" + ip + "]";
	}

	
}
