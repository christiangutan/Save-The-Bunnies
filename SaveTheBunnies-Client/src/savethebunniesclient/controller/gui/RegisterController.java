package savethebunniesclient.controller.gui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.prism.paint.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.ErrorPopUpWindow;
import savethebunniesclient.model.view.InfoPopUpWindow;
import savethebunniesclient.util.OnActionData;

/**
 * Controller of the view where you can register as a new user
 * @author christian_gutan
 *
 */
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
	
	private boolean boolCorrectPassword = false;
	private boolean boolCorrectEmail = false;
	private boolean boolSuitablePassword = false;
	
	@FXML
	private Button registerButton;
	@FXML
	private Button cancelButton;
	
	private boolean exit = false;
	
	private Thread threadCheckParameters;
	
	@FXML
	private ImageView exclamationEmail;
	@FXML
	private ImageView exclamationPassword;
	@FXML
	private ImageView exclamationConfirmPassword;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
	@FXML
	public void initialize() {	
		GuiApp.setPlaying(false);
	
		registerButton.setDisable(true);

		exit = false;
		
		threadCheckParameters = new Thread (new Runnable() {
			@Override
			public void run() {
				while(!exit) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(!(passwordTextField.getText().toString().length() < 8)) {
						boolSuitablePassword = true;
						exclamationPassword.setVisible(false);
					} else {
						boolSuitablePassword = false;
						exclamationPassword.setVisible(true);
					}
					
					if(confirmPasswordTextField.getText().equals(passwordTextField.getText())) {
						boolCorrectEmail = true;
						exclamationConfirmPassword.setVisible(false);
					} else {
						boolCorrectEmail = false;
						exclamationConfirmPassword.setVisible(true);
					}
					
					if (validateEmail(emailTextField.getText().toString())) {
						exclamationEmail.setVisible(false);
						boolCorrectPassword = true;
					} else {
						boolCorrectPassword = false;
						exclamationEmail.setVisible(true);
					}	
					
					if(boolCorrectPassword && boolCorrectEmail && boolSuitablePassword) {
						registerButton.setDisable(false);
					} else {
						registerButton.setDisable(true);
					}
				}
			}
			
		});
		threadCheckParameters.setDaemon(true);
		threadCheckParameters.start();
	}
	
	@FXML
	private void actionRegistration() {
		Music.playSound(SoundType.BUTTON);
		Thread threadRegistrationUser = new Thread (new Runnable() {
			@Override
			public void run() {
				String state = ConnectionServer.registerUser(usernameTextField.getText(), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
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
		Music.playSound(SoundType.BUTTON);
		try {
			GuiApp.main.createView("Login.fxml","css-Login-Registration.css");
			exit = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@FXML
	public void close() {
		Music.playSound(SoundType.BUTTON);
		DoubleOptionPopUpWindow window = new DoubleOptionPopUpWindow("ARE YOU SURE?");
		window.setTextButton1("YES");
		window.setTextButton2("NO");
		window.setOnAction1(new OnActionData() {
			@Override
			public void onAction() {
				System.exit(0);
			}
		});
		window.setOnAction2(new OnActionData() {
			@Override
			public void onAction() {
			}
		});
		window.createView();
	}
	
	public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
	}
}
