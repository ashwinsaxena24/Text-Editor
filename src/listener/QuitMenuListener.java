package listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;

public class QuitMenuListener implements EventHandler<ActionEvent> {
	Stage primaryStage;
	
	public QuitMenuListener(Stage stage) {
		primaryStage = stage;
	}
	public void handle(ActionEvent ae) {
		primaryStage.close();
	}
}