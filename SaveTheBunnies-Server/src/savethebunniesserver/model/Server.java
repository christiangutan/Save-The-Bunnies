package savethebunniesserver.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import savethebunniesclient.util.DataPackageRegistrationUser;

public class Server {
	
	private static ServerSocket server;
	
	private static boolean stateServer = false;	//state of server; True = server on; False = server off
	
	private static Thread threadServer = new Thread (new Runnable() {
		@Override
		public void run() {
			
			Platform.runLater(() -> {
				Log.startServices();
			});
			
			stateServer = true;		//state of server; True = server on; False = server off
			
				try {
					server= new ServerSocket(9999);
					Platform.runLater(() -> {
						Log.addInfoCorrect("Open connection on port 9999");
					});
					
				} catch (IOException e) {
					Platform.runLater(() -> {
						Log.addInfoError("ERROR - Connection on port 9999");
					});
					e.printStackTrace();
				}
				
				while(Server.stateServer) {		//depends on stateServer and can be changed from the GUI button
					Socket socket = null;
					try {
						socket = server.accept();
					} catch (IOException e) {
						Platform.runLater(() -> {
							Log.addInfoError("ERROR - Server.accept");
						});
						e.printStackTrace();
					}
					
					ObjectInputStream inputStream = null;
					try {
						inputStream = new ObjectInputStream(socket.getInputStream());
					} catch (IOException e) {
						Platform.runLater(() -> {
							Log.addInfoError("ERROR - InputStream");
						});
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
						registrationUser(inputObjectData);
					}
					
					//if (inputObjectData instanceof )
					
					
					
					
					
					
					
					
					
					
					/*--------------------------------------------------------------------------*/
					
					try {
						socket.close();
					} catch (IOException e) {
						Platform.runLater(() -> {
							Log.addInfoError("ERROR - It's not possible to close the socket");
						});
						e.printStackTrace();
					}
				}

		}
		
	});
	
	public static void startServer() {
		Server.stateServer = true;
		threadServer.start();
	}
	
	public static void stopServer() {
		Server.stateServer = false;
	}
	
	public static boolean serverState() {
		return Server.stateServer;
	}
	
	private static boolean registrationUser(Object inputObjectData) {
		DataPackageRegistrationUser dataUser = null;
		
		dataUser = (DataPackageRegistrationUser) inputObjectData;
		
		System.out.println(dataUser.toString());
		
		//Activities to register User
		
		
		
		//Activities to register User
		
		Platform.runLater(() -> {
			Log.addInfoActivityPanel("Usuario Registrado, se supone que correctamente" , Color.BLUE);
		});
		
		return true;
	}
	
	private static boolean loginUser(Object inputObjectData) {
		
		return true;
	}
}
