package listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveMenuListener implements EventHandler<ActionEvent> {
	Stage primaryStage;
	TextArea text;
	
	public SaveMenuListener(Stage stage,TextArea text) {
		primaryStage = stage;
		this.text = text;
	}
	
	public void handle(ActionEvent ae) {
		FileChooser saveFileChoose = new FileChooser();
		saveFileChoose.setTitle("Save File");
		saveFileChoose.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
		saveFileOnDisk(saveFileChoose.showSaveDialog(primaryStage));
	}

	public void saveFileOnDisk(File sFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(sFile));
			writer.write(text.getText());
			writer.close();
		}
		catch(IOException ex) {
			System.out.println("Cannot save file");
		}
	}
}
