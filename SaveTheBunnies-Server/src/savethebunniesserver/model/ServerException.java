package savethebunniesserver.model;

/**
 * ServerException clas
 * @author christian_gutan
 *
 */
public class ServerException extends Exception{
	public static String ERROR_LOGIN_USER_NOT_USER_FOUND = "This user is not registered";
	public static String ERROR_LOGIN_USER_INCORRECT_PASSWORD = "Incorrect Password";
	public static String ERROR_REGISTRATION_USERNAME_NOT_AVAILABLE = "Username not available";
	public static String ERROR_DDBB = "Internal server error\nContact the administrator\nERROR 001";
	
	public static String ERROR_SERVER_DOES_NOT_CLOSE = "It is not possible to close the server";
	
	public ServerException(String message) {
		super(message);
	}
	
	
	
}
