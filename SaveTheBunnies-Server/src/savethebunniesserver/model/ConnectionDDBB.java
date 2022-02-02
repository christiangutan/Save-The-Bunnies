package savethebunniesserver.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clientPackage.DataPackageLoginUser;
import clientPackage.DataPackageRegistrationUser;
import clientPackage.DataPackageUpdateLevels;
import javafx.scene.paint.Color;
import savethebunniesserver.util.DataServer;
import savethebunniesserver.util.Util;
import serverPackage.DataPackageLoggedUser;

public class ConnectionDDBB {
	private static final String user = "root";
	private static final String password= "";
	private static Connection connection;
	
	public static boolean openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/savethebunnies", user, password);
			Log.addInfoCorrect("Connection to the DDBB (port 3306) - Correct");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			Log.addInfoError("ERROR - It's not possible to connect to the DDBB (port 3306)");
			return false;
		}
	}
	
	public static void registerUser (DataPackageRegistrationUser info) throws ServerException{
		try {
			Log.addInfoCorrect("Register User - ConnectionDDBB");
			
			Date date = new Date(new java.util.Date().getTime());
			
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("INSERT INTO USERS VALUE (null, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, info.getUsername());	// username
			statement.setString(2, info.getName());		// name
			statement.setString(3, info.getPassword());	// password
			statement.setString(4, info.getEmail());	// email
			statement.setInt(5, 0);						// lastLevenPassedStory
			statement.setString(6, "");					// levelsBuilt
			statement.setInt(7, 1);						// idImageProfile --->default image
			statement.setDate(8, date);					// date
						
			statement.executeUpdate();
			
			statement.close();
		} catch (SQLException e) {
			
			if(e.getMessage().contains("for key 'username'")) throw new ServerException(ServerException.ERROR_REGISTRATION_USERNAME_NOT_AVAILABLE);
			
			Log.addInfoError("ERROR DDBB - ERROR 001 - " + e.getMessage());
			
			throw new ServerException(ServerException.ERROR_DDBB);
		}
	}
	
	public static void loginUser (DataPackageLoginUser info) throws ServerException {
		
		Log.addInfoCorrect("Login User - ConnectionDDBB");
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT COUNT(*) FROM savethebunnies.users WHERE username = ?");
			statement.setString(1, info.getUsername());	//username
			ResultSet rs = statement.executeQuery();
			
			rs.next();
			
			if(rs.getInt(1) != 1) throw new ServerException(ServerException.ERROR_LOGIN_USER_NOT_USER_FOUND);
			
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			throw new ServerException(ServerException.ERROR_DDBB);
		}
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT COUNT(*) FROM savethebunnies.users WHERE username = ? AND password = ?");
			statement.setString(1, info.getUsername());		//username
			statement.setString(2, info.getPassword());		//password
			
			ResultSet rs = statement.executeQuery();
			
			rs.next();
			
			if(rs.getInt(1)!=1) throw new ServerException(ServerException.ERROR_LOGIN_USER_INCORRECT_PASSWORD);
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			throw new ServerException(ServerException.ERROR_DDBB);
		}
	}
	
	public static DataPackageLoggedUser getLoggedInfo(DataPackageLoginUser info) {
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT * FROM savethebunnies.users WHERE username = ? AND password = ?");
			statement.setString(1, info.getUsername());		//username
			statement.setString(2, info.getPassword());		//password
			
			ResultSet rs = statement.executeQuery();
			
			rs.next();
			String username = rs.getString(2);
			String name = rs.getString(3);
			String password = rs.getString(4);
			String email = rs.getString(5);
			int lastLevenPassedStory = rs.getInt(6);
			String levelsBuiltString = rs.getString(7);
			int idProfile = rs.getInt(8);
			Date dateRegistration = rs.getDate(9);
			
			int[] levelsBuilt = Util.conversorStringToIntArrayLevels(levelsBuiltString);
			
			return new DataPackageLoggedUser (username, name, password, email, lastLevenPassedStory,
					levelsBuilt, idProfile, dateRegistration, "User info correct", true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateLastStoryLevel(DataPackageUpdateLevels info) throws ServerException {
		Log.addInfoCorrect("UpdateLastStoryLevel - ConnectionDDBB");
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("UPDATE `users` SET `lastLevenPassedStory` = ?  WHERE username = ?");
			statement.setInt(1, info.getLastLevel());	//Last level
			statement.setString(2, info.getUsername());	//username
			statement.executeUpdate();
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			throw new ServerException(ServerException.ERROR_DDBB);
		}
	}

	public static void addNewLevel(clientPackage.DataPackageNewLevel info) throws ServerException {
		Log.addInfoCorrect("AddNewLevel - ConnectionDDBB");
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("INSERT INTO `levels` (`idLevel`, `txt`, `username`) VALUES (NULL, ?, ?)");
			statement.setString(1, info.getLevel());	//Level
			statement.setString(2, info.getUsername());	//username
			statement.executeUpdate();
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			throw new ServerException(ServerException.ERROR_DDBB);
		}
	}
	
	public static void deleteLevel(clientPackage.DataPackageDeleteLevel info) throws ServerException {
		Log.addInfoCorrect("DeleteLevel - ConnectionDDBB");
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("DELETE FROM `levels` WHERE `levels`.`idLevel` = ?");
			statement.setInt(1, info.getLevel());	//Level
			statement.executeUpdate();
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			throw new ServerException(ServerException.ERROR_DDBB);
		}
	}
	
	public static void modifyLevel(clientPackage.DataPackageModifyLevel info) throws ServerException {
		Log.addInfoCorrect("ModifyLevel - ConnectionDDBB");
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("UPDATE `levels` SET `txt` = ? WHERE `levels`.`idLevel` = ?");
			statement.setString(1, info.getLevel());	//Level
			statement.setInt(2, info.getIdLevel());	//username
			statement.executeUpdate();
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			throw new ServerException(ServerException.ERROR_DDBB);
		}
	}

	public static serverPackage.DataPackageMyLevels getMyLevelsInfo(clientPackage.DataPackageMyLevels info) {
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT * FROM savethebunnies.levels WHERE username = ? ");
			statement.setString(1, info.getUsername());		//username			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<String> levelsTxt = new ArrayList<String>() ;
			ArrayList <Integer> idLevels = new ArrayList<Integer>();
			
			while(rs.next()) {
				idLevels.add(rs.getInt(1)); 	//id
				levelsTxt.add(rs.getString(2));	//Level
			}
			
			String [] levelsTxtArray = levelsTxt.toArray(new String[levelsTxt.size()]);
			int [] idLevelsArray = idLevels.stream().mapToInt(i->i).toArray();
			
			Log.addInfoActivityPanel("My Levels Send - User: " + info.getUsername(), Color.PURPLE);
			
			return new serverPackage.DataPackageMyLevels (levelsTxtArray, idLevelsArray, true, "");
			
		} catch (SQLException e) {
			e.printStackTrace();
			Log.addInfoActivityPanel("My Levels NOT Send - User: " + info.getUsername(), Color.PURPLE);
			return new serverPackage.DataPackageMyLevels (null, null, false, "Internal ERROR");
		}
	}

	public static serverPackage.DataPackageAvailableLevels getAvailableLevelsInfo(clientPackage.DataPackageAvailableLevels info) {
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT * FROM savethebunnies.levels");
			//statement.setString(1, info.getUsername());		//username			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<String> levelsTxt = new ArrayList<String>() ;
			ArrayList<Integer> idLevels = new ArrayList<Integer>();
			//ArrayList<String> usernamesLevels = new ArrayList<String>();
			
			while(rs.next()) {
				idLevels.add(rs.getInt(1)); 			//id
				levelsTxt.add(rs.getString(2));			//Level
				//usernamesLevels.add(rs.getString(3));	//Username				
			}
			String [] levelsTxtArray = levelsTxt.toArray(new String [0]);
			//String [] usernameLevelsArray = usernamesLevels.toArray(new String[0]);
			int [] idLevelsArray = idLevels.stream().mapToInt(i->i).toArray();
			
			Log.addInfoActivityPanel("Available Levels Send - User: " + info.getUsername(), Color.PURPLE);
			
			return new serverPackage.DataPackageAvailableLevels (levelsTxtArray, idLevelsArray, true, "");
			
		} catch (SQLException e) {
			e.printStackTrace();
			Log.addInfoActivityPanel("Available Levels NOT Send - User: " + info.getUsername(), Color.PURPLE);
			return new serverPackage.DataPackageAvailableLevels (null, null, false, "Internal ERROR");
		}
	}	
	
	public static DataServer getDataServerUsersLevels() {
		DataServer ds = new DataServer(0,0);
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
						.prepareStatement("SELECT count(*) FROM `levels`");
			ResultSet rs = statement.executeQuery();
			rs.next();
			ds.setNumLevels(rs.getInt(1));
			
			PreparedStatement statement2 = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT count(*) FROM `users`");
			ResultSet rs2 = statement2.executeQuery();
			rs2.next();
			ds.setNumUsers(rs2.getInt(1));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
			Log.addInfoCorrect("DDBB Closed");
		} catch (SQLException e) {
			Log.addInfoError("DDBB NO Closed - ERROR");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
