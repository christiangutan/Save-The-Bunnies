package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageImageProfileUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8463912710344224703L;
	private String username;
	private String ip;
	
	public DataPackageImageProfileUser(String username) {
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
		return "DataPackageImageProfileUser [username=" + username + ", ip=" + ip + "]";
	}

	
}
