package savethebunniesclient.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import savethebunniesclient.util.DataPackageRegistrationUser;

public class ConnectionServer {
	
	private static Thread threadCheckUsername;
	
	private static boolean stateConnection;
	
	public static boolean connection() {
		
		Thread threadConnection = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					
					
					
					
				}catch(Exception e) {
					
					stateConnection = false;
				}
			}
			
		});
		
		threadConnection.start();
		
		return stateConnection;
	}
	
	public static boolean checkUsername(String username) {
		
		//connection con el server
		
		System.out.println("Checkeando si Username está disponible");
		return false;
	}

	public static boolean registerUser(String username, String name, String email, String password) {	
		try {
			DataPackageRegistrationUser data = new DataPackageRegistrationUser(username, name, email, password);

			Socket socket = new Socket("192.168.1.97", 9999);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
		} catch (IOException e) {
			System.out.println("no conection with socket");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}	
}
