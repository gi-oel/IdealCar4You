package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Fahrzeugparkmanager extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Fahrzeugparkmanager");
        stage.getIcons().add(new Image("logo.png"));

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu vehicles = new Menu("Fahrzeuge");
        menuBar.getMenus().add(vehicles);

        BorderPane rootLayout = new BorderPane(menuBar);
        stage.setScene(new Scene(rootLayout, 500, 300));
        stage.show();
    }
}
