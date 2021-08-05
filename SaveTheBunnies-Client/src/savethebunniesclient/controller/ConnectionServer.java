package savethebunniesclient.controller;

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

		return true;
	}


	
}
