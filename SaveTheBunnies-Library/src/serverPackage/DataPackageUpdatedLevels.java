package serverPackage;

import java.io.Serializable;

public class DataPackageUpdatedLevels implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5192354321042276976L;
	private boolean state;
	private String error;
	
	public DataPackageUpdatedLevels(boolean state, String error) {
		setState(state);
		setError(error);
	}

	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
