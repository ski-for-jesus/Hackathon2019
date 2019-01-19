package hackathon2019;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagerView extends Application {
	public void start(Stage stage) throws Exception {
		displayMainPage(stage);
	}
	
	private void displayCustomizationMenu(Stage stage) {
		Canvas menuCanvas = new Canvas(700, 500);
		AnchorPane menuBp = new AnchorPane(menuCanvas);
		Scene menuScene = new Scene(menuBp);
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			displayMainPage(stage);
		});
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> {
			//SAVE STUFF HERE
			System.out.println("PUT THE SAVE STUFF IN HERE");
			displayMainPage(stage);
		});
		Text nameText = new Text("Name: ");
		TextArea nameTextInput = new TextArea("THIS IS A TEST");
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
		menuBp.getChildren().addAll(backButton, saveButton, nameText, nameTextInput, proccessText);
		stage.setScene(menuScene);
	}
	
	private void displayMainPage(Stage stage) {
		stage.setTitle("Shortcut to Shortcuts");
		Canvas mainCanvas = new Canvas(400, 100);
		mainCanvas.requestFocus();
		BorderPane bp = new BorderPane(mainCanvas);
		ToolBar toolBar = new ToolBar();
		Button create = new Button("Create");
		Button delete = new Button("Delete");
		toolBar.getItems().addAll(create, delete);
		bp.setTop(toolBar);
		create.setOnAction(e -> {
			displayCustomizationMenu(stage);
		});
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}
}
