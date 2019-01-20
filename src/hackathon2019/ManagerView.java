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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ManagerView extends Application {
	private Boolean nameSet = false;
	private String name;
	private String path;
	private File selected_file;
	//private Mode newMode;
	
	public void start(Stage stage) throws Exception {
		
		displayMainPage(stage);
	}
	
	private void displayCustomizationMenu(Stage stage, ModeManager mm) {
		Mode newMode;
		//Boolean nameSet = false;
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
			// if(nameSet == true)
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
			name = nameTextInput.getText();
			if(name != null) {
				nameSet = true;
				try {
					mm.addMode(name);
				}catch(FileNotFoundException e1){
					e1.printStackTrace();
				}catch(UnsupportedEncodingException e1){
					e1.printStackTrace();
				}
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
			selected_file = fc.showOpenDialog(null);
			path = selected_file.getAbsolutePath();
			//String proccessname = proc_name.getText();
			//System.out.println(proccessname);
		});
		
		add_proc.setOnAction(e -> {
			String proccessname = proc_name.getText();
			if (path != null && proccessname != null) {
				Mode foundmode = mm.getMode(name);
				if (foundmode == null) {
					System.out.println("ERROR HERE");
					System.exit(1);
				}
				foundmode.add(proccessname, path, false);
			}else {
				System.out.println("File is not Valid or Name not set");
			}	
		});
		
//		ToggleGroup group1 = new ToggleGroup();
//		RadioButton applicationButton = new RadioButton("Application");
//		applicationButton.setToggleGroup(group1);
//		applicationButton.setSelected(true);
//		RadioButton webpageButton = new RadioButton("Webpage");
//		webpageButton.setToggleGroup(group1);
		Button addWebpage = new Button("Add Webpage");
		TextField web_url = new TextField();
		web_url.setPromptText("Enter webpage url.");
		TextField web_name = new TextField();
		web_name.setPromptText("Enter webpage name.");
		addWebpage.setOnAction(e -> {
			String url = web_url.getText();
			String webname = web_name.getText();
			if (url != null && webname != null) {
				Mode foundmode = mm.getMode(name);
				if (foundmode == null) {
					System.out.println("ERROR HERE");
					System.exit(1);
				}
				foundmode.add(webname, url, true);
			}else {
				System.out.println("URL not set or name not set");
			}
		});
		
		
		
		proc_name.setLayoutX(100);
		proc_name.setLayoutY(70);
		choosefile.setLayoutX(0);
		choosefile.setLayoutY(70);
		proc_name.setLayoutX(100);
		proc_name.setLayoutY(70);
		web_url.setLayoutX(0);
		web_url.setLayoutY(110);
		web_name.setLayoutX(170);
		web_name.setLayoutY(110);
		addWebpage.setLayoutX(325);
		addWebpage.setLayoutY(110);
		
		add_proc.setLayoutX(255);
		add_proc.setLayoutY(70);
//		applicationButton.setLayoutX(350);
//		applicationButton.setLayoutY(75);
//		webpageButton.setLayoutX(440);
//		webpageButton.setLayoutY(75);
		menuBp.getChildren().addAll(backButton, saveButton, nameText, nameTextInput, proccessText, choosefile, proc_name,
				add_proc, set_name, addWebpage, web_url,web_name);
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
			displayCustomizationMenu(stage, mm);
		});
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}
	
	public void setNameHandler(ActionEvent event, Mode toUpdateMode, TextField nameText) {
		String name = nameText.getText();
		if(name != null) {
			
			nameSet = true;
		}else {
			nameText.setPromptText("ENTER NAME BEFORE SET");
		}
	}
}
