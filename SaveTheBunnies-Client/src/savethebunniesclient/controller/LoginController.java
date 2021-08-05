package savethebunniesclient.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import savethebunniesclient.app.GuiApp;

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
		System.out.println("actionLogin");
	}
	
	@FXML
	public void actionRegister() {
		System.out.println("actionRegister");
		try {
			GuiApp.main.createView("Register.fxml","css-Login-Registration.css");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
