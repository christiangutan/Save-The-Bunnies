package savethebunniesclient.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import savethebunniesclient.util.DataPackageLoginUser;
import savethebunniesclient.util.DataPackageRegistrationUser;
import savethebunniesclient.util.Resources;
import savethebunniesserver.util.DataPackageLoggedUser;
import savethebunniesserver.util.DataPackageRegisteredUser;

public class ConnectionServer {
	
	private static String messageAnswer;

	public static String registerUser(String username, String name, String email, String password) {	
		try {
			DataPackageRegistrationUser data = new DataPackageRegistrationUser(username, name, email, password);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketRegisteredUser();
		} catch (IOException e) {
			e.printStackTrace();
			return "Error connection with Server";
		}
	}	
	
	public static String loginUser(String username, String password) {	
		try {
			DataPackageLoginUser data = new DataPackageLoginUser(username, password);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketLoggedUser();
		} catch (IOException e) {
			return "No connection with Server";
		}
	}	
	
	private static String openServerSocketRegisteredUser() {
		Thread threadServerWaitingAnswer = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocket server = null;
				Socket socket = null;
				ObjectInputStream inputStream = null;
				Object inputObjectData = null;
				ConnectionServer.setMessageAnswer("");
				try {
					server = new ServerSocket(Resources.PORTSERVERCREATED);				
					socket = server.accept();
					server.close();
					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof DataPackageRegisteredUser) {
							if(((DataPackageRegisteredUser) inputObjectData).isStateRegistration()) {
								ConnectionServer.setMessageAnswer(((DataPackageRegisteredUser) inputObjectData).getMessageInfo());
							} else {
								ConnectionServer.setMessageAnswer(((DataPackageRegisteredUser) inputObjectData).getMessageInfo());
								//ConnectionServer.setMessageAnswer("You are using an old version");
							}
							
						}
						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("Error registration. Try again");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with server");
				}finally {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		threadServerWaitingAnswer.start();
		
		try {
			threadServerWaitingAnswer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return ConnectionServer.getMessageAnswer();
	}
	
	private static String openServerSocketLoggedUser() {
		Thread threadServerWaitingAnswer = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocket server = null;
				Socket socket = null;
				ObjectInputStream inputStream = null;
				Object inputObjectData = null;
				ConnectionServer.setMessageAnswer("");
				try {
					server = new ServerSocket(Resources.PORTSERVERCREATED);
					socket = server.accept();
					server.close();
					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof DataPackageLoggedUser) {
							User.setEmail(((DataPackageLoggedUser) inputObjectData).getEmail());
							User.setIdImageProfile(((DataPackageLoggedUser) inputObjectData).getIdImageProfile());
							User.setLastLevelStory(((DataPackageLoggedUser) inputObjectData).getLastLevelStory());
							User.setName(((DataPackageLoggedUser) inputObjectData).getName());
							User.setUsername(((DataPackageLoggedUser) inputObjectData).getUsername());
							//User.setLevels(((DataPackageLoggedUser) inputObjectData).getLevelsBuilt());
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("Error login. Try again ");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				}
				
				
			}
			
		});
		
		threadServerWaitingAnswer.start();
		
		try {
			threadServerWaitingAnswer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return ConnectionServer.getMessageAnswer();
	}

	public static String getMessageAnswer() {
		return ConnectionServer.messageAnswer;
	}

	public static void setMessageAnswer(String messageAnswer) {
		ConnectionServer.messageAnswer = messageAnswer;
	}
	
}
