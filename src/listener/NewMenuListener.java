package listener;

import javafx.event.*;
import javafx.scene.control.*;

public class NewMenuListener implements EventHandler<ActionEvent> {
	private TextArea text;
	public NewMenuListener(TextArea text) {
		this.text = text;
	}
	
	public void handle(ActionEvent ae) {
		text.setText("");
		text.requestFocus();
	}
}
