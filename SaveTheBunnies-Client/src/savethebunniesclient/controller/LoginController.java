package savethebunniesclient.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.util.OnActionData;
import savethebunniesclient.view.ErrorPopUpWindow;
import savethebunniesclient.view.InfoPopUpWindow;

public class LoginController {
	
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonRegister;
	
	@FXML
	public void actionLogin() {
		Thread threadLoginUser = new Thread (new Runnable() {
			@Override
			public void run() {
				String state = ConnectionServer.loginUser(usernameTextField.getText(), passwordTextField.getText());
				if(state.equals("")) {
					InfoPopUpWindow window = new InfoPopUpWindow("User logged");
					window.createView();
					window.setOnAction(new OnActionData() {
						@Override
						public void onAction() {
							try {
								GuiApp.main.createView("Welcome.fxml", "css-Welcome.css");
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
		
		threadLoginUser.setDaemon(true);
		threadLoginUser.start();
	}
	
	@FXML
	public void actionRegister() {
		try {
			GuiApp.main.createView("Register.fxml","css-Login-Registration.css");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
