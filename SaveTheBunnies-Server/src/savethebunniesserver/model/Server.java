package savethebunniesserver.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import savethebunniesclient.util.DataPackageLoginUser;
import savethebunniesclient.util.DataPackageRegistrationUser;
import savethebunniesserver.util.Resources;
import savethebunniesserver.util.DataPackageLoggedUser;
import savethebunniesserver.util.DataPackageRegisteredUser;

public class Server {
	
	private static ServerSocket server;
	
	private static boolean stateServer = false;	//state of server; True = server on; False = server off
	
	private static Thread threadServer = new Thread (new Runnable() {
		@Override
		public void run(){
			
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
					try {
						socket = server.accept();
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
					
					if (inputObjectData instanceof DataPackageRegistrationUser) {
						String finalState = registrationUser(inputObjectData);
						DataPackageRegisteredUser dataSend = null;		
						
						DataPackageRegistrationUser dataUser = null;
						
						dataUser = (DataPackageRegistrationUser) inputObjectData;
						
						if(finalState.equals("")) {
							dataSend = new DataPackageRegisteredUser("", true);
							Log.addInfoActivityPanel("User Registered - User: " + dataUser.getUsername() , Color.BLUE);	
						} else {
							dataSend = new DataPackageRegisteredUser(finalState, false);
							Log.addInfoError("User NO Registered - User: " + dataUser.getUsername());
						}
						
						try {
							Log.addInfoCorrect("Sending to " + ((DataPackageRegistrationUser) inputObjectData).getIp() + " DataResgister");
							sendData(dataSend, inputObjectData, ((DataPackageRegistrationUser) inputObjectData).getIp());
						} catch (IOException e) {
							Log.addInfoError("ERROR - Socket DataPackageRegisteredUser");
							e.printStackTrace();
						} 
						
					}
					Log.addInfoCorrect("Antes de comprobar DataPackageLoginUser");
					if (inputObjectData instanceof DataPackageLoginUser) {
						String finalState = loginUser(inputObjectData);
						DataPackageLoggedUser dataSend = null;
						Log.addInfoCorrect("Antes del if");
						Log.addInfoCorrect(finalState);
						if(finalState.equals("")) {
							Log.addInfoCorrect("Correcto");
							dataSend = ConnectionDDBB.getLoggedInfo((DataPackageLoginUser) inputObjectData);
						} else {
							Log.addInfoCorrect("Incorrecto");
							dataSend = new DataPackageLoggedUser("", "", "", "", 0, null, null, 0, null, "", false);
						}
						try {
							Log.addInfoCorrect("Sending to " + ((DataPackageLoginUser) inputObjectData).getIp() + " DataLogin");
							sendData(dataSend, inputObjectData, ((DataPackageLoginUser) inputObjectData).getIp());
						} catch (IOException e) {
							Log.addInfoError("ERROR - Socket DataPackageLoggedUser");
							e.printStackTrace();
						} 
						
					}
					
					
					
					
					
					
					
					
					
					
					/*--------------------------------------------------------------------------*/
					
					try {
						socket.close();
					} catch (IOException e) {
						Log.addInfoError("ERROR - It's not possible to close the socket");
						e.printStackTrace();
					}
				}

		}
		
	});
	
	public static void sendData(Object dataSend, Object inputObjectData, String ip) throws IOException {
		Socket socketSend;
		socketSend = new Socket(/*((DataPackageRegistrationUser) inputObjectData).getIp()*/"192.168.56.1", Resources.PORTSOCKET);
		ObjectOutputStream outputStream = new ObjectOutputStream(socketSend.getOutputStream());
		outputStream.writeObject(dataSend);
		socketSend.close();
		
	}
	
	public static void startServer() {
		Server.stateServer = true;
		threadServer.start();
		Log.startServices();
	}
	
	public static void stopServer() {
		Server.stateServer = false;
		Platform.runLater(() -> {
			Server.stopServer();
		});
		Log.stopServices();
						
	}
	
	public static boolean serverState() {
		return Server.stateServer;
	}
	
	private static String registrationUser(Object inputObjectData) {
		DataPackageRegistrationUser dataUser = null;
		
		dataUser = (DataPackageRegistrationUser) inputObjectData;
		
		return ConnectionDDBB.registerUser(dataUser);		
	}
	
	private static String loginUser(Object inputObjectData) {
		DataPackageLoginUser dataUser = null;
		
		dataUser = (DataPackageLoginUser) inputObjectData;
		
		return ConnectionDDBB.loginUser(dataUser);

	}
}
