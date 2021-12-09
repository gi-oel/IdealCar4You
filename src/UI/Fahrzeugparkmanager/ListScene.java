package UI.Fahrzeugparkmanager;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Fahrzeug;
import Infrasturcture.PersistencyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ListScene {
    //Fahrzeuge auflisten
    public static BorderPane listCars(MenuItem menuItem) {
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

        //Edit / Detail event handler
        EventHandler<ActionEvent> detailEvent = actionEvent -> {
            Stage detailStage = new Stage();
            detailStage.setTitle("Detailansicht / Bearbeiten");
            detailStage.getIcons().add(new Image("logo.png"));
            detailStage.setScene(new Scene(DetailScene.editScene(ps.getFahrzeug(vehicleListView.getSelectionModel().getSelectedIndex()), vehicleListView.getSelectionModel().getSelectedIndex()), 500, 450));
            detailStage.show();
        };
        detailButton.setOnAction(detailEvent);
        editButton.setOnAction(detailEvent);

        //Alle Fahrzeuge in Liste einfügen
        for (int i = 0; i < ps.getFahrzeuge().size(); i++) {
            Fahrzeug vehicle = ps.getFahrzeug(i);
            String vehicleType = vehicle instanceof Auto ? "Auto" : "Transporter";
            vehiclesList.add(vehicleType + ": " + vehicle.getMarke() + " " + vehicle.getModel() + ", Farbe: " + vehicle.getColor() + ", Erstzulassung: " + vehicle.getErstzulassung().format(DateTimeFormatter.ofPattern("d.M.u")));
        }
        vehicleListView.setItems(vehiclesList);

        //Wenn auf ein Eintrag geklickt wird, Knöpfe aktivieren
        vehicleListView.setOnMouseClicked(click -> {
            System.out.println("Benutzer wählte " + vehicleListView.getSelectionModel().getSelectedItem());
            //Knöpfe aktivieren
            deleteButton.setDisable(false);
            editButton.setDisable(false);
            detailButton.setDisable(false);

            //Wenn der Benutzer 2 mal klickt
            if (click.getClickCount() == 2) {
                detailButton.fire();
            }
        });

        //Wenn auf löschen geklickt wird
        deleteButton.setOnAction(event -> {
            Fahrzeug toDelete = ps.getFahrzeug(vehicleListView.getSelectionModel().getSelectedIndex());
            Alert bestaetigung = new Alert(Alert.AlertType.CONFIRMATION);
            bestaetigung.setHeaderText("Löschen bestätigen");
            bestaetigung.setContentText("Wollen Sie das Fahrzeug " + toDelete.getMarke() + " " + toDelete.getModel() + " wirklich löschen?");
            Optional<ButtonType> result = bestaetigung.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    ps.deleteFahrzeug(toDelete);
                    menuItem.fire();
                } catch (IOException e) {
                    Alert fail = new Alert(Alert.AlertType.ERROR);
                    fail.setHeaderText("Löschen fehlgeschlagen");
                    fail.setContentText(toDelete.getMarke() + " " + toDelete.getModel() + " konnte nicht gelöscht werden!");
                    fail.showAndWait();
                }
            }
        });


        //root
        BorderPane rootLayout = new BorderPane();
        rootLayout.setCenter(vehicleListView);
        rootLayout.setRight(rightBox);
        return rootLayout;
    }
}
