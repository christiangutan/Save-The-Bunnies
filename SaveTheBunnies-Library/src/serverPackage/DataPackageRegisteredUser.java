package serverPackage;


import java.io.Serializable;

public class DataPackageRegisteredUser implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3649717717351370477L;
	private String messageInfo;
	private boolean stateRegistration;
	
	public DataPackageRegisteredUser(String messageInfo, boolean stateRegistration) {
		this.messageInfo = messageInfo;
		this.stateRegistration = stateRegistration;
	}

	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public boolean isStateRegistration() {
		return stateRegistration;
	}
	public void setStateRegistration(boolean stateRegistration) {
		this.stateRegistration = stateRegistration;
	}
}
