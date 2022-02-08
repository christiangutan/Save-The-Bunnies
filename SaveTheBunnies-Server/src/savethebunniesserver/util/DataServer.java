package savethebunniesserver.util;

/**
 * Data server. They are updated when the DDBB is modified
 * @author christian_gutan
 *
 */
public class DataServer {
	private int numUsers;
	private int numLevels;
	public DataServer(int numUsers, int numLevels) {
		super();
		this.numUsers = numUsers;
		this.numLevels = numLevels;
	}
	public int getNumUsers() {
		return numUsers;
	}
	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}
	public int getNumLevels() {
		return numLevels;
	}
	public void setNumLevels(int numLevels) {
		this.numLevels = numLevels;
	}	
}
