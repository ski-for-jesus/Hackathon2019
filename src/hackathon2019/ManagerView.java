package hackathon2019;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ManagerView extends Application {
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		TilePane tp = new TilePane();
		Menu createM = new Menu("Create");
		Menu deleteM = new Menu("Delete");
		MenuItem newPref = new MenuItem();
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
