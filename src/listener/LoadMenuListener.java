package listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class LoadMenuListener implements EventHandler<ActionEvent> {
	Stage primaryStage;
	TextArea text;
	
	public LoadMenuListener(Stage stage,TextArea text) {
		primaryStage = stage;
		this.text = text;
	}
	public void handle(ActionEvent ae) {
		FileChooser loadFileChoose = new FileChooser();
		loadFileChoose.setTitle("Open File");
		loadFileChoose.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
		loadFileFromDisk(loadFileChoose.showOpenDialog(primaryStage));
	}

	public void loadFileFromDisk(File lFile) {
		text.setText("");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(lFile));
			String line;
			while((line = reader.readLine()) != null) {
				text.appendText(line+"\n");
			}
			reader.close();
		}
		catch(IOException ex) {
			System.out.println("Cannot open file");
		}
	}
}

