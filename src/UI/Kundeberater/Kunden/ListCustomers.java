package UI.Kundeberater.Kunden;

import Domain.Kunde.Kunde;
import Infrasturcture.PersistencyService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListCustomers {
    public static BorderPane list(MenuItem menuItem) {
        BorderPane root = new BorderPane();
        List<Kunde> theCustomerList = new ArrayList<>();

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
            theCustomerList.add(kunde);
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

        EventHandler<ActionEvent> detailAction = actionEvent -> {
            int index = ps.getKunden().indexOf(theCustomerList.get(customerListView.getSelectionModel().getSelectedIndex()));
            Kunde kunde = ps.getkunde(index);
            Stage newStage = new Stage();
            newStage.setScene(new Scene(KundeDetail.detail(index, kunde, newStage)));
            newStage.getIcons().add(new Image("logo.png"));
            newStage.show();
        };
        //Kunde bearbeiten
        bearbeiten.setOnAction(detailAction);
        //kunden detail, ist dasselbe, damit der kunde nicht verwirrt ist, ob es keine detail view gibt
        detailAnsicht.setOnAction(detailAction);

        //Wenn ein kunde ausgewählt wird
        customerListView.setOnMouseClicked(event -> {
            Kunde selKunde = theCustomerList.get(customerListView.getSelectionModel().getSelectedIndex());
            System.out.println("Benutzer wählte: " + selKunde.getVorname() + " " + selKunde.getName());
            loeschen.setDisable(false);
            bearbeiten.setDisable(false);
            detailAnsicht.setDisable(false);

            //Wenn doppelklick
            if (event.getClickCount() == 2) {
                detailAnsicht.fire();
            }
        });

        //Löschen
        loeschen.setOnAction(actionEvent -> {
            //Fragen ob wirklich löschen
            int index = ps.getKunden().indexOf(theCustomerList.get(customerListView.getSelectionModel().getSelectedIndex()));
            Kunde kunde = ps.getkunde(index);
            Alert really = new Alert(Alert.AlertType.CONFIRMATION);
            really.setTitle("Kunde löschen");
            really.setContentText("Wollen Sie den Kunden " + kunde.getVorname() + " " + kunde.getName() + " wirklich löschen?");

            //Wenn er bestätigt
            Optional<ButtonType> result = really.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    ps.deleteKunde(kunde);
                    menuItem.fire();
                } catch (IOException e) {
                    Alert alarm = new Alert(Alert.AlertType.ERROR);
                    alarm.setTitle("Fehler beim löschen");
                    alarm.setContentText("Der Kunde konnte nicht gelöscht werden!");
                    alarm.showAndWait();
                }
            }
        });

        //wenn der nutzer einen kunden sucht
        suche.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                customerListView.getItems().clear(); //Alle kunden löschen aus jetziger liste
                theCustomerList.clear();
                detailAnsicht.setDisable(true);
                loeschen.setDisable(true);
                bearbeiten.setDisable(true);
                for (Kunde kunde : ps.getKunden()) {
                    if (kunde.getName().toLowerCase().contains(suche.getText().toLowerCase()) || kunde.getVorname().toLowerCase().contains(suche.getText().toLowerCase()) || kunde.getEmail().toLowerCase().contains(suche.getText().toLowerCase()) || kunde.getWohnort().toLowerCase().contains(suche.getText().toLowerCase()) || kunde.getStrasseUndNr().toLowerCase().contains(suche.getText().toLowerCase())) {
                        theCustomerList.add(kunde);
                        customerListView.getItems().add(kunde.getVorname() + " " + kunde.getName() + ", Geburtstag: " + kunde.getGeburtsdatum().format(DateTimeFormatter.ofPattern("d.M.u")) + ", Wohnort: " + kunde.getWohnort());
                    }
                }
            }
        });

        root.setCenter(sucheUndListe);
        root.setRight(buttons);
        return root;
    }
}
