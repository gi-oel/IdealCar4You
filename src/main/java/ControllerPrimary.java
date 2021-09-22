import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.java.fahrzeuge.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ControllerPrimary {
    //Eine instanz für die Methods klasse wird erstellt
    ControllerMethods method = new ControllerMethods();

    public void clickedFahrzeugparkmanager(ActionEvent actionEvent) throws IOException {
        //Es wird eine neue Stage geöffnet für den Fahrzeugparkmanager
        method.changeStageLogin("../fxml/fahrzeugVerwalten.fxml", "Fahrzeugparkmanager", 650, 450, 600, 400);
        ControllerMethods.getVehicles();
    }

    public void clickedKundenberater(ActionEvent actionEvent) {
        //Eine neue Stage wird geöffnet und der user übergeben
        method.changeStageLogin("../fxml/kundenberater.fxml", "Kundenberater", 500, 300, 450, 250);
    }

    public void closeButton(ActionEvent actionEvent) {
        //Die Applikation wird geschlossen
        ControllerVariables.closeApplication();
    }

    public void zurueck(ActionEvent actionEvent) {
        //Öffnen der Login Stage
        ControllerVariables.getStageLogin().show();
        ControllerVariables.getStageUser().hide();
    }

    public void fahrzeugErfassen(ActionEvent actionEvent) throws IOException {
        //die scene Fahrzeug auswahl wird gesetzt
        ControllerMethods.changeScene("../fxml/fahrzeugAuswahl.fxml");

    }

    public void zurueckScene(ActionEvent actionEvent) {
        //Es wird die Stage aufgerufen und die scene des users gesetzt
        ControllerVariables.getStageUser().setScene(ControllerVariables.getSceneUser());
    }

    public void fahrzeugAusgewaehlt(ActionEvent actionEvent) throws IOException {
        //Die Id des Knopfes wird überprüft
        switch (((Control) actionEvent.getSource()).getId()) {
            //Wenn der User den Knopf Auto gedrückt hat wird die neue scene aufgerufen und die Methode fahrzeugErfassen aufgerufen
            case "btnAuto":
                System.out.println("Auto wurde ausgewählt");
                ControllerMethods.changeScene("../fxml/fahrzeugErfassen.fxml");
                ControllerMethods.fahrzeugErfassen(1);
                break;
            //Wenn der User den Knopf Motorrad geklickt hat wird die neue scene aufgerufen und die Methode fahrzeugErfassen aufgerufen
            case "btnMotorrad":
                System.out.println("Motorrad wurde ausgewählt");
                ControllerMethods.changeScene("../fxml/fahrzeugErfassen.fxml");
                ControllerMethods.fahrzeugErfassen(2);
                break;
            //Wenn der User den Knopf Transporter ausgewählt hat wird die neue scene aufgerufen und die Methode fahrzeugErfassen aufgerufen
            case "btnTransporter":
                System.out.println("Transporter wurde ausgewählt");
                ControllerMethods.changeScene("../fxml/fahrzeugErfassen.fxml");
                ControllerMethods.fahrzeugErfassen(3);
                break;
            //Falls es aus irgend einem Grund einen Fehler gab
            default:
                System.out.println("Ooops, an Error occured");
                break;
        }
    }

    public void fahrzeugVerwalten(ActionEvent actionEvent) throws IOException {
        //Die Scene wird geändert zu Fahrzeug erfassen
        ControllerMethods.changeScene("../fxml/fahrzeugVerwalten.fxml");
        ControllerMethods.getVehicles();
    }

    public void fahrzeugWirdErfasst(ActionEvent actionEvent) throws IOException {
        ControllerMethods.saveVehicle();
        ControllerMethods.changeScene("../fxml/fahrzeugVerwalten.fxml");
        ControllerMethods.getVehicles();
    }

    public void kundeVerwalten(ActionEvent actionEvent) throws IOException {
        ControllerMethods.changeScene("../fxml/kundeVerwalten.fxml");
        ControllerMethods.getCustomers();
    }

    public void vermietungVerwalten(ActionEvent actionEvent) throws IOException {
        ControllerMethods.changeScene("../fxml/vermietungenVerwalten.fxml");
        ControllerMethods.getVermietungen();
    }

    public void vermietungBearbeiten(ActionEvent actionEvent) {
    }

    public void neueVermietung(ActionEvent actionEvent) throws IOException {
        ControllerMethods.changeScene("../fxml/vermietungErfassen.fxml");
        ComboBox status = (ComboBox) ControllerVariables.getSceneAuswahl().lookup("#selectStatus");

        String[] statusArr = {"Offen", "Bezahlt", "Abgeschlossen"};
        status.getItems().addAll(statusArr);
        status.setValue(statusArr[0]);
    }

    public void vermietungLoeschen(ActionEvent actionEvent) {
        try {
            //Listen werden instanziert
            JsonController jsonController;
            List<Vermietung> vermietungen = new ArrayList<>();

            //ObjectMapper wird instanziert
            ObjectMapper objectMapper = new ObjectMapper();

            //Daten werden ausgelesen
            jsonController = ControllerMethods.getData();

            //Listen werden aktualisiert
            vermietungen.addAll(jsonController.getVermietungen());

            ListView vermietung = (ListView) ControllerVariables.getSceneAuswahl().lookup("#vermietungListe");
            vermietungen.remove(vermietung.getSelectionModel().getSelectedIndex());

            //Die Listen werden im Json Controller gespeichert
            jsonController.setVermietungen(vermietungen);
            //Datei wird aktualisiert
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Die Liste wird neu geladen
            vermietung.getItems().remove(vermietung.getSelectionModel().getSelectedIndex());
        } catch (Exception e) {

        }
    }

    public void kundeErfassen(ActionEvent actionEvent) throws IOException {
        ControllerMethods.changeScene("../fxml/kundeErfassen.fxml");
    }

    public void kundeSpeichern(ActionEvent actionEvent) throws IOException {
        ControllerMethods.saveCustomer();
        ControllerMethods.changeScene("../fxml/kundeVerwalten.fxml");
        ControllerMethods.getCustomers();
    }

    public void kundeBearbeiten(ActionEvent actionEvent) throws IOException {
        ListView kunden = (ListView) ControllerVariables.getSceneAuswahl().lookup("#lstKunde");

        ControllerMethods.kundeBearbeiten(kunden);
    }

    public void kundeLoeschen(ActionEvent actionEvent) {
        try {
            //Listen werden instanziert
            JsonController jsonController;
            List<Fahrzeug> fahrzeuge = new ArrayList<>();
            List<Kunde> kunde = new ArrayList<>();

            //ObjectMapper wird instanziert
            ObjectMapper objectMapper = new ObjectMapper();

            //Daten werden ausgelesen
            jsonController = objectMapper.readValue(Paths.get("daten.json").toFile(), JsonController.class);

            //Listen werden aktualisiert
            fahrzeuge.addAll(jsonController.getVehicles());
            kunde.addAll(jsonController.getKunden());

            ListView kunden = (ListView) ControllerVariables.getSceneAuswahl().lookup("#lstKunde");
            kunde.remove(kunden.getSelectionModel().getSelectedIndex());

            //Die Listen werden im Json Controller gespeichert
            jsonController.setKunden(kunde);
            jsonController.setVehicles(fahrzeuge);
            //Datei wird aktualisiert
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Die Liste wird neu geladen
            kunden.getItems().remove(kunden.getSelectionModel().getSelectedIndex());
        } catch (Exception e) {

        }

    }

    public void kundeAusgewaehlt(MouseEvent mouseEvent) {
        //Die Buttons werden instanziert
        Button bearbeiten = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnKundeBearbeiten");
        Button loeschen = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnKundeLoeschen");
        //Die Knöpfe werden aktiviert
        loeschen.setDisable(false);
        bearbeiten.setDisable(false);
    }

    public void kundeAktualisieren(ActionEvent actionEvent) {
        //Die Felder werden instanziert
        TextField txtVorname = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtVorname");
        TextField txtNachname = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtNachname");
        TextField txtHausnummer = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtHausnummer");
        TextField txtStrasse = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtStrasse");
        Spinner numPLZ = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numPLZ");
        TextField txtWohnort = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtWohnort");
        TextField txtTelefon = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtTelefon");
        TextField txtEmail = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtEmail");
        DatePicker datGeburt = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datGeburt");

        //Das Geburtsdatum und die Postleitzahl wird als String gespeichert
        int plz = (int) numPLZ.getValue();
        String txtGeburt = null;
        try {
            //Das eingegebene Datum wird als String gespeichert
            txtGeburt = datGeburt.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            System.out.println("Fehler beim Geburtsdatum");
        }

        //Die Listen werden erstellt und objekte erstellt
        ObjectMapper objectMapper = new ObjectMapper();
        JsonController jsonController;
        List<Kunde> kunden = new ArrayList<>();

        try {
            //Die Daten werden ausgelesen und in die Listen gespeichert
            jsonController = ControllerMethods.getData();
            kunden.addAll(jsonController.getKunden());

            //Der Kunde wird bearbeitet
            Kunde derKunde = kunden.get(ControllerVariables.getSelectedIndex());

            //Die neuen Daten werden gespeichert
            derKunde.setGeburtsdatum(txtGeburt);
            derKunde.setPlz(plz);
            derKunde.setEmail(txtEmail.getText());
            derKunde.setVorname(txtVorname.getText());
            derKunde.setNachname(txtNachname.getText());
            derKunde.setHausnummer(txtHausnummer.getText());
            derKunde.setStrasse(txtStrasse.getText());
            derKunde.setWohnort(txtWohnort.getText());
            derKunde.setTelefon(txtTelefon.getText());

            //Der Kunde in der Liste wird aktualisiert
            kunden.set(ControllerVariables.getSelectedIndex(), derKunde);

            //Die Datei wird aktualisiert
            jsonController.setKunden(kunden);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Die Kunden werden angezeigt
            ControllerMethods.changeScene("../fxml/kundeVerwalten.fxml");
            ControllerMethods.getCustomers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fahrzeugBearbeiten(ActionEvent actionEvent) throws IOException {
        ListView fahrzeuge = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listVehicles");

        ControllerMethods.fahrzeugBearbeiten(fahrzeuge);
    }

    public void fahrzeugLoeschen(ActionEvent actionEvent) {
        try {
            //Listen werden instanziert
            List<Fahrzeug> fahrzeuge = new ArrayList<>();

            //Daten werden ausgelesen
            JsonController jsonController = ControllerMethods.getData();

            //Listen werden aktualisiert
            fahrzeuge.addAll(jsonController.getVehicles());

            //Das ausgewählte Fahrzeug wird als nicht mehr Verfügbar markiert
            ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listVehicles");
            Fahrzeug geloeschtesFahrzeug = fahrzeuge.get(listView.getSelectionModel().getSelectedIndex());
            geloeschtesFahrzeug.setNichtVerfuegbar(false);
            fahrzeuge.set(listView.getSelectionModel().getSelectedIndex(), geloeschtesFahrzeug);

            //Die Listen werden im Json Controller gespeichert
            jsonController.setVehicles(fahrzeuge);
            //Datei wird aktualisiert
            //ObjectMapper wird instanziert
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Die Liste wird neu geladen
            String typ;
            switch (geloeschtesFahrzeug.getType()) {
                case 1:
                    typ = "Auto";
                    break;
                case 2:
                    typ = "Motorrad";
                    break;
                default:
                    typ = "Transporter";
            }
            listView.getItems().set(listView.getSelectionModel().getSelectedIndex(), "[" + typ + "] " + geloeschtesFahrzeug.getMarke() + " " + geloeschtesFahrzeug.getModell() + " " + geloeschtesFahrzeug.getAussenfarbe() + " -Nicht verfügbar");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fahrzeugvermietung3000 - Fehler");
            alert.setHeaderText("Warnung");
            alert.setContentText("Es ist ein Fehler aufgetreten!");

            alert.showAndWait();

        }
    }

    public void fahrzeugAktualisieren(ActionEvent actionEvent) throws IOException {
        //JsonController wird geladen
        JsonController jsonController = ControllerMethods.getData();
        //Fahrzeugliste wird geladen
        List<Fahrzeug> fahrzeuge = new ArrayList<>();

        //Das Fahrzeug wird erstellt
        Fahrzeug dasFahrzeug = null;
        //Der Json controller wird erstellt um die Daten aus zu lesen
        try {
            //Die Fahrzeuge werden in eine Liste geladen
            fahrzeuge.addAll(jsonController.getVehicles());
            dasFahrzeug = fahrzeuge.get(ControllerVariables.getSelectedIndex());
        } catch (NullPointerException e) {
            //Es wird ein ALert angezeigt
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fahrzeugvermietung3000 - Fehler");
            alert.setHeaderText("Warnung");
            alert.setContentText("Es wurden keine Kunden erstellt. Bitte erstelle zuerst einen Kunden!");

            alert.showAndWait();
        }

        //Die TextFields und Labels werden instanziert und auch die Felder
        Spinner spinnerFirst = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#txtFirst");
        TextField txtMarke = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtMarke");
        TextField txtModell = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtModell");
        Spinner numHubraum = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numHubraum");
        TextField aussenfarbe = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtAussenfarbe");
        Spinner numKmStand = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numKmStand");
        TextField kennzeichen = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtKennzeichen");
        DatePicker datAb = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datAb");
        DatePicker datBis = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datBis");
        ChoiceBox aufbau = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtSecond");
        ChoiceBox navigation = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtThird");
        ChoiceBox verfuegbar = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#boolVerfuegbar");

        int spinnerFirstInt = (int) spinnerFirst.getValue();
        int numHubraumInt = (int) numHubraum.getValue();
        int kmStandInt = (int) numKmStand.getValue();
        String dateAbString = null;
        String dateBisString = null;
        String aufbauString = (String) aufbau.getValue();
        Boolean nav;
        Boolean verfu;
        if (verfuegbar.getValue() == "Nein") {
            verfu = true;
        } else {
            verfu = false;
        }
        if (navigation.getValue() == "Ja") {
            nav = true;
        } else {
            nav = false;
        }
        try {
            //Das eingegebene Datum wird als String gespeichert
            dateAbString = datAb.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            dateBisString = datBis.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            System.out.println("Fehler beim Datum");
        }
        //Es wird geschaut, welchen Fahrzeug typ der User gewählt hat
        switch (dasFahrzeug.getType()) {
            case 1:
                Auto auto = (Auto) dasFahrzeug;
                //Die Daten werden gesetzt
                auto.setMarke(txtMarke.getText());
                auto.setModell(txtModell.getText());
                auto.setAussenfarbe(aussenfarbe.getText());
                auto.setKennzeichen(kennzeichen.getText());
                auto.setKofferraumPlatz(spinnerFirstInt);
                auto.setHubraum(numHubraumInt);
                auto.setKmStand(kmStandInt);
                auto.setVerfuegbarAb(dateAbString);
                auto.setVerfuegbarBis(dateBisString);
                auto.setAufbau(aufbauString);
                auto.setNavigation(nav);
                auto.setNichtVerfuegbar(verfu);

                fahrzeuge.set(ControllerVariables.getSelectedIndex(), auto);
                break;
            case 2:
                Motorrad motorrad = (Motorrad) dasFahrzeug;
                motorrad.setMarke(txtMarke.getText());
                motorrad.setModell(txtModell.getText());
                motorrad.setAussenfarbe(aussenfarbe.getText());
                motorrad.setKennzeichen(kennzeichen.getText());
                motorrad.setTankvolumen(spinnerFirstInt);
                motorrad.setHubraum(numHubraumInt);
                motorrad.setKmStand(kmStandInt);
                motorrad.setVerfuegbarAb(dateAbString);
                motorrad.setVerfuegbarBis(dateBisString);
                motorrad.setNichtVerfuegbar(verfu);

                fahrzeuge.set(ControllerVariables.getSelectedIndex(), motorrad);
                break;
            case 3:
                Transporter transporter = (Transporter) dasFahrzeug;
                transporter.setMarke(txtMarke.getText());
                transporter.setModell(txtModell.getText());
                transporter.setAussenfarbe(aussenfarbe.getText());
                transporter.setKennzeichen(kennzeichen.getText());
                transporter.setLadegewicht(spinnerFirstInt);
                transporter.setHubraum(numHubraumInt);
                transporter.setKmStand(kmStandInt);
                transporter.setVerfuegbarAb(dateAbString);
                transporter.setVerfuegbarBis(dateBisString);
                transporter.setNichtVerfuegbar(verfu);

                fahrzeuge.set(ControllerVariables.getSelectedIndex(), transporter);
                break;
            default:
                //Wenn es ein Fehler gab
                System.out.println("Es gab ein Fehler");
                break;
        }
        jsonController.setVehicles(fahrzeuge);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);
        ControllerMethods.changeScene("../fxml/fahrzeugVerwalten.fxml");
        ControllerMethods.getVehicles();
    }

    public void vermietungSpeichern(ActionEvent actionEvent) throws IOException {
        Spinner dauer = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numTageMietdauer");
        Spinner preis = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numPreis");
        ComboBox status = (ComboBox) ControllerVariables.getSceneAuswahl().lookup("#selectStatus");
        int mietDauer = (int) dauer.getValue();
        int preisValue = (int) preis.getValue();
        String statusString = (String) status.getValue();

        Vermietung vermietung = new Vermietung(ControllerVariables.getSelectedVehicle(), ControllerVariables.getSelectedCustomer(), mietDauer, preisValue, statusString);
        JsonController jsonController = ControllerMethods.getData();
        List<Vermietung> vermietungen = new ArrayList<>();
        try {
            vermietungen.addAll(jsonController.getVermietungen());
        }catch (Exception e){
            
        }
        vermietungen.add(vermietung);
        jsonController.setVermietungen(vermietungen);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

        ControllerMethods.changeScene("../fxml/vermietungenVerwalten.fxml");
        ControllerMethods.getVermietungen();

    }

    public void fahrzeugAuswaehlenVm(ActionEvent actionEvent) throws IOException {
        ControllerMethods.changeScene("../fxml/fahrzeugSelect.fxml");
    }

    public void kundeAuswaehlenVm(ActionEvent actionEvent) throws IOException {
        ControllerMethods.changeScene("../fxml/kundeAuswaehlen.fxml");
        JsonController jsonController = ControllerMethods.getData();
        List<Kunde> kunden = jsonController.getKunden();
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#kundenListe");

        for (int i = 0; i < kunden.size(); i++) {
            listView.getItems().add(kunden.get(i).getNachname() + " " + kunden.get(i).getVorname());
        }
    }

    public void entgrauKundeAuswahl(MouseEvent mouseEvent) {
        Button auswaehlen = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnKundeAuswaehlen");

        auswaehlen.setDisable(false);
    }

    public void entgrauAuswahlFahrzeug(MouseEvent mouseEvent) {
        Button auswaehlen = (Button) ControllerVariables.getSceneAuswahl().lookup("#auswahlFahreugKnopf");

        auswaehlen.setDisable(false);
    }

    public void fahrzeugAuswaehlen(ActionEvent actionEvent) throws IOException {
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listViewFahrzeuge");
        JsonController jsonController = ControllerMethods.getData();
        List<Fahrzeug> fahrzeuge = jsonController.getVehicles();
        listView.getSelectionModel().getSelectedIndex();
        ControllerVariables.setSelectedVehicle(fahrzeuge.get(listView.getSelectionModel().getSelectedIndex()));
        ControllerMethods.changeScene("../fxml/vermietungErfassen.fxml");
        ComboBox status = (ComboBox) ControllerVariables.getSceneAuswahl().lookup("#selectStatus");
        String[] statusArr = {"Offen", "Bezahlt", "Abgeschlossen"};
        status.getItems().addAll(statusArr);
        status.setValue(statusArr[0]);
    }

    public void autoFiltern(ActionEvent actionEvent) throws IOException {
        JsonController jsonController = ControllerMethods.getData();
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listViewFahrzeuge");
        listView.getItems().clear();

        List<Fahrzeug> fahrzeuge = new ArrayList<>();
        fahrzeuge.addAll(jsonController.getVehicles());
        for (int i = 0; i < fahrzeuge.size(); i++) {
            if (fahrzeuge.get(i).getType() == 1 && fahrzeuge.get(i).getNichtVerfuegbar() == true) {
                listView.getItems().add(fahrzeuge.get(i).getMarke() + " " + fahrzeuge.get(i).getModell() + " " + fahrzeuge.get(i).getAussenfarbe());
            } else {
            }
        }
    }

    public void motorradFiltern(ActionEvent actionEvent) throws IOException {
        JsonController jsonController = ControllerMethods.getData();
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listViewFahrzeuge");
        listView.getItems().clear();

        List<Fahrzeug> fahrzeuge = new ArrayList<>();
        fahrzeuge.addAll(jsonController.getVehicles());
        for (int i = 0; i < fahrzeuge.size(); i++) {
            if (fahrzeuge.get(i).getType() == 2 && fahrzeuge.get(i).getNichtVerfuegbar() == true) {
                listView.getItems().add(fahrzeuge.get(i).getMarke() + " " + fahrzeuge.get(i).getModell() + " " + fahrzeuge.get(i).getAussenfarbe());
            } else {
            }
        }
    }

    public void transporterFiltern(ActionEvent actionEvent) throws IOException {
        JsonController jsonController = ControllerMethods.getData();
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listViewFahrzeuge");
        listView.getItems().clear();

        List<Fahrzeug> fahrzeuge = new ArrayList<>();
        fahrzeuge.addAll(jsonController.getVehicles());
        for (int i = 0; i < fahrzeuge.size(); i++) {
            if (fahrzeuge.get(i).getType() == 3 && fahrzeuge.get(i).getNichtVerfuegbar() == true) {
                listView.getItems().add(fahrzeuge.get(i).getMarke() + " " + fahrzeuge.get(i).getModell() + " " + fahrzeuge.get(i).getAussenfarbe());
            } else {
            }
        }
    }

    public void kundeAusgewaehltVermietung(ActionEvent actionEvent) throws IOException {
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#kundenListe");
        JsonController jsonController = ControllerMethods.getData();
        List<Kunde> kunden = jsonController.getKunden();
        ControllerVariables.setSelectedCustomer(kunden.get(listView.getSelectionModel().getSelectedIndex()));

        ControllerMethods.changeScene("../fxml/vermietungErfassen.fxml");
        ComboBox status = (ComboBox) ControllerVariables.getSceneAuswahl().lookup("#selectStatus");
        String[] statusArr = {"Offen", "Bezahlt", "Abgeschlossen"};
        status.getItems().addAll(statusArr);
        status.setValue(statusArr[0]);
    }

    public void entgrauvermitungen(MouseEvent mouseEvent) {
        Button bearbeiten = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnBearbeiten");
        Button loeschen = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnVermietungLoeschen");

        bearbeiten.setDisable(false);
        loeschen.setDisable(false);
    }
}
