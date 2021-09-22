import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.java.fahrzeuge.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControllerMethods {

    public static void fahrzeugErfassen(int fahrzeugTyp) {
        //Die TextFields und Labels werden instanziert
        Label lblFirst = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblFirst");
        Label lblSecond = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblSecond");
        Label lblThird = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblThird");
        Spinner spinnerFirst = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#txtFirst");
        ChoiceBox choiceSecond = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtSecond");
        ChoiceBox choiceThird = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtThird");
        Label lblType = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblType");

        //Die Spinner werden gesetzt
        Spinner hubraum = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numHubraum");
        //Die Treibstoff Choice Box wird gesetzt
        ChoiceBox treibstoff = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#multplTreibstoff");
        String[] treibstoffArr = {"Benzin", "Diesel", "Elektrisch"};
        treibstoff.getItems().addAll(treibstoffArr);
        treibstoff.setValue(treibstoffArr[0]);

        //Die kategorie choice box wird gesetzt
        ChoiceBox kategorie = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#multplKategorie");
        String[] kategorieArr = {"Basic", "Medium", "Luxus"};
        kategorie.getItems().addAll(kategorieArr);
        kategorie.setValue(kategorieArr[0]);

        //Die Choice Box Ja nein für Verfügbar wird gesetzt
        ChoiceBox verfuegar = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#boolVerfuegbar");
        String[] verfuegbarArr = {"Ja", "Nein"};
        verfuegar.getItems().addAll(verfuegbarArr);
        verfuegar.setValue(verfuegbarArr[0]);

        //Es wird geschaut, welchen Fahrzeug typ der User gewählt hat
        switch (fahrzeugTyp) {
            case 1:
                //Wenn der user Auto ausgewählt hat werden die Label gesetzt
                lblFirst.setText("Kofferraumplatz für \"Anzahl koffer\"");
                lblSecond.setText("Aufbau");
                lblThird.setText("Navigationssystem");
                lblType.setText("Auto");
                //Es wird die ChoiceBox2 gesetzt
                String[] aufbauArr = {"Kleinwagen", "Limousine", "Kombi", "SUV", "Cabriolet"};
                choiceSecond.getItems().addAll(aufbauArr);
                choiceSecond.setValue(aufbauArr[0]);

                //Es wird die Choicebox3 gesetzt
                String[] navArr = {"Ja", "Nein"};
                choiceThird.getItems().setAll(navArr);
                choiceThird.setValue(navArr[0]);
                break;
            case 2:
                //Wenn der User Motorrad ausgewählt hat
                lblFirst.setText("Tankvolumen in Liter");
                lblSecond.setVisible(false);
                choiceSecond.setVisible(false);
                lblThird.setVisible(false);
                choiceThird.setVisible(false);
                lblType.setText("Motorrad");
                break;
            case 3:
                //Wenn der user Transporter ausgewählt hat
                lblFirst.setText("Ladegewicht in kg");
                lblSecond.setVisible(false);
                choiceSecond.setVisible(false);
                lblThird.setVisible(false);
                choiceThird.setVisible(false);
                lblType.setText("Transporter");
                break;
            default:
                //Wenn es ein Fehler gab
                lblFirst.setVisible(false);
                lblSecond.setVisible(false);
                lblThird.setVisible(false);
                spinnerFirst.setVisible(false);
                choiceSecond.setVisible(false);
                choiceThird.setVisible(false);
                System.out.println("Oooops something went wrong!");
                break;
        }
    }

    public static void saveVehicle() throws IOException {
        //Die Eingabefelder werden auf der View gesucht und instanziert
        TextField txtMarke = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtMarke");
        TextField txtModell = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtModell");
        Spinner numHubraum = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numHubraum");
        ChoiceBox treibstoff = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#multplTreibstoff");
        TextField aussenfarbe = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtAussenfarbe");
        Spinner numKmStand = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numKmStand");
        TextField kennzeichen = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtKennzeichen");
        ChoiceBox kategorie = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#multplKategorie");
        DatePicker datAb = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datAb");
        DatePicker datBis = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datBis");
        ChoiceBox verfuegar = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#boolVerfuegbar");
        Label lblType = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblType");
        Spinner txtFirst = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#txtFirst");
        ChoiceBox aufbau = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtSecond");
        ChoiceBox txtThird = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtThird");


        //Die Daten der Felder werden ausgelesen und in neue Variablen gespeichert
        int hubraum = (int) numHubraum.getValue();
        String treibstoffArt = (String) treibstoff.getValue();
        int kmStand = (int) numKmStand.getValue();
        String kategorieFahrzeug = (String) kategorie.getValue();
        Boolean verfuegbarkeit;
        int fahrzeugTyp;
        int txtFirstValue = (int) txtFirst.getValue();
        String aufbauValue = (String) aufbau.getValue();
        Boolean navigation;

        //Die Zahl wird angepasst, zu dem Fahrzeug, das der Kunde erfasst hat
        if (lblType.getText() == "Auto") {
            fahrzeugTyp = 1;
        } else if (lblType.getText() == "Motorrad") {
            fahrzeugTyp = 2;
        } else {
            fahrzeugTyp = 3;
        }
        //Wenn der user Ja oder nein gewählt hat bei verfügbar wird der Boolean aktualisiert
        if (verfuegar.getValue() == "Ja") {
            verfuegbarkeit = true;
        } else {
            verfuegbarkeit = false;
        }
        //Wenn der user JA oder Nein Ausgewählt hat bei Navigationssystem
        if (txtThird.getValue() == "Ja") {
            navigation = true;
        } else {
            navigation = false;
        }

        //Der Objectmapper wird instanziert
        ObjectMapper objectMapper = new ObjectMapper();
        //Die Fahrzeugliste wird instanziert
        JsonController jsonController = new JsonController();
        //Es wird eine neue Liste erstellt, in der Fahrzeuge gespeichert werden
        List<Fahrzeug> listeNeueFahrzeuge = new ArrayList<>();

        //Der Pfad der Datei wird erstellt und es wird geschaut, ob die Datei schon existiert
        File fahrzeugJson = new File("daten.json");
        //Wenn es die JSON File schon gibt
        if ((new File("daten.json").isFile())) {
            System.out.println("Datei existiert schon.");
            try {
                //Die Fahrzeuge werden ausgelesen und in die Liste gespeichert
                jsonController = ControllerMethods.getData();
                listeNeueFahrzeuge.addAll(jsonController.getVehicles());
            } catch (IOException e) {
                System.out.println("Keine Daten");
            }

        } else {
            //Wenn es die File noch nicht gibt wird sie erstellt
            if (fahrzeugJson.createNewFile()) {
                System.out.println("Datei: " + fahrzeugJson.getName() + " wurde erstellt.");
            }
        }
        String dateAb = null;
        String dateBis = null;
        try {
            //Das eingegebene Datum wird als String gespeichert
            dateAb = datAb.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            dateBis = datBis.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            System.out.println("Fehler beim Datum");
        }
        //Wenn der user ein Fahrzeug erstellt hat und es ein Auto ist
        if (lblType.getText() == "Auto") {
            //Es wird ein Objekt des Autos erstellt
            Auto auto = new Auto(txtMarke.getText(), txtModell.getText(), hubraum, treibstoffArt, aussenfarbe.getText(), kmStand, kennzeichen.getText(), kategorieFahrzeug, dateAb, dateBis, verfuegbarkeit, fahrzeugTyp, txtFirstValue, aufbauValue, navigation);
            System.out.println("Auto wurde erfolgreich gespeichert");
            //Das Objekt wird in einer JSON file gespeichert
            listeNeueFahrzeuge.add(auto);
            jsonController.setVehicles(listeNeueFahrzeuge);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Wenn es ein Transporter ist
        } else if (lblType.getText() == "Transporter") {
            //Es wird ein Objekt des Transporters instanziert
            Transporter transport = new Transporter(txtMarke.getText(), txtModell.getText(), hubraum, treibstoffArt, aussenfarbe.getText(), kmStand, kennzeichen.getText(), kategorieFahrzeug, dateAb, dateBis, verfuegbarkeit, fahrzeugTyp, txtFirstValue);
            System.out.println("Transporter wurde erfolgreich gespeichert");
            //Das Objekt wird in der JSON Datei gespeichert
            listeNeueFahrzeuge.add(transport);
            jsonController.setVehicles(listeNeueFahrzeuge);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Wenn der user ein Motorrad erstellt
        } else if (lblType.getText() == "Motorrad") {
            //Es wird ein Objekt des Motorrades instanziert
            Motorrad motorrad = new Motorrad(txtMarke.getText(), txtModell.getText(), hubraum, treibstoffArt, aussenfarbe.getText(), kmStand, kennzeichen.getText(), kategorieFahrzeug, dateAb, dateBis, verfuegbarkeit, fahrzeugTyp, txtFirstValue);
            System.out.println("Motorrad wurde erfolgreich gespeichert");
            //Das Objekt wird in der JSON Datei gespeichert
            listeNeueFahrzeuge.add(motorrad);
            jsonController.setVehicles(listeNeueFahrzeuge);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

            //Wenn es ein Fehler gab
        } else {
            System.out.println("Oooops an error occured! -Auto speichern");
        }

    }

    public static void getVehicles() {
        //Der ObjectMapper wird instanziert
        ObjectMapper mapper = new ObjectMapper();
        //Die ListView wird instanziert
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#listVehicles");
        //Es wird eine neue Liste gemacht
        List<Fahrzeug> fahrzeuge = new ArrayList<>();
        //Die Fahrzeug Liste wird instanziert zum importieren der Fahrzeuge
        JsonController vehiclesList = null;
        try {
            //Die Fahrzeuge werden ausgelesen
            vehiclesList = mapper.readValue(Paths.get("daten.json").toFile(), JsonController.class);

            //Es werden die gespeicherten Fahrzeuge in die neue Liste eingegeben
            fahrzeuge.addAll(vehiclesList.getVehicles());

            if (fahrzeuge.isEmpty()) {
                //Es wird ein Alert angzeigt wenn es keine Einträge gibt
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fahrzeugvermietung3000 - Fehler");
                alert.setHeaderText("Warnung");
                alert.setContentText("Es gibt im Moment keine Fahrzeuge zum anzeigen. Erfasse zuerst ein Fahrzeug!");

                alert.showAndWait();
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("Keine Fahrzeuge gespeichert");

            //Es wird ein Alert angzeigt
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fahrzeugvermietung3000 - Fehler");
            alert.setHeaderText("Warnung");
            alert.setContentText("Es gibt im Moment keine Fahrzeuge zum anzeigen. Erfasse zuerst ein Fahrzeug!");

            alert.showAndWait();
        }
        //Es wird durch das Array geloopt und die Modell namen ausgelesen
        for (int i = 0; i < fahrzeuge.size(); i++) {
            String verfuegbar;
            if (fahrzeuge.get(i).getNichtVerfuegbar()) {
                verfuegbar = "Verfügbar";
            } else {
                verfuegbar = "Nicht Verfügbar";
            }
            String type = null;
            switch (fahrzeuge.get(i).getType()) {
                case 1:
                    type = "Auto";
                    break;
                case 2:
                    type = "Motorrad";
                    break;
                case 3:
                    type = "Transporter";
                    break;
                default:
                    System.out.println("Es ist ein Fehler aufgetreten beim Fahrzeugtyp");
            }
            listView.getItems().add("[" + type + "] " + fahrzeuge.get(i).getMarke() + " " + fahrzeuge.get(i).getModell() + " " + fahrzeuge.get(i).getAussenfarbe() + " -" + verfuegbar);
        }
    }

    public static void saveCustomer() throws IOException {
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

        //Der Objectmapper wird instanziert
        ObjectMapper objectMapper = new ObjectMapper();

        //Es wird eine neue Liste für die Kunden erstellt
        List<Kunde> listeNeueKunden = new ArrayList<>();

        //Der Json Controller wird instanziert
        JsonController jsonController = null;

        //Der Pfad der Datei wird erstellt und es wird geschaut, ob die Datei schon existiert
        File fahrzeugJson = new File("daten.json");
        //Wenn es die JSON File schon gibt
        if ((new File("daten.json").isFile())) {
            System.out.println("Datei existiert schon.");
            try {
                //Die Kunden und Fahrzeuge werden aus der Json file gelesen
                jsonController = ControllerMethods.getData();
                //Die Kunden und Fahrzeuge werden aus der File in die jetztige Liste gelesen
                listeNeueKunden.addAll(jsonController.getKunden());

            } catch (NullPointerException | IOException e) {
                System.out.println("Keine Daten");
            }

        } else {
            //Wenn es die File noch nicht gibt wird sie erstellt
            if (fahrzeugJson.createNewFile()) {
                System.out.println("Datei: " + fahrzeugJson.getName() + " wurde erstellt.");
            }
        }
        //Das Objekt Kunde wird erstellt und der neue Kunde hinzugefügt
        Kunde kunde = new Kunde(txtVorname.getText(), txtNachname.getText(), txtStrasse.getText(), txtHausnummer.getText(), plz, txtWohnort.getText(), txtTelefon.getText(), txtEmail.getText(), txtGeburt);
        listeNeueKunden.add(kunde);

        //Die Listen werden im Json controller gespeichert
        jsonController.setKunden(listeNeueKunden);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(Paths.get("daten.json").toFile(), jsonController);

    }

    public static void getCustomers() {
        //Objectmapper wird instanziert
        ObjectMapper mapper = new ObjectMapper();
        ListView listView = (ListView) ControllerVariables.getSceneAuswahl().lookup("#lstKunde");

        //Es wird eine Liste für die Kunden generiert
        List<Kunde> kunden = new ArrayList<>();
        JsonController jsonController = null;
        try {
            //Die Kunden werden ausgelesen
            jsonController = mapper.readValue(Paths.get("daten.json").toFile(), JsonController.class);
            kunden.addAll(jsonController.getKunden());
        } catch (NullPointerException | IOException e) {
            //Es wird ein Alert angzeigt
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fahrzeugvermietung3000 - Fehler");
            alert.setHeaderText("Warnung");
            alert.setContentText("Es gibt im Moment keine Kunden zum anzeigen. Erfasse zuerst ein Kunde!");

            alert.showAndWait();
        }
        for (int i = 0; i < kunden.size(); i++) {
            listView.getItems().add(kunden.get(i).getVorname() + " " + kunden.get(i).getNachname());
        }
    }

    public static void kundeBearbeiten(ListView listViewKunde) throws IOException {
        //ObjectMapper wird instanziert
        ObjectMapper objectMapper = new ObjectMapper();
        //Es wird eine Liste für die Kunden erstellt
        List<Kunde> kundeListe = new ArrayList<>();

        //Der ausgewählte index wird gesetzt
        ControllerVariables.setSelectedIndex(listViewKunde.getSelectionModel().getSelectedIndex());

        //Der Kunde wird erstellt
        Kunde derKunde = null;
        //Der Json controller wird erstellt um die Daten aus zu lesen
        JsonController jsonController;
        try {
            //Die Kunden werden in eine Liste geladen
            jsonController = objectMapper.readValue(Paths.get("daten.json").toFile(), JsonController.class);
            kundeListe.addAll(jsonController.getKunden());
            //Der ausgewählte Kunde wird gesetzt
            derKunde = kundeListe.get(listViewKunde.getSelectionModel().getSelectedIndex());
        } catch (IOException | NullPointerException e) {
            //Es wird ein ALert angezeigt
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fahrzeugvermietung3000 - Fehler");
            alert.setHeaderText("Warnung");
            alert.setContentText("Es wurden keine Kunden erstellt. Bitte erstelle zuerst einen Kunden!");

            e.printStackTrace();
            alert.showAndWait();
        }
        //Die neue Scene wird gesetzt
        changeScene("../fxml/kundeErfassen.fxml");

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
        Button speichern = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnSpeichern");
        Button aktualisieren = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnKundeAktualisieren");

        //Die Knöpfe werden angezeigt, die angezeigt werden müssen
        speichern.setVisible(false);
        speichern.setManaged(false);
        aktualisieren.setVisible(true);

        //Das Datum wird von einem String zu einem Datum konvertiert
        try {
            System.out.println(derKunde.getGeburtsdatum());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate geburtsdatum = LocalDate.parse(derKunde.getGeburtsdatum(), formatter);
            datGeburt.setValue(geburtsdatum);

        } catch (Exception e) {
            System.out.println("Fehler beim Datum");
        }

        //Die Felder werden gesetzt
        txtVorname.setText(derKunde.getVorname());
        txtNachname.setText(derKunde.getNachname());
        txtHausnummer.setText(derKunde.getHausnummer());
        txtStrasse.setText(derKunde.getStrasse());
        numPLZ.getValueFactory().setValue(derKunde.getPlz());
        txtWohnort.setText(derKunde.getWohnort());
        txtTelefon.setText(derKunde.getTelefon());
        txtEmail.setText(derKunde.getEmail());

    }

    public static JsonController getData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonController jsoncontroller = objectMapper.readValue(Paths.get("daten.json").toFile(), JsonController.class);
        return jsoncontroller;
    }

    public static void fahrzeugBearbeiten(ListView fahrzeuge) throws IOException {
        //Es wird eine Liste für die Fahrzeuge erstellt
        List<Fahrzeug> fahrzeugListe = new ArrayList<>();

        //Der ausgewählte index wird gesetzt
        ControllerVariables.setSelectedIndex(fahrzeuge.getSelectionModel().getSelectedIndex());

        //Der Kunde wird erstellt
        Fahrzeug dasFahrzeug = null;
        //Der Json controller wird erstellt um die Daten aus zu lesen
        JsonController jsonController;
        try {
            //Die Kunden werden in eine Liste geladen
            jsonController = ControllerMethods.getData();
            fahrzeugListe.addAll(jsonController.getVehicles());
            //Der ausgewählte Kunde wird gesetzt
            dasFahrzeug = fahrzeugListe.get(fahrzeuge.getSelectionModel().getSelectedIndex());
        } catch (IOException | NullPointerException e) {
            //Es wird ein ALert angezeigt
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fahrzeugvermietung3000 - Fehler");
            alert.setHeaderText("Warnung");
            alert.setContentText("Es wurden keine Kunden erstellt. Bitte erstelle zuerst einen Kunden!");

            e.printStackTrace();
            alert.showAndWait();
        }
        //Die neue Scene wird gesetzt
        changeScene("../fxml/fahrzeugErfassen.fxml");

        //Die TextFields und Labels werden instanziert und auch die Felder
        Label lblFirst = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblFirst");
        Label lblSecond = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblSecond");
        Label lblThird = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblThird");
        Spinner spinnerFirst = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#txtFirst");
        ChoiceBox choiceSecond = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtSecond");
        ChoiceBox choiceThird = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#txtThird");
        Label lblType = (Label) ControllerVariables.getSceneAuswahl().lookup("#lblType");
        Button speichern = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnSpeichern");
        Button aktualisieren = (Button) ControllerVariables.getSceneAuswahl().lookup("#btnAktualisieren");
        TextField txtMarke = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtMarke");
        TextField txtModell = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtModell");
        Spinner numHubraum = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numHubraum");
        TextField aussenfarbe = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtAussenfarbe");
        Spinner numKmStand = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#numKmStand");
        TextField kennzeichen = (TextField) ControllerVariables.getSceneAuswahl().lookup("#txtKennzeichen");
        DatePicker datAb = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datAb");
        DatePicker datBis = (DatePicker) ControllerVariables.getSceneAuswahl().lookup("#datBis");
        Spinner txtFirst = (Spinner) ControllerVariables.getSceneAuswahl().lookup("#txtFirst");

        txtMarke.setText(dasFahrzeug.getMarke());
        txtModell.setText(dasFahrzeug.getModell());
        numHubraum.getValueFactory().setValue(dasFahrzeug.getHubraum());
        aussenfarbe.setText(dasFahrzeug.getAussenfarbe());
        numKmStand.getValueFactory().setValue(dasFahrzeug.getKmStand());
        kennzeichen.setText(dasFahrzeug.getKennzeichen());


        //Die Treibstoff Choice Box wird gesetzt
        ChoiceBox treibstoff = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#multplTreibstoff");
        String[] treibstoffArr = {"Benzin", "Diesel", "Elektrisch"};
        treibstoff.getItems().addAll(treibstoffArr);
        treibstoff.setValue(dasFahrzeug.getTreibstoffArt());

        //Die kategorie choice box wird gesetzt
        ChoiceBox kategorie = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#multplKategorie");
        String[] kategorieArr = {"Basic", "Medium", "Luxus"};
        kategorie.getItems().addAll(kategorieArr);
        kategorie.setValue(dasFahrzeug.getKategorie());

        //Die Choice Box Ja nein für Verfügbar wird gesetzt
        ChoiceBox verfuegar = (ChoiceBox) ControllerVariables.getSceneAuswahl().lookup("#boolVerfuegbar");
        String[] verfuegbarArr = {"Ja", "Nein"};
        verfuegar.getItems().addAll(verfuegbarArr);
        String verfuegbarString;
        if (dasFahrzeug.getNichtVerfuegbar()) {
            verfuegbarString = "Nein";
        } else {
            verfuegbarString = "Ja";
        }
        verfuegar.setValue(verfuegbarString);

        //Es wird geschaut, welchen Fahrzeug typ der User gewählt hat
        switch (dasFahrzeug.getType()) {
            case 1:
                Auto auto = (Auto) dasFahrzeug;
                //Wenn der user Auto ausgewählt hat werden die Label gesetzt
                lblFirst.setText("Kofferraumplatz für \"Anzahl koffer\"");
                txtFirst.getValueFactory().setValue(auto.getKofferraumPlatz());
                lblSecond.setText("Aufbau");
                lblThird.setText("Navigationssystem");
                lblType.setText("Auto");
                //Es wird die ChoiceBox2 gesetzt
                String[] aufbauArr = {"Kleinwagen", "Limousine", "Kombi", "SUV", "Cabriolet"};
                choiceSecond.getItems().addAll(aufbauArr);
                choiceSecond.setValue(auto.getAufbau());

                //Es wird die Choicebox3 gesetzt
                String[] navArr = {"Ja", "Nein"};
                choiceThird.getItems().setAll(navArr);
                int nav;
                if (auto.getNavigation()) {
                    nav = 0;
                } else {
                    nav = 1;
                }
                choiceThird.setValue(navArr[nav]);
                break;
            case 2:
                Motorrad motorrad = (Motorrad) dasFahrzeug;
                //Wenn der User Motorrad ausgewählt hat
                lblFirst.setText("Tankvolumen in Liter");
                txtFirst.getValueFactory().setValue(motorrad.getTankvolumen());
                lblSecond.setVisible(false);
                choiceSecond.setVisible(false);
                lblThird.setVisible(false);
                choiceThird.setVisible(false);
                lblType.setText("Motorrad");
                break;
            case 3:
                Transporter transporter = (Transporter) dasFahrzeug;
                //Wenn der user Transporter ausgewählt hat
                lblFirst.setText("Ladegewicht in kg");
                txtFirst.getValueFactory().setValue(transporter.getLadegewicht());
                lblSecond.setVisible(false);
                choiceSecond.setVisible(false);
                lblThird.setVisible(false);
                choiceThird.setVisible(false);
                lblType.setText("Transporter");
                break;
            default:
                //Wenn es ein Fehler gab
                lblFirst.setVisible(false);
                lblSecond.setVisible(false);
                lblThird.setVisible(false);
                spinnerFirst.setVisible(false);
                choiceSecond.setVisible(false);
                choiceThird.setVisible(false);
                System.out.println("Oooops something went wrong!");
                break;
        }

        //Die Knöpfe werden angezeigt, die angezeigt werden müssen
        speichern.setVisible(false);
        speichern.setManaged(false);
        aktualisieren.setVisible(true);

        //Das Datum wird von einem String zu einem Datum konvertiert
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate verfuegbarAb = LocalDate.parse(dasFahrzeug.getVerfuegbarAb(), formatter);
            datAb.setValue(verfuegbarAb);

            LocalDate verfuegbarBis = LocalDate.parse(dasFahrzeug.getVerfuegbarBis(), formatter);
            datBis.setValue(verfuegbarBis);

        } catch (Exception e) {
            System.out.println("Problem beim Datum");

        }

    }

    public void changeStageLogin(String auswahlStage, String user, int widthSt, int heightSt, int widthSc, int heightSc) {
        try {
            //Wenn der Knopf Fahrzeugparkmanager gedrückt wird, öffnet sich ein neues Fenster
            Stage stageUser = new Stage();
            //Die FXML Datei wird geladen
            Parent root = FXMLLoader.load(getClass().getResource(auswahlStage));
            //Der Titel des Fensters wird gesetzt
            stageUser.setTitle("Fahrzeugvermietung3000 - " + user);
            //Die Scene wird erstellt
            Scene scene = new Scene(root, widthSc, heightSc);
            ControllerVariables.setSceneAuswahl(scene);
            stageUser.setScene(scene);
            //Die Scene wird im Controller gespeichert für den Zugriff
            ControllerVariables.setSceneUser(scene);
            //Das Icon des Fensters wird eingefügt
            stageUser.getIcons().add(new Image(getClass().getResourceAsStream("../logo.png")));
            //Die Minimum und Maximum Grösse wird gesetzt
            stageUser.setMinWidth(widthSt);
            stageUser.setMinHeight(heightSt);
            //Das Fenster öffnet sich
            stageUser.show();

            //Das letzte Fenster schliesst sich
            ControllerVariables.getStageLogin().hide();

            //Die User Stage wird in den Controller gespeichert
            ControllerVariables.setStageUser(stageUser);
        } catch (IOException b) {
            //Wenn es einen Fehler gegeben hat kommt eine Fehlermeldung
            System.out.println("Oooops, Etwas ist fehlgeschlagen!!");
        }

    }

    public static void changeScene(String scenePath) throws IOException {
        //Die FXML Datei wird geladden
        Parent root = FXMLLoader.load(ControllerMethods.class.getResource(scenePath));
        Scene sceneAuswahl = new Scene(root);
        //Die Scene wird im Controller Variables gespeichert
        ControllerVariables.setSceneAuswahl(sceneAuswahl);
        //Die Scene wird gesetzt
        ControllerVariables.getStageUser().setScene(sceneAuswahl);
    }

    public static void getVermietungen() {
        ListView vermietungen = (ListView) ControllerVariables.getSceneAuswahl().lookup("#vermietungListe");

        new JsonController();
        JsonController jsonController;
        List<Vermietung> vermietungenList = new ArrayList<>();
        try {
            jsonController = ControllerMethods.getData();
            vermietungenList.addAll(jsonController.getVermietungen());

            for (int i = 0; i < vermietungenList.size(); i++) {
                vermietungen.getItems().add(vermietungenList.get(i).getKunde().getNachname() + " " + vermietungenList.get(i).getKunde().getVorname() + " " + vermietungenList.get(i).getFahrzeug().getMarke() + " " + vermietungenList.get(i).getFahrzeug().getModell() + " -" + vermietungenList.get(i).getStatus());
            }
        } catch (IOException e) {
            System.out.println("Es gab ein fehler");
            e.printStackTrace();
        }
    }
}
