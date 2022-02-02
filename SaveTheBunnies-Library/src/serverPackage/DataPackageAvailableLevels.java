package serverPackage;

import java.io.Serializable;

public class DataPackageAvailableLevels implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4282150255203229718L;
	private String[] levelsTxt;
	private int[] idLevels;
	private boolean state;
	private String msg;
	public DataPackageAvailableLevels(String [] levelsTxt, int [] idLevels, boolean state, String info) {
		setLevelsTxt(levelsTxt);
		setIdLevels(idLevels);
		setState(state);
		setMsg(info);
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
