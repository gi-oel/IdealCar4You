package UI.Kundeberater.Fahrzeuge;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Fahrzeug;
import Infrasturcture.PersistencyService;
import UI.Fahrzeugparkmanager.DetailScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class FahrzeugListe {
    public static BorderPane list() {
        PersistencyService ps = new PersistencyService();
        System.out.println("Anzahl Fahrzeuge in liste: " + ps.getFahrzeuge().size());

        //Search bar
        VBox sucheUndListe = new VBox(10);
        TextField suche = new TextField();
        suche.setPromptText("Suchen...");
        sucheUndListe.getChildren().add(suche);
        VBox.setMargin(suche, new Insets(0, 5, 0, 0));
        suche.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println("Benutzer suche gestartet");
            }
        });

        //List
        ListView<String> vehicleListView = new ListView<>();
        ObservableList<String> vehiclesList = FXCollections.observableArrayList();
        sucheUndListe.getChildren().add(vehicleListView);

        //add buttons to right side
        VBox rightBox = new VBox(20);
        Button detailButton = new Button("Detailansicht");
        detailButton.setPrefWidth(100);
        detailButton.setDisable(true);
        //ins layout hinzufügen
        rightBox.getChildren().add(detailButton);
        rightBox.setPadding(new Insets(10, 10, 10, 10));
        rightBox.setAlignment(Pos.CENTER);

        //Edit / Detail event handler
        EventHandler<ActionEvent> detailEvent = actionEvent -> {
            FahrzeugDetail.detail(ps.getFahrzeug(vehicleListView.getSelectionModel().getSelectedIndex()));
        };
        detailButton.setOnAction(detailEvent);

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
            detailButton.setDisable(false);

            //Wenn der Benutzer 2 mal klickt
            if (click.getClickCount() == 2) {
                detailButton.fire();
            }
        });

        //root
        BorderPane rootLayout = new BorderPane();
        rootLayout.setCenter(sucheUndListe);
        rootLayout.setRight(rightBox);
        return rootLayout;
    }
}
