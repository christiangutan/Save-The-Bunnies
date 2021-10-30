package savethebunniesclient.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.view.ErrorPopUpWindow;
import savethebunniesclient.view.InfoPopUpWindow;

public class RegisterController {
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private TextField confirmPasswordTextField;
	
	@FXML
	private ImageView loadingCorrectUsername;
	@FXML
	private ImageView correctUsername;
	
	private boolean boolCorrectPassword;
	private boolean boolCorrectUsername;
	
	@FXML
	private Button registerButton;
	@FXML
	private Button cancelButton;
	
	private boolean exit = false;
	
	private Thread threadCheckPasswords;
		
	@FXML
	public void initialize() {		
		exit = false;
		
		usernameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					//ConnectionServer.checkUsername(usernameTextField.getText());
					
					//que se checkee si el username es correcto, yo creo que es mejo al final
				}
			}			
		});
		threadCheckPasswords = new Thread (new Runnable() {
			@Override
			public void run() {
				while(!exit) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(confirmPasswordTextField.getText().equals(passwordTextField.getText())) {
						registerButton.setDisable(false);
					} else {
						registerButton.setDisable(true);
					}
				}
			}
			
		});
		threadCheckPasswords.setDaemon(true);
		threadCheckPasswords.start();
	}
	
	@FXML
	private void actionRegistration() {
		Thread threadRegistrationUser = new Thread (new Runnable() {
			@Override
			public void run() {
				String state = ConnectionServer.registerUser(usernameTextField.getText(), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
				System.out.println("Resultado del registro: " + state);
				if(state.equals("")) {
					InfoPopUpWindow window = new InfoPopUpWindow("User registered");
					window.createView();
					window.setOnAction(new OnActionData() {
						@Override
						public void onAction() {
							try {
								GuiApp.main.createView("Login.fxml", "css-Login-Registration.css");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					ErrorPopUpWindow window = new ErrorPopUpWindow(state);
					window.createView();
					window.setOnAction(new OnActionData() {
						@Override
						public void onAction() {
						}
					});
				}
			}
		});
		
		threadRegistrationUser.setDaemon(true);
		threadRegistrationUser.start();
	}
	
	@FXML
	private void actionCancel() {
		try {
			GuiApp.main.createView("Login.fxml","css-Login-Registration.css");
			exit = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
