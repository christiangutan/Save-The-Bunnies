package savethebunniesserver.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import savethebunniesserver.model.Log;
import savethebunniesserver.model.Server;

public class MainViewController {
	
	@FXML
	private TextFlow textFlow;
	
	@FXML
	private Button buttonStartStop;
	
	@FXML
	public void initialize() {
		Log.setTextFlow(textFlow);
	}
	
	@FXML
	public void actionButtonStartStop() {
		if (Server.serverState()) {
			Server.stopServer();
		} else {
			Server.startServer();
		}
	}
}
