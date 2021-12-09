package UI.Fahrzeugparkmanager;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Fahrzeug;
import Domain.Fahrzeug.Transporter;
import Infrasturcture.PersistencyService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

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
        rootLayout.setCenter(listCars());

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
            rootLayout.setCenter(listCars());
        });
        //Autos erfassen
        createAuto.setOnAction(action -> {
            rootLayout.setCenter(VehicleScenes.createVehicle(Auto.class));
            managerStage.setWidth(700);
            managerStage.setHeight(500);
            managerStage.setMinWidth(500);
            managerStage.setMinHeight(500);
        });
        createTransporter.setOnAction(event -> {
            rootLayout.setCenter(VehicleScenes.createVehicle(Transporter.class));
            managerStage.setWidth(700);
            managerStage.setHeight(450);
            managerStage.setMinHeight(500);
            managerStage.setMinHeight(450);
        });

        managerStage.setScene(new Scene(rootLayout, 500, 300));
        managerStage.show();
    }

    private BorderPane listCars() {
        //Instance for persistency service
        PersistencyService ps = new PersistencyService();
        System.out.println("Anzahl Fahrzeuge in liste: " + ps.getFahrzeuge().size());

        //List
        ListView<String> vehicleListView = new ListView<>();
        ObservableList<String> vehiclesList = FXCollections.observableArrayList();

        //add buttons to right side
        VBox rightBox = new VBox(20);
        Button deleteButton = new Button("Löschen");
        Button detailButton = new Button("Detailansicht");
        Button editButton = new Button("Bearbeiten");
        //Knopfweite wird gleich gemacht wie längster knopf und deaktivieren
        detailButton.setPrefWidth(100);
        detailButton.setDisable(true);
        deleteButton.setMinWidth(detailButton.getPrefWidth());
        deleteButton.setDisable(true);
        editButton.setMinWidth(detailButton.getPrefWidth());
        editButton.setDisable(true);
        //ins layout hinzufügen
        rightBox.getChildren().add(detailButton);
        rightBox.getChildren().add(editButton);
        rightBox.getChildren().add(deleteButton);
        rightBox.setPadding(new Insets(10, 10, 10, 10));
        rightBox.setAlignment(Pos.CENTER);

        //Alle Fahrzeuge in Liste einfügen
        for (int i = 0; i < ps.getFahrzeuge().size(); i++) {
            Fahrzeug vehicle = ps.getFahrzeug(i);
            String vehicleType = vehicle instanceof Auto ? "Auto" : "Transporter";
            vehiclesList.add(vehicleType + ": " + vehicle.getMarke() + " " + vehicle.getModel() + ", Farbe: " + vehicle.getColor() + ", Erstzulassung: " + vehicle.getErstzulassung().format(DateTimeFormatter.ofPattern("d.M.u")));
        }
        vehicleListView.setItems(vehiclesList);

        //root
        BorderPane rootLayout = new BorderPane();
        rootLayout.setCenter(vehicleListView);
        rootLayout.setRight(rightBox);
        return rootLayout;
    }
}
