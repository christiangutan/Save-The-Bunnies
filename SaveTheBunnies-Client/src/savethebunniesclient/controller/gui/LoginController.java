package savethebunniesclient.controller.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.ErrorPopUpWindow;
import savethebunniesclient.model.view.InfoPopUpWindow;
import savethebunniesclient.util.OnActionData;

/**
 * Controller of the login view
 * @author christian_gutan
 *
 */
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
	private ImageView mainPhoto;
	
	@FXML
    public void initialize() {
		GuiApp.setPlaying(false);

	}
	
	@FXML
	public void actionLogin() {
		
		Music.playSound(SoundType.BUTTON);
		Thread threadLoginUser = new Thread (new Runnable() {
			@Override
			public void run() {
				String state = ConnectionServer.loginUser(usernameTextField.getText(), passwordTextField.getText());
				if(state.equals("")) {
					InfoPopUpWindow window = new InfoPopUpWindow("User logged");
					ConnectionServer.getImageProfile(User.getUsername());
					window.createView();
					window.setOnAction(new OnActionData() {
						@Override
						public void onAction() {
							try {
								Music.playSound(SoundType.MUSICMENU);
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
		Music.playSound(SoundType.BUTTON);
		try {
			GuiApp.main.createView("Register.fxml","css-Login-Registration.css");
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
}
