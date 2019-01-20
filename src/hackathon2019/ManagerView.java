package hackathon2019;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
			try {
				displayMainPage(stage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//This is where the object is created 
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> {
			//SAVE STUFF HERE
			//Check that Mode is valid (name input) and at least one proc in the object
			System.out.println("PUT THE SAVE STUFF IN HERE");
			try {
				displayMainPage(stage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
				// CHECK THE THIRD ARGUMENT OF CALL BELOW
				Process newproc = new Process(procName, path, false);
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
	
	private void displayMainPage(Stage stage) throws FileNotFoundException, UnsupportedEncodingException {
		stage.setTitle("Shortcut to Shortcuts");
		Canvas mainCanvas = new Canvas(400, 100);
		BorderPane bp = new BorderPane(mainCanvas);
		HBox hb = new HBox();
		hb.setPadding(new Insets(8));
		hb.setPrefHeight(90);
		hb.setPrefWidth(400);
		bp.setCenter(hb);
		mainCanvas.requestFocus();
		ModeManager mm = new ModeManager();
		List<String> list = mm.getNames();
		if(!list.isEmpty()) {
			for(String name: list) {
				Button newButton = new Button(name);
				newButton.setOnAction(e-> {
					mm.execute(name);
				});
				hb.getChildren().add(newButton);				
			}
		}
		
		ToolBar toolBar = new ToolBar();
		Button create = new Button("Create");
		Button edit = new Button("Edit");
		Button back = new Button("Back");
		Button delete = new Button("Delete");
		toolBar.getItems().addAll(create, edit, delete);
		bp.setTop(toolBar);
		edit.setOnAction(e -> {
			toolBar.getItems().removeAll(create, edit, delete);
			toolBar.getItems().add(back);
			if(!list.isEmpty()) {
				for(String name: list) {
					Button newButton = new Button(name);
					newButton.setOnAction(e1-> {
						//add edit functionality here
					});		
					toolBar.getItems().addAll(newButton);
				}
			}
			back.setOnAction(e1 -> {
				toolBar.getItems().clear();
				toolBar.getItems().addAll(create, edit, delete);
			});
		});
		create.setOnAction(e -> {
			displayCustomizationMenu(stage);
		});
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}
}
