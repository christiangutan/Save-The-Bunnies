package serverPackage;


import java.io.Serializable;

public class DataPackageDeleteLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920394724571728658L;
	private boolean stateDeleteLevel;
	private String messageInfo;
	
	public DataPackageDeleteLevel( String messageInfo, boolean stateDeleteLevel) {
		setMessageInfo(messageInfo);
		setStateNewLevel(stateDeleteLevel);
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateNewLevel() {
		return stateDeleteLevel;
	}
	public void setStateNewLevel(boolean stateDeleteLevel) {
		this.stateDeleteLevel = stateDeleteLevel;
	}
	
}
