package UI;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class Fahrzeugparkmanager extends Application {
    @Override
    public void start(Stage managerStage) throws Exception {
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
            rootLayout.setCenter(createCar(Auto.class));
        });
        createTransporter.setOnAction(event -> {
            rootLayout.setCenter(createCar(Transporter.class));
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
        Button newButton = new Button("Neu");
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

    private BorderPane createCar(Class typ) {
        //layout
        BorderPane rootLayout = new BorderPane();
        GridPane grid = new GridPane();
        Text titel = new Text();
        titel.setStyle("-fx-font: 24 arial;");
        titel.setTextAlignment(TextAlignment.CENTER);
        if (typ == Auto.class) {
            titel.setText("Auto erfassen");
        } else if (typ == Transporter.class) {
            titel.setText("Transporter erfassen");
        }
        rootLayout.setTop(titel);
        BorderPane.setAlignment(titel, Pos.CENTER);

        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(45);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(45);
        grid.getColumnConstraints().addAll(column0, column1, column2);
        Insets insertGroup = new Insets(5, 0, 0, 0);
        Insets groupSeparator = new Insets(10, 0, 0, 0);

        //Marke
        Text markeText = new Text("Marke:");
        grid.add(markeText, 0, 1);
        TextField marke = new TextField();
        GridPane.setMargin(marke, insertGroup);
        marke.setPromptText("Marke");
        grid.add(marke, 0, 2);

        //Model
        Text modelText = new Text("Model:");
        GridPane.setMargin(modelText, groupSeparator);
        grid.add(modelText, 0, 3);
        TextField model = new TextField();
        grid.add(model, 0, 4);
        GridPane.setMargin(model, insertGroup);

        //
        rootLayout.setCenter(grid);
        return rootLayout;
    }
}
