package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageModifyLevel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3856595208396818612L;
	private String username;
	private String level;
	private int idLevel;
	private String ip;
	
	public DataPackageModifyLevel(String username, String level, int idLevel) {
		setUsername(username);
		setLevel(level);
		setIdLevel(idLevel);
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
	public int getIdLevel() {
		return idLevel;
	}
	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	@Override
	public String toString() {
		return "DataPackageModifyLevel [username=" + username + ", ip=" + ip + "]";
	}

	
}
