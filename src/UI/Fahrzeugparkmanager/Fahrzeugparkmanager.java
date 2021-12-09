package UI.Fahrzeugparkmanager;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Transporter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Fahrzeugparkmanager extends Application {
    @Override
    public void start(Stage managerStage) {
        managerStage.setTitle("Fahrzeugparkmanager");
        managerStage.getIcons().add(new Image("logo.png"));

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu vehiclesMenu = new Menu("Fahrzeuge");
        MenuItem createAuto = new MenuItem("Auto erfassen");
        MenuItem createTransporter = new MenuItem("Transporter erfassen");
        MenuItem list = new MenuItem("Fahrzeuge auflisten");
        Menu applikation = new Menu("Applikation");
        MenuItem close = new MenuItem("Schliessen");
        MenuItem logout = new MenuItem("Ausloggen");
        applikation.getItems().addAll(logout, close);
        vehiclesMenu.getItems().addAll(createAuto, createTransporter, list);
        menuBar.getMenus().addAll(applikation, vehiclesMenu);

        //Root layout definieren
        BorderPane rootLayout = new BorderPane();
        rootLayout.setTop(menuBar);
        rootLayout.setCenter(ListScene.listCars(list));

        //User logt sich aus
        logout.setOnAction(actionEvent -> {
            managerStage.close();
        });
        //applikation schliessen
        close.setOnAction(actionEvent -> {
            Runtime.getRuntime().exit(0);
        });
        //Autos auflisen
        list.setOnAction(event -> {
            rootLayout.setCenter(ListScene.listCars(list));
        });
        //Autos erfassen
        createAuto.setOnAction(action -> {
            rootLayout.setCenter(CreateScene.createVehicle(Auto.class, list));
            managerStage.setWidth(700);
            managerStage.setHeight(500);
            managerStage.setMinWidth(500);
            managerStage.setMinHeight(500);
        });
        createTransporter.setOnAction(event -> {
            rootLayout.setCenter(CreateScene.createVehicle(Transporter.class, list));
            managerStage.setWidth(700);
            managerStage.setHeight(450);
            managerStage.setMinHeight(500);
            managerStage.setMinHeight(450);
        });

        managerStage.setScene(new Scene(rootLayout, 500, 300));
        managerStage.show();
    }
}
