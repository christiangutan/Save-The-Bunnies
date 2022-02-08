package serverPackage;

import java.io.Serializable;

public class DataPackageImageProfileUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254273331253398699L;
	private byte[]  imageProfileUser;
	private String messageInfo;
	private boolean stateImageProfileUser;
	
	public DataPackageImageProfileUser(byte[] imageProfileUser, String messageInfo, boolean stateImgageProfileUser) {
		setImageProfileUser(imageProfileUser);
		setMessageInfo(messageInfo);
		setStateImgageProfileUser(stateImgageProfileUser);
	}
	public byte[] getImageProfileUser() {
		return imageProfileUser;
	}
	public void setImageProfileUser(byte[] imageProfileUser) {
		this.imageProfileUser = imageProfileUser;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateImgageProfileUser() {
		return stateImageProfileUser;
	}
	public void setStateImgageProfileUser(boolean stateImgageProfileUser) {
		this.stateImageProfileUser = stateImgageProfileUser;
	}
}
