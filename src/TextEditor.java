import java.io.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;

public class TextEditor extends Application {
	TextArea text;
	MenuBar menuBar;
	Menu file,help;
	ScrollPane scroller;
	Stage primaryStage;
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		primaryStage = new Stage();
		primaryStage.setTitle("Editor");
		VBox rootNode = new VBox();
		primaryStage.setScene(new Scene(rootNode));
		primaryStage.setMaximized(true);

		text = new TextArea();
		text.setPrefSize(1350,650);
		text.setWrapText(true);

		scroller = new ScrollPane(text);
		scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		menuBar = new MenuBar();
		file = new Menu("File");
		help = new Menu("Help");

		MenuItem newFile = new MenuItem("New");
		newFile.setOnAction(new NewMenuListener());

		MenuItem saveFile = new MenuItem("Save");
		saveFile.setOnAction(new SaveMenuListener());

		MenuItem loadFile = new MenuItem("Open");
		loadFile.setOnAction(new LoadMenuListener());

		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(new QuitMenuListener());

		MenuItem about = new MenuItem("About");
		about.setOnAction(new AboutMenuListener());
		HBox box = new HBox();

		file.getItems().addAll(newFile,loadFile,saveFile);
		help.getItems().addAll(about,quit);
		menuBar.getMenus().addAll(file,help);
		box.getChildren().add(menuBar);
		rootNode.getChildren().addAll(box,scroller);

		stage = primaryStage;
		stage.show();
	}

	public class NewMenuListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			text.setText("");
			text.requestFocus();
		}
	}

	public class SaveMenuListener implements EventHandler<ActionEvent> {
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

	public class LoadMenuListener implements EventHandler<ActionEvent> {
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

	public class QuitMenuListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			primaryStage.close();
		}
	}

	public class AboutMenuListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("About");
			alert.setContentText("Text Editor 1.0\nCreated By: Ashwin Saxena");
			alert.showAndWait();
		}
	}
}