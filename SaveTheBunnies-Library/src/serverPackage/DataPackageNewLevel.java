package serverPackage;


import java.io.Serializable;

public class DataPackageNewLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920394724571728658L;
	private boolean stateNewLevel;
	private String messageInfo;
	
	public DataPackageNewLevel( String messageInfo, boolean stateNewLevel) {
		setMessageInfo(messageInfo);
		setStateNewLevel(stateNewLevel);
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateNewLevel() {
		return stateNewLevel;
	}
	public void setStateNewLevel(boolean stateNewLevel) {
		this.stateNewLevel = stateNewLevel;
	}
	
}
