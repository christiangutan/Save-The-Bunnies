package savethebunniesserver.controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import savethebunniesserver.app.GuiApp;
import savethebunniesserver.model.ConnectionDDBB;
import savethebunniesserver.model.Log;
import savethebunniesserver.model.Server;
import savethebunniesserver.model.ServerException;
import savethebunniesserver.util.DataServer;

public class MainViewController {
	
	@FXML
	private TextFlow textFlow;
	
	@FXML
	private Button buttonStartStop;
	
	@FXML
	private ScrollPane scroll;
	
	@FXML
	private Text numberOfUsers;
	
	@FXML
	private Text numberOfLevels;
	
	
	@FXML
	public void initialize() {
		Log.setTextFlow(textFlow);
		Log.setScroll(scroll);
	}
	
	@FXML
	public void actionButtonStartStop() {
		
		if (Server.serverState()) {
			try {
				Server.stopServer();
				buttonStartStop.setText("Start!!");
				ConnectionDDBB.closeConnection();
			} catch (ServerException e) {
				Log.addInfoError(e.getMessage());
			}
			
		} else {
			Server.startServer();
			ConnectionDDBB.openConnection();
			buttonStartStop.setText("Stop");
			uptdateDataUsersAndLevels();
		}
	}
	
	@FXML
	public void actionLinkProjectSaveTheBunnies() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/christiangutan/Save-The-Bunnies"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionClose() {
		try {
			if(Server.serverState()) {
				Server.stopServer();
				ConnectionDDBB.closeConnection();
			}
		} catch (ServerException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	@FXML
	public void actionExportData() {
		uptdateDataUsersAndLevels();
		DirectoryChooser directoryChooser=new DirectoryChooser();
		File file = directoryChooser.showDialog(null);
		String path = file.getPath ();
		System.out.println(path);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			file = new File(path + "\\Save_The_Bunnies_Data_" + dtf.format(LocalDateTime.now()) + ".txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("Save The Bunnies Data"
	        		+ "\nExported: " + dtf2.format(LocalDateTime.now())
	        		+ "\nNumber of Users: " + numberOfUsers.getText()
	        		+ "\nNumber of Levels: " + numberOfLevels.getText());
	        bw.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	public void actionExportLog() {
		uptdateDataUsersAndLevels();
		DirectoryChooser directoryChooser=new DirectoryChooser();
		File file = directoryChooser.showDialog(null);
		String path = file.getPath ();
		System.out.println(path);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			file = new File(path + "\\Save_The_Bunnies_Log_" + dtf.format(LocalDateTime.now()) + ".txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("Save The Bunnies Log"
	        		+ "\nExported: " + dtf2.format(LocalDateTime.now()) + "\n"
	        		+ Log.getTextLog());
	        bw.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	public void uptdateDataUsersAndLevels() {
		DataServer ds = ConnectionDDBB.getDataServerUsersLevels();
		numberOfUsers.setText(ds.getNumUsers() + "");
		numberOfLevels.setText(ds.getNumLevels() + "");
	}
}
