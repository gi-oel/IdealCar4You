package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Kundenberater extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Kundenberater");
        stage.getIcons().add(new Image("logo.png"));

        //MenuBar
        MenuBar menuBar = new MenuBar();
        Menu vehicles = new Menu("Fahrzeuge");
        Menu customers = new Menu("Kunden");
        menuBar.getMenus().addAll(vehicles, customers);

        BorderPane rootLayout = new BorderPane(menuBar);
        stage.setScene(new Scene(rootLayout, 500, 300));
        stage.show();
    }
}
