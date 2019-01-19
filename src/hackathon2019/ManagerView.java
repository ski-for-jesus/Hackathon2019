package hackathon2019;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ManagerView extends Application {
	public void start(Stage stage) throws Exception {
		displayMainPage(stage);
	}
	
	private void displayCustomizationMenu(Stage stage) {
		Canvas menuCanvas = new Canvas(700, 500);
		BorderPane menuBp = new BorderPane(menuCanvas);
		Scene menuScene = new Scene(menuBp);
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			displayMainPage(stage);
		});
		menuBp.setTop(backButton);
		stage.setScene(menuScene);
	}
	
	private void displayMainPage(Stage stage) {
		BorderPane bp = new BorderPane();
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
		int x = 100;
		int y = 100;
		Canvas canvas = new Canvas(x, y);
		bp.setTop(menuPane);
		bp.setCenter(canvas);
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}
}
