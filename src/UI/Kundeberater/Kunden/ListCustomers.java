package UI.Kundeberater.Kunden;

import Domain.Kunde.Kunde;
import Infrasturcture.PersistencyService;
import UI.Kundeberater.Kundenberater;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class ListCustomers {
    public static BorderPane list() {
        BorderPane root = new BorderPane();

        //Suche
        VBox sucheUndListe = new VBox(10);
        TextField suche = new TextField();
        suche.setPromptText("Suche...");
        suche.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(suche, new Insets(5, 0, 0, 0));
        sucheUndListe.getChildren().add(suche);

        //Liste
        ListView<String> customerListView = new ListView<>();
        customerListView.setPlaceholder(new Label("Keine Kunden"));
        sucheUndListe.getChildren().add(customerListView);

        //Alle kunden auflisten
        PersistencyService ps = new PersistencyService();
        for (Kunde kunde : ps.getKunden()) {
            customerListView.getItems().add(kunde.getVorname() + " " + kunde.getName() + ", Geburtstag: " + kunde.getGeburtsdatum().format(DateTimeFormatter.ofPattern("d.M.u")) + ", Wohnort: " + kunde.getWohnort());
        }

        //Knoepfe
        VBox buttons = new VBox(20);
        buttons.setPadding(new Insets(10, 10, 10, 10));
        Button detailAnsicht = new Button("Detailansicht");
        Button loeschen = new Button("Löschen");
        Button bearbeiten = new Button("Bearbeiten");
        detailAnsicht.setPrefWidth(100);
        buttons.setAlignment(Pos.CENTER);
        loeschen.setPrefWidth(detailAnsicht.getPrefWidth());
        bearbeiten.setPrefWidth(detailAnsicht.getPrefWidth());
        bearbeiten.setDisable(true);
        detailAnsicht.setDisable(true);
        loeschen.setDisable(true);
        buttons.getChildren().addAll(detailAnsicht, bearbeiten, loeschen);

        //Wenn ein kunde ausgewählt wird
        customerListView.setOnMouseClicked(event -> {
            Kunde selKunde = ps.getkunde(customerListView.getSelectionModel().getSelectedIndex());
            System.out.println("Benutzer wählte: " + selKunde.getVorname() + " " + selKunde.getName());
            loeschen.setDisable(false);
            bearbeiten.setDisable(false);
            detailAnsicht.setDisable(false);
        });

        EventHandler<ActionEvent> detailAction = actionEvent -> {
            int index = customerListView.getSelectionModel().getSelectedIndex();
            Kunde kunde = ps.getkunde(index);
            Stage newStage = new Stage();
            newStage.setScene(new Scene(KundeDetail.detail(index, kunde, newStage)));
            newStage.show();
        };
        //Kunde bearbeiten
        bearbeiten.setOnAction(detailAction);
        //kunden detail, ist dasselbe, damit der kunde nicht verwirrt ist, ob es keine detail view gibt
        detailAnsicht.setOnAction(detailAction);

        //Löschen
        loeschen.setOnAction(actionEvent -> {
            int index = customerListView.getSelectionModel().getSelectedIndex();
            Kunde kunde = ps.getkunde(index);
            Alert really = new Alert(Alert.AlertType.CONFIRMATION);

        });

        root.setCenter(sucheUndListe);
        root.setRight(buttons);
        return root;
    }
}
