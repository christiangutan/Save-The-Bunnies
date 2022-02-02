package savethebunniesclient.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.util.CreateFile;
import savethebunniesclient.util.Resources;
import serverPackage.*;
import clientPackage.*;


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
	
	public static String updateStoryLevel(String username, int newLevel) {
		try {
			DataPackageUpdateLevels data = new DataPackageUpdateLevels(username, newLevel);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketUpdatedStoryLevel();
		} catch (IOException e) {
			return "No connection with Server";
		}
	}
	
	public static String getAvailableLevels(String username) {
		try {
			clientPackage.DataPackageAvailableLevels data = new clientPackage.DataPackageAvailableLevels(username);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketGetAvailableLevels();
		} catch (IOException e) {
			return "No connection with Server";
		}
	}
	
	public static String getMyLevels(String username) {
		try {
			clientPackage.DataPackageMyLevels data = new clientPackage.DataPackageMyLevels(username);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketGetMyLevels();
		} catch (IOException e) {
			return "No connection with Server";
		}
	}
	
	public static String addNewLevel(String username, String level) {
		try {
			clientPackage.DataPackageNewLevel data = new clientPackage.DataPackageNewLevel(username, level);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketAddNewLevel();
		} catch (IOException e) {
			return "No connection with Server";
		}
	}
	
	public static String deleteLevel(String username, int idLevel) {
		try {
			clientPackage.DataPackageDeleteLevel data = new clientPackage.DataPackageDeleteLevel(username, idLevel);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketDeleteLevel();
		} catch (IOException e) {
			return "No connection with Server";
		}
	}
	
	public static String modifyLevel(String username, String level, int idLevel) {
		try {
			clientPackage.DataPackageModifyLevel data = new clientPackage.DataPackageModifyLevel(username, level, idLevel);

			Socket socket = new Socket(Resources.IP, Resources.PORTMAINSERVER);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(data);
			
			socket.close();
			
			return openServerSocketModifyLevel();
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
				} finally {
					try {
						server.close();
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

					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof DataPackageLoggedUser) {
							if(((DataPackageLoggedUser) inputObjectData).isStateLogin()) {
								//System.out.println("Email de la base de datos: " + ((DataPackageLoggedUser) inputObjectData).getEmail());
								User.setEmail(((DataPackageLoggedUser) inputObjectData).getEmail());
								User.setIdImageProfile(((DataPackageLoggedUser) inputObjectData).getIdImageProfile());
								User.setLastLevelPassedStory(((DataPackageLoggedUser) inputObjectData).getLastLevelPassedStory());
								User.setName(((DataPackageLoggedUser) inputObjectData).getName());
								User.setUsername(((DataPackageLoggedUser) inputObjectData).getUsername());
								//User.setLevels(((DataPackageLoggedUser) inputObjectData).getLevelsBuilt());
							} else {
								ConnectionServer.setMessageAnswer( ((DataPackageLoggedUser) inputObjectData).getMessageInfo());
							}
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
				} finally {
					try {
						server.close();
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
	
	private static String openServerSocketUpdatedStoryLevel() {
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

					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof DataPackageUpdatedLevels) {
							if(!((DataPackageUpdatedLevels) inputObjectData).isState()) {
								ConnectionServer.setMessageAnswer("Error servidor");
							}
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("Error");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				} finally {
					try {
						server.close();
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

	private static String openServerSocketGetMyLevels() {
		Thread threadServerWaitingAnswer = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocket server = null;
				Socket socket = null;
				ObjectInputStream inputStream = null;
				Object inputObjectData = null;
				ConnectionServer.setMessageAnswer("");
				CreateFile.deleteFolderContentMyLevels();							

				try {
					server = new ServerSocket(Resources.PORTSERVERCREATED);
					socket = server.accept();

					inputStream = new ObjectInputStream(socket.getInputStream());
					

					try {
						inputObjectData = inputStream.readObject();
						if (inputObjectData instanceof serverPackage.DataPackageMyLevels) {
							if(((serverPackage.DataPackageMyLevels) inputObjectData).isState()) {
								if(((serverPackage.DataPackageMyLevels) inputObjectData).getLevelsTxt().length == 0) {
									ConnectionServer.setMessageAnswer("No levels");
								} else {
									//System.out.println(((serverPackage.DataPackageMyLevels) inputObjectData).getLevelsTxt().length);
									
									
									for(int i = 0; i < ((serverPackage.DataPackageMyLevels) inputObjectData).getLevelsTxt().length; i++) {
										CreateFile.createFileMyLevels(((serverPackage.DataPackageMyLevels) inputObjectData).getLevelsTxt()[i], ((serverPackage.DataPackageMyLevels) inputObjectData).getIdLevels()[i]);
									}
									try {
										InfoController.UpdateMyLevels();
									} catch (IllegalArgumentException | LevelException e) {
										e.printStackTrace();
									}
									User.setLevelsBuilt(((serverPackage.DataPackageMyLevels) inputObjectData).getIdLevels());
								}
							} else {
								ConnectionServer.setMessageAnswer(((serverPackage.DataPackageMyLevels) inputObjectData).getMsg());
							}
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("You are using an old version");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				} finally {
					try {
						server.close();
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
	
	private static String openServerSocketGetAvailableLevels() {
		Thread threadServerWaitingAnswer = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocket server = null;
				Socket socket = null;
				ObjectInputStream inputStream = null;
				Object inputObjectData = null;
				ConnectionServer.setMessageAnswer("");
				CreateFile.deleteFolderContentsOnlineLevels();

				try {
					server = new ServerSocket(Resources.PORTSERVERCREATED);
					socket = server.accept();

					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						if (inputObjectData instanceof serverPackage.DataPackageAvailableLevels) {
							//System.out.println("state:" + ((serverPackage.DataPackageAvailableLevels) inputObjectData).isState());
							if(((serverPackage.DataPackageAvailableLevels) inputObjectData).isState()) {
								if(((serverPackage.DataPackageAvailableLevels) inputObjectData).getLevelsTxt().length == 0) {
									ConnectionServer.setMessageAnswer("No levels");
								} else {
									for(int i = 0; i < ((serverPackage.DataPackageAvailableLevels) inputObjectData).getLevelsTxt().length; i++) {
										CreateFile.createFile(((serverPackage.DataPackageAvailableLevels) inputObjectData).getLevelsTxt()[i], ((serverPackage.DataPackageAvailableLevels) inputObjectData).getIdLevels()[i]);
									}
									try {
										InfoController.UpdateOnlineLevels();
									} catch (IllegalArgumentException | LevelException e) {
										e.printStackTrace();
									}
								}
							} else {
								ConnectionServer.setMessageAnswer(((serverPackage.DataPackageAvailableLevels) inputObjectData).getMsg());
							}
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("You are using an old version");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				} finally {
					try {
						server.close();
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

	private static String openServerSocketAddNewLevel() {
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

					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof serverPackage.DataPackageNewLevel) {
							if(!((serverPackage.DataPackageNewLevel) inputObjectData).isStateNewLevel()) {
								ConnectionServer.setMessageAnswer("INTERNAL ERROR");
							}
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("Error");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				} finally {
					try {
						server.close();
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
	
	private static String openServerSocketDeleteLevel() {
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

					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof serverPackage.DataPackageDeleteLevel) {
							if(!((serverPackage.DataPackageDeleteLevel) inputObjectData).isStateNewLevel()) {
								ConnectionServer.setMessageAnswer("INTERNAL ERROR");
							}
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("Error");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				} finally {
					try {
						server.close();
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
	
	private static String openServerSocketModifyLevel() {
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

					inputStream = new ObjectInputStream(socket.getInputStream());
					
					try {
						inputObjectData = inputStream.readObject();
						
						if (inputObjectData instanceof serverPackage.DataPackageModifyLevel) {
							if(!((serverPackage.DataPackageModifyLevel) inputObjectData).isStateNewLevel()) {
								ConnectionServer.setMessageAnswer("INTERNAL ERROR");
							}
						} else {
							ConnectionServer.setMessageAnswer("You are using an old version");
						}						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
						ConnectionServer.setMessageAnswer("Error");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
					ConnectionServer.setMessageAnswer("Error connection with Server");
				} finally {
					try {
						server.close();
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
	
	public static String getMessageAnswer() {
		return ConnectionServer.messageAnswer;
	}

	public static void setMessageAnswer(String messageAnswer) {
		ConnectionServer.messageAnswer = messageAnswer;
	}
	
}
