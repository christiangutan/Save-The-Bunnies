package savethebunniesserver.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import savethebunniesserver.util.Resources;
import serverPackage.*;
import clientPackage.*;


public class Server {
	
	private static ServerSocket server;
	
	private static boolean stateServer = false;	//state of server; True = server on; False = server off
	
	private static Thread threadServer;
		
	public static void startServer() {
		Server.stateServer = true;
		
		threadServer = new Thread (new Runnable() {
			@Override
			public void run(){
				
				boolean found = false;
				
				stateServer = true;		//state of server; True = server on; False = server off
					try {
						server= new ServerSocket(9999);
						Log.addInfoCorrect("Open connection on port 9999");
					} catch (IOException e) {
						Log.addInfoError("ERROR - Connection on port 9999");
						stateServer = false;
						e.printStackTrace();
					}
					
					while(Server.stateServer) {		//depends on stateServer and can be changed from the GUI button
						Socket socket = null;
						found = false;
						try {
							System.out.println("Socket.accept()");
							socket = server.accept();
							System.out.println("Socket Accepted");
						} catch (IOException e) {
							Log.addInfoError("ERROR - Server.accept");
							e.printStackTrace();
						}
						
						ObjectInputStream inputStream = null;
						try {
							inputStream = new ObjectInputStream(socket.getInputStream());
						} catch (IOException e) {
							Log.addInfoError("ERROR - InputStream");
							e.printStackTrace();
						}
						
						/*--------------------------------------------------------------------------*/
						//							All possibilities				
						/*--------------------------------------------------------------------------*/
						Object inputObjectData = null;
						
						try {
							inputObjectData = inputStream.readObject();
						} catch (ClassNotFoundException | IOException e1) {
							e1.printStackTrace();
						}
						
						if (inputObjectData instanceof DataPackageRegistrationUser && found == false) {
							System.out.println("DataPackageRegistrationUser");
							found = true;
							
							DataPackageRegisteredUser dataSend = null;		
							
							DataPackageRegistrationUser dataUser = null;
							
							dataUser = (DataPackageRegistrationUser) inputObjectData;
							
							try {
								registrationUser(inputObjectData);
								
								dataSend = new DataPackageRegisteredUser("", true);
								Log.addInfoActivityPanel("User Registered - User: " + dataUser.getUsername() , Color.ORANGE);	
								
							} catch (ServerException e) {
								dataSend = new DataPackageRegisteredUser(e.getMessage(), false);
								Log.addInfoActivityPanel("User NO Registered - User: " + dataUser.getUsername() + " - Cause: " + e.getMessage(), Color.PURPLE);
							}
							
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " DataRegister", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageRegisteredUser");
							} 							
						}

						if (inputObjectData instanceof DataPackageLoginUser && found == false) {
							System.out.println("DataPackageLoginUser");
							found = true;
							
							DataPackageLoggedUser dataSend = null;
							
							DataPackageLoginUser dataUser = null;
							
							dataUser = (DataPackageLoginUser)inputObjectData;
							
							try {
								loginUser(inputObjectData);
								
								dataSend = ConnectionDDBB.getLoggedInfo((DataPackageLoginUser) inputObjectData);
								Log.addInfoActivityPanel("User Logged - User: " + dataUser.getUsername(), Color.PURPLE);
							
							} catch (ServerException e) {
								Log.addInfoActivityPanel("User NO Logged - User: " + dataUser.getUsername() + " - Cause: " + e.getMessage(), Color.PURPLE);
								dataSend = new DataPackageLoggedUser("", "", "", "", 0, null, 0, null, e.getMessage(), false);
							}
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " DataLogin", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageLoggedUser");
							} 
						}
						
						if (inputObjectData instanceof DataPackageUpdateLevels && found == false) {
							found = true;
							
							DataPackageUpdatedLevels dataSend = null;
							
							DataPackageUpdateLevels dataUser = null;
							
							dataUser = (DataPackageUpdateLevels)inputObjectData;
							
							try {
								
								ConnectionDDBB.updateLastStoryLevel(dataUser);
								
								dataSend = new DataPackageUpdatedLevels(true, "");
								Log.addInfoActivityPanel("Update last story level - User: " + dataUser.getUsername(), Color.PURPLE);
							
							} catch (ServerException e) {
								Log.addInfoActivityPanel("No Updated last story level - User: " + dataUser.getUsername() + " - Cause: " + e.getMessage(), Color.PURPLE);
								dataSend = new DataPackageUpdatedLevels(false, "Error server");
							}
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " DataLastStoryLevelsUpdated", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageUpdateLevels");
							} 
						}
						
						if (inputObjectData instanceof clientPackage.DataPackageNewLevel && found == false) {
							found = true;
							
							serverPackage.DataPackageNewLevel dataSend = null;
							
							clientPackage.DataPackageNewLevel dataUser = null;
							
							dataUser = (clientPackage.DataPackageNewLevel)inputObjectData;
							
							try {
								ConnectionDDBB.addNewLevel(dataUser);
								
								dataSend = new serverPackage.DataPackageNewLevel("", true);
								Log.addInfoActivityPanel("Add new level - User: " + dataUser.getUsername(), Color.PURPLE);
							
							} catch (ServerException e) {
								Log.addInfoActivityPanel("No new level added - User: " + dataUser.getUsername() + " - Cause: " + e.getMessage(), Color.PURPLE);
								dataSend = new serverPackage.DataPackageNewLevel("Error server", false);
							}
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " DataNewLevelAdded", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageNewLevel");
							} 
						}
						
						if (inputObjectData instanceof clientPackage.DataPackageDeleteLevel && found == false) {
							found = true;
							
							serverPackage.DataPackageDeleteLevel dataSend = null;
							
							clientPackage.DataPackageDeleteLevel dataUser = null;
							
							dataUser = (clientPackage.DataPackageDeleteLevel)inputObjectData;
							
							try {
								ConnectionDDBB.deleteLevel(dataUser);
								
								dataSend = new serverPackage.DataPackageDeleteLevel("", true);
								Log.addInfoActivityPanel("Add new level - User: " + dataUser.getUsername(), Color.PURPLE);
							
							} catch (ServerException e) {
								Log.addInfoActivityPanel("No new level added - User: " + dataUser.getUsername() + " - Cause: " + e.getMessage(), Color.PURPLE);
								dataSend = new serverPackage.DataPackageDeleteLevel("Error server", false);
							}
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " DataLevelDeleted", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageDeleteLevel");
							} 
						}
						
						if (inputObjectData instanceof clientPackage.DataPackageModifyLevel && found == false) {
							found = true;
							
							serverPackage.DataPackageModifyLevel dataSend = null;
							
							clientPackage.DataPackageModifyLevel dataUser = null;
							
							dataUser = (clientPackage.DataPackageModifyLevel)inputObjectData;
							
							try {
								ConnectionDDBB.modifyLevel(dataUser);
								
								dataSend = new serverPackage.DataPackageModifyLevel("", true);
								Log.addInfoActivityPanel("Add new level - User: " + dataUser.getUsername(), Color.PURPLE);
							
							} catch (ServerException e) {
								Log.addInfoActivityPanel("No new level added - User: " + dataUser.getUsername() + " - Cause: " + e.getMessage(), Color.PURPLE);
								dataSend = new serverPackage.DataPackageModifyLevel("Error server", false);
							}
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " DataLevelModified", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageModifyLevel");
							} 
						}

						if (inputObjectData instanceof clientPackage.DataPackageMyLevels && found == false) {
							found = true;
							
							serverPackage.DataPackageMyLevels dataSend = null;
							
							clientPackage.DataPackageMyLevels dataUser = null;
							
							dataUser = (clientPackage.DataPackageMyLevels)inputObjectData;
							
							dataSend = ConnectionDDBB.getMyLevelsInfo(dataUser);
							
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + " MyLevels", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageMyLevels");
							} 
						}
						
						if (inputObjectData instanceof clientPackage.DataPackageAvailableLevels && found == false) {
							found = true;
							
							serverPackage.DataPackageAvailableLevels dataSend = null;
							
							clientPackage.DataPackageAvailableLevels dataUser = null;
							
							dataUser = (clientPackage.DataPackageAvailableLevels)inputObjectData;
							
							dataSend = ConnectionDDBB.getAvailableLevelsInfo(dataUser);
							
							try {
								Log.addInfoActivityPanel("Sending to " + dataUser.getIp() + "Available Levels", Color.BLUE);
								Socket socketSend;
								socketSend = new Socket(dataUser.getIp(), Resources.PORTSOCKET);
								ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
								outputStream.writeObject(dataSend);
								socketSend.close();
							} catch (IOException e) {
								Log.addInfoError("ERROR - Socket DataPackageMyLevels");
							} 
						}
						
						if(!found) {
							found = true;
							Log.addInfoCorrect("Closing Server - Step 2");	
						}
						
						/*--------------------------------------------------------------------------*/
						
						try {
							socket.close();
						} catch (IOException e) {
							Log.addInfoError("ERROR - It's not possible to close the socket");
							e.printStackTrace();
						}
					}
					
					try {
						server.close();
						Log.addInfoCorrect("Server Closed");	
					} catch (IOException e) {
						Log.addInfoError("ERROR - It's not possible to close the server");
						e.printStackTrace();
					}
			}
			
		});

		threadServer.start();
			
		Log.startServices();
	}
	
	public static void stopServer() throws ServerException {
		Server.stateServer = false;
		boolean dataSend = false;
		Socket socketSend;
		try {
			socketSend = new Socket(InetAddress.getLocalHost().getHostAddress(), 9999);
			ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
			outputStream.writeObject(dataSend);
			socketSend.close();
			Log.addInfoCorrect("Closing Server - Step 1");
			Log.stopServices();
		} catch (IOException e) {
			Log.addInfoError("ERROR - It's not possible to close server");
			throw new ServerException(ServerException.ERROR_SERVER_DOES_NOT_CLOSE);
		}
		
						
	}
	
	public static boolean serverState() {
		return Server.stateServer;
	}
	
	private static void registrationUser(Object inputObjectData) throws ServerException {
		DataPackageRegistrationUser dataUser = null;
		
		dataUser = (DataPackageRegistrationUser) inputObjectData;
		
		ConnectionDDBB.registerUser(dataUser);		
	}
	
	private static void loginUser(Object inputObjectData) throws ServerException {
		DataPackageLoginUser dataUser = null;
		
		dataUser = (DataPackageLoginUser) inputObjectData;
		
		ConnectionDDBB.loginUser(dataUser);
	}
	
	private static void updateLevel(Object inputObjectData) throws ServerException {
		DataPackageUpdateLevels dataUser = null;
		
		dataUser = (DataPackageUpdateLevels) inputObjectData;
		
		ConnectionDDBB.updateLastStoryLevel(dataUser);
	}
}
