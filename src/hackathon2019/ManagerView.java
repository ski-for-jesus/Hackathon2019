package hackathon2019;
 
import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ManagerView extends Application {
	
	public void start(Stage stage) throws Exception {
		
		displayMainPage(stage);
	}
	
	private void displayCustomizationMenu(Stage stage) {
		Mode newMode;
		Canvas menuCanvas = new Canvas(700, 500);
		AnchorPane menuBp = new AnchorPane(menuCanvas);
		Scene menuScene = new Scene(menuBp);
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			displayMainPage(stage);
		});
		
		//This is where the object is created 
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> {
			//SAVE STUFF HERE
			//Check that Mode is valid (name input) and at least one proc in the object
			System.out.println("PUT THE SAVE STUFF IN HERE");
			displayMainPage(stage);
		});
		Text nameText = new Text("Name: ");
		TextField nameTextInput = new TextField();
		nameTextInput.setPromptText("Enter mode name.");
		
		Text proccessText = new Text("Proccesses:");
		nameTextInput.setLayoutX(50);
		nameTextInput.setLayoutY(30);
		nameTextInput.setPrefColumnCount(20);
		nameTextInput.setPrefHeight(1);
		nameText.setLayoutX(10);
		nameText.setLayoutY(50);
		backButton.setLayoutX(0);
		backButton.setLayoutY(0);
		saveButton.setLayoutX(0);
		saveButton.setLayoutY(350);
		proccessText.setLayoutX(10);
		proccessText.setLayoutY(400);
		
		// This is where the file chooser and new processes are added to the new mode
		TextField proc_name = new TextField();
		proc_name.setPromptText("Enter process name.");
		Button choosefile = new Button("Select Process");
		choosefile.setOnAction(add -> {
			FileChooser fc = new FileChooser();
			File selected_file = fc.showOpenDialog(null);
			
			if (selected_file != null) {
				String path;
				path = selected_file.getAbsolutePath();
				String procName = proc_name.getText();
;				Process newproc = new Process(procName, path);
				System.out.println(path);
			}else {
				System.out.println("File is not Valid");
			}
		});
		
		proc_name.setLayoutX(100);
		proc_name.setLayoutY(70);
		choosefile.setLayoutX(0);
		choosefile.setLayoutY(70);
		menuBp.getChildren().addAll(backButton, saveButton, nameText, nameTextInput, proccessText, choosefile, proc_name);
		stage.setScene(menuScene);
	}
	
	private void displayMainPage(Stage stage) {
		BorderPane bp = new BorderPane();
		TilePane tp = new TilePane();
		Menu createM = new Menu("Create");
		Menu deleteM = new Menu("Delete");
		MenuItem newPref = new MenuItem("New Preference Set");
		newPref.setOnAction(e -> {
			displayCustomizationMenu(stage);
		});
		createM.getItems().add(newPref);
		MenuBar menuPane = new MenuBar();
		menuPane.getMenus().add(createM);
		menuPane.getMenus().add(deleteM);
		bp.setCenter(tp);
		bp.setTop(menuPane);
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}
}
