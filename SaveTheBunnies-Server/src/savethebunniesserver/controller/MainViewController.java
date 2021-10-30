package savethebunniesserver.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import savethebunniesserver.model.ConnectionDDBB;
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
			buttonStartStop.setText("Start!!");
			ConnectionDDBB.closeConnection();
		} else {
			Server.startServer();
			ConnectionDDBB.openConnection();
			buttonStartStop.setText("Stop");
		}
	}
}
