package UI.Kundeberater.Fahrzeuge;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Fahrzeug;
import Infrasturcture.PersistencyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
        VBox.setMargin(suche, new Insets(5, 0, 0, 0));

        //List
        ListView<String> vehicleListView = new ListView<>();
        ObservableList<String> vehiclesList = FXCollections.observableArrayList();
        vehicleListView.setPlaceholder(new Label("Keine Fahrzeuge"));
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

        //Wenn der Benutzer ein Fahrzeug sucht
        suche.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println("Benutzer suche gestartet");
                vehicleListView.getItems().clear(); //alles entfernen
                for (Fahrzeug fahrzeugObj : ps.getFahrzeuge()) {
                    if (fahrzeugObj.getMarke().toLowerCase().contains(suche.getText().toLowerCase()) || fahrzeugObj.getModel().toLowerCase().contains(suche.getText().toLowerCase()) || fahrzeugObj.getColor().toLowerCase().contains(suche.getText().toLowerCase())) {
                        String vehicleType = fahrzeugObj instanceof Auto ? "Auto: " : "Transporter: ";
                        vehicleListView.getItems().add(vehicleType + fahrzeugObj.getMarke() + " " + fahrzeugObj.getModel() + ", Farbe: " + fahrzeugObj.getColor() + ", Erstzulassung: " + fahrzeugObj.getErstzulassung().format(DateTimeFormatter.ofPattern("d.M.u")));
                    }
                }
                detailButton.setDisable(true); //Deaktiviert knopf wieder
            }
        });

        //root
        BorderPane rootLayout = new BorderPane();
        rootLayout.setCenter(sucheUndListe);
        rootLayout.setRight(rightBox);
        return rootLayout;
    }
}
