package serverPackage;


import java.io.Serializable;

public class DataPackageModifyLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920394724571728658L;
	private boolean stateModifiedLevel;
	private String messageInfo;
	
	public DataPackageModifyLevel( String messageInfo, boolean stateModifiedLevel) {
		setMessageInfo(messageInfo);
		setStateNewLevel(stateModifiedLevel);
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateNewLevel() {
		return stateModifiedLevel;
	}
	public void setStateNewLevel(boolean stateModifiedLevel) {
		this.stateModifiedLevel = stateModifiedLevel;
	}
	
}
