package hackathon2019;
 
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private Boolean nameSet = false;
	//private Mode newMode;
	
	public void start(Stage stage) throws Exception {
		
		displayMainPage(stage);
	}
	
	private void displayCustomizationMenu(Stage stage) {
		Mode newMode;
		//Boolean nameSet = false;
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
			// if(nameSet == true)
			System.out.println("PUT THE SAVE STUFF IN HERE");
			displayMainPage(stage);
		});
		Text nameText = new Text("Name: ");
		TextField nameTextInput = new TextField();
		nameTextInput.setPromptText("Enter mode name.");
		Button set_name = new Button("Set Name");
//		set_name.setOnAction(new EventHandler<ActionEvent>() -> {
//			@Override
//			public void handle(ActionEvent e) {
//				
//			}
//			String name = nameTextInput.getText();
//			if(name != null) {
//				newMode = new Mode(name);
//				nameSet = true;
//			}else {
//				nameTextInput.setPromptText("ENTER NAME BEFORE SET");
//			}
//		});
		set_name.setOnAction(e -> {
			String name = nameTextInput.getText();
			if(name != null) {
				nameSet = true;
			}else {
				nameTextInput.setPromptText("ENTER NAME BEFORE SET");
			}
		});
		
		
		
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
		set_name.setLayoutX(300);
		set_name.setLayoutY(30);
		
		// This is where the file chooser and new processes are added to the new mode
		TextField proc_name = new TextField();
		proc_name.setPromptText("Enter process name.");
		Button choosefile = new Button("Select Process");
		Button add_proc = new Button("Add Process");
		choosefile.setOnAction(add -> {
			FileChooser fc = new FileChooser();
			File selected_file = fc.showOpenDialog(null);
			String proccessname = proc_name.getText();
			if (selected_file != null && proccessname != null) {
				String path;
				path = selected_file.getAbsolutePath();
				String procName = proc_name.getText();
				//Process newproc = new Process(procName, path);
				//MANAGER.GETMODE>ADDPROCCCESS HERE!!!
				System.out.println(path);
			}else {
				System.out.println("File is not Valid or Name not set");
			}
		});
		
		proc_name.setLayoutX(100);
		proc_name.setLayoutY(70);
		choosefile.setLayoutX(0);
		choosefile.setLayoutY(70);
		proc_name.setLayoutX(100);
		proc_name.setLayoutY(70);
		add_proc.setLayoutX(255);
		add_proc.setLayoutY(70);
		menuBp.getChildren().addAll(backButton, saveButton, nameText, nameTextInput, proccessText, choosefile, proc_name,
				add_proc, set_name);
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
	
	public void setNameHandler(ActionEvent event, Mode toUpdateMode, TextField nameText) {
		String name = nameText.getText();
		if(name != null) {
			toUpdateMode = new Mode(name);
			nameSet = true;
		}else {
			nameText.setPromptText("ENTER NAME BEFORE SET");
		}
	}
}
