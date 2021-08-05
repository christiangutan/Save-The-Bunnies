package savethebunnies.controller;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import savethebunnies.app.GuiApp;

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
		System.out.println("EJECUTADO EL CONSTRUCTOR");
		usernameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					ConnectionServer.checkUsername(usernameTextField.getText());
				}
			}			
		});
		
		threadCheckPasswords = new Thread (new Runnable() {
			@Override
			public void run() {
				while(!exit) {
					if(confirmPasswordTextField.getText().equals(passwordTextField.getText())) {
						System.out.println("contraseñas iguales");
						registerButton.setDisable(false);
					} else {
						System.out.println("Contraseñas iguales");
						registerButton.setDisable(true);
					}
				}
			}
			
		});
		threadCheckPasswords.setDaemon(true);
		threadCheckPasswords.start();
	}
		
	private void checkPassword() {
		
	}
	
	@FXML
	private void actionRegistration() {
		System.out.println("Registrar Usuario");
		Thread threadRegistrationUser = new Thread (new Runnable() {
			@Override
			public void run() {
				boolean state = ConnectionServer.registerUser(usernameTextField.getText(), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
				
				if(state) {
					//Everything is right
					//Create the message User Created
				}else {
					//Something has gone wrong
					//User didn't created
				}
				
			}
		});
		
		threadRegistrationUser.setDaemon(true);
		threadRegistrationUser.start();
	}
	
	@FXML
	private void actionCancel() {
		System.out.println("Cancel");
		try {
			GuiApp.main.createView("Login.fxml","css-Login-Registration.css");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
