package serverPackage;


import java.io.Serializable;

public class DataPackageSendNewProfileImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255197655551166060L;
	private boolean stateNewProfileImage;
	private String messageInfo;
	
	public DataPackageSendNewProfileImage( String messageInfo, boolean stateNewProfileImage) {
		setMessageInfo(messageInfo);
		setStateNewLevel(stateNewProfileImage);
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateNewProfileImage() {
		return stateNewProfileImage;
	}
	public void setStateNewLevel(boolean stateNewLevel) {
		this.stateNewProfileImage = stateNewLevel;
	}
	
}
