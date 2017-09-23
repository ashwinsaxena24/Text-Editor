import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import listener.*;

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
		newFile.setOnAction(new NewMenuListener(text));

		MenuItem saveFile = new MenuItem("Save");
		saveFile.setOnAction(new SaveMenuListener(primaryStage,text));

		MenuItem loadFile = new MenuItem("Open");
		loadFile.setOnAction(new LoadMenuListener(primaryStage,text));

		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(new QuitMenuListener(primaryStage));

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

}