package savethebunniesclient.model.game;

public class ConnectionServerException extends Exception{
	
	public ConnectionServerException() {
		super();
	}
	
	public ConnectionServerException(String msg) {
		super(msg);
	}

	public static final String ERROR_NO_CONNECTION_SERVER = "No connection with Server";
	public static final String ERROR_CONECCTION_SERVER = "Error connection with Server";
	public static final String ERROR_LOGIN_INCORRECT_PASSWORD = "The password is incorrect";
	public static final String ERROR_OLD_VERSION = "You are using an old version";
	public static final String ERROR_LOGIN_TRY_AGAIN = "Error login. Try again";
	
}
