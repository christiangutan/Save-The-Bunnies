package savethebunniesserver.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.application.Platform;
import javafx.scene.image.Image;
import savethebunniesclient.util.DataPackageLoginUser;
import savethebunniesclient.util.DataPackageRegistrationUser;
import savethebunniesserver.util.DataPackageLoggedUser;
import savethebunniesserver.util.Util;

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
	
	public static String registerUser (DataPackageRegistrationUser info) {
		try {
			Log.addInfoCorrect("registerUser - ConnectionDDBB");
			
			Date date = new Date(new java.util.Date().getTime());
			
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("INSERT INTO USERS VALUE (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, info.getUsername());	// username
			statement.setString(2, info.getName());		// name
			statement.setString(3, info.getPassword());	// password
			statement.setString(4, info.getEmail());	// email
			statement.setInt(5, 0);						// lastLevenPassedStory
			statement.setString(6, "");					// levelsBuilt
			statement.setString(7, "");					// levelsPassed
			statement.setInt(8, 1);						// idImageProfile --->default image
			statement.setDate(9, date);					// date
			
			
			statement.executeUpdate();
			
			statement.close();
			
			return "";
			
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			
			if(e.getMessage().contains("for key 'username'")) return "This username is not available";
			
			return "Error BBDD";
		}
	}
	
	public static String loginUser (DataPackageLoginUser info) {
		Log.addInfoCorrect("registerUser - ConnectionDDBB");
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT COUNT(*) FROM savethebunnies.users WHERE username = ?");
			statement.setString(1, info.getUsername());	//username
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			
			rs.next();
			
			if(rs.getInt(1) != 1) return "No user exists";
			
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			e.printStackTrace();
			return "ERROR DDBB";
		}
		
		try {
			PreparedStatement statement = ConnectionDDBB.getConnection()
					.prepareStatement("SELECT COUNT(*) FROM savethebunnies.users WHERE username = ? AND password = ?");
			statement.setString(1, info.getUsername());		//username
			statement.setString(2, info.getPassword());		//password
			
			ResultSet rs = statement.executeQuery();
			
			rs.next();
			
			if(rs.getInt(1)==1) return "";
			else return "Incorrect password";
		} catch (SQLException e) {
			Log.addInfoError(e.getMessage());
			e.printStackTrace();
			return "ERROR DDBB";
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
			String levelsPassedString = rs.getString(8);
			int idProfile = rs.getInt(9);
			Date dateRegistration = rs.getDate(10);
			
			int[] levelsBuilt = Util.conversorStringToIntArrayLevels(levelsBuiltString);
			int[] levelsPassed = Util.conversorStringToIntArrayLevels(levelsPassedString);
			
			Log.addInfoError("Username: " + username + "; Name: " + name);
			
			return new DataPackageLoggedUser (username, name, password, email, lastLevenPassedStory,
					levelsBuilt, levelsPassed, idProfile, dateRegistration, "User info correct", false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
