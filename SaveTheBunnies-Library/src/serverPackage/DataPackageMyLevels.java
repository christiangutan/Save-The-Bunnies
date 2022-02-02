package serverPackage;

import java.io.Serializable;

public class DataPackageMyLevels implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9016066287671042535L;
	private String [] levelsTxt;
	private int [] idLevels;
	private boolean state;
	private String msg;
	public DataPackageMyLevels(String [] levelsTxt, int[] idLevels, boolean state, String msg) {
		setLevelsTxt(levelsTxt);
		setIdLevels(idLevels);
		setState(state);
		setMsg(msg);
	}
	public String[] getLevelsTxt() {
		return levelsTxt;
	}
	public void setLevelsTxt(String[] levelsTxt) {
		this.levelsTxt = levelsTxt;
	}
	public int[] getIdLevels() {
		return idLevels;
	}
	public void setIdLevels(int[] idLevels) {
		this.idLevels = idLevels;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
