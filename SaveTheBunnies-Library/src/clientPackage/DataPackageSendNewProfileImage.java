package clientPackage;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataPackageSendNewProfileImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3396162625747112220L;
	private String username;
	private byte[] image;
	private String type;
	private String ip;
	
	public DataPackageSendNewProfileImage(String username, byte[] image, String type) {
		setUsername(username);
		setImage(image);
		setType(type);
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
	public  byte[] getImage() {
		return image;
	}
	public void setImage( byte[] image) {
		this.image = image;
	}	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DataPackageSendProfileImage [username=" + username + ", ip=" + ip + "]";
	}

	
}
