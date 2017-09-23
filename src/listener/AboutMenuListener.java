package listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AboutMenuListener implements EventHandler<ActionEvent> {
	public void handle(ActionEvent ae) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("About");
		alert.setContentText("Text Editor 1.0\nCreated By: Ashwin Saxena");
		alert.showAndWait();
	}
}