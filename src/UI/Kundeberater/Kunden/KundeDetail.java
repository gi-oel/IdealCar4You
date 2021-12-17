package UI.Kundeberater.Kunden;

import Domain.Kunde.Kunde;
import Infrasturcture.PersistencyService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;
import java.util.function.UnaryOperator;

public class KundeDetail {
    public static BorderPane detail(int index, Kunde kunde, Stage theStage) {
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(45);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(45);
        grid.getColumnConstraints().addAll(column0, column1, column2);
        Insets groupInset = new Insets(10, 0, 0, 0);
        Insets innerInset = new Insets(5, 0, 0, 0);

        //Textformatierung für Zahlen
        //Text formatter für nummer inputfelder
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                // NumberFormat beginnt am anfang vom text
                format.parse(c.getControlNewText(), parsePosition);
                if (parsePosition.getIndex() == 0 ||
                        parsePosition.getIndex() < c.getControlNewText().length()) {
                    // Formattierung wird abgelehnt, wenn alles fehlschlägt
                    return null;
                }
            }
            return c;
        };
        TextFormatter<Integer> numberFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999);

        //Title
        Text kundeErstellen = new Text("Kunde erfassen");
        kundeErstellen.setStyle("-fx-font: 24 arial;");
        root.setTop(kundeErstellen);
        kundeErstellen.setTextAlignment(TextAlignment.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        BorderPane.setAlignment(kundeErstellen, Pos.TOP_CENTER);

        //Name
        Text nameText = new Text("Name:");
        grid.add(nameText, 0, 0);
        GridPane.setMargin(nameText, groupInset);
        TextField name = new TextField();
        name.setPromptText("Name");
        name.setText(kunde.getName());
        grid.add(name, 0, 1);
        grid.setMaxWidth(Double.MAX_VALUE);
        GridPane.setMargin(name, innerInset);

        //Vorname
        Text vornameText = new Text("Vorname:");
        grid.add(vornameText, 0, 2);
        GridPane.setMargin(vornameText, groupInset);
        TextField vorname = new TextField();
        vorname.setMaxWidth(Double.MAX_VALUE);
        vorname.setPromptText("Vorname");
        vorname.setText(kunde.getVorname());
        grid.add(vorname, 0, 3);
        GridPane.setMargin(vorname, innerInset);

        //Strasse
        Text strasseText = new Text("Strasse:");
        grid.add(strasseText, 0, 4);
        GridPane.setMargin(strasseText, groupInset);
        TextField strasse = new TextField();
        strasse.setMaxWidth(Double.MAX_VALUE);
        strasse.setText(kunde.getStrasseUndNr().split(";")[0]);
        grid.add(strasse, 0, 5);
        GridPane.setMargin(strasse, innerInset);
        strasse.setPromptText("Strasse");

        //Nummer
        Text nummerText = new Text("Hausnummer:");
        grid.add(nummerText, 0, 6);
        GridPane.setMargin(nummerText, groupInset);
        TextField hausnummer = new TextField();
        hausnummer.setMaxWidth(Double.MAX_VALUE);
        hausnummer.setText(kunde.getStrasseUndNr().split(";")[1]);
        grid.add(hausnummer, 0, 7);
        GridPane.setMargin(hausnummer, innerInset);
        hausnummer.setPromptText("Hausnummer");

        //PLZ
        Text plzText = new Text("Postleitzahl:");
        grid.add(plzText, 0, 8);
        GridPane.setMargin(plzText, groupInset);
        Spinner<Integer> plz = new Spinner<>();
        plz.setValueFactory(spinnerValueFactory);
        plz.getEditor().setTextFormatter(numberFormatter);
        plz.setEditable(true);
        plz.getValueFactory().setValue(kunde.getPlz());
        grid.add(plz, 0, 9);
        plz.setMaxWidth(Double.MAX_VALUE);
        GridPane.setMargin(plz, innerInset);

        //Wohnort
        Text wohnortText = new Text("Wohnort:");
        grid.add(wohnortText, 2, 0);
        GridPane.setMargin(wohnortText, groupInset);
        TextField wohnort = new TextField();
        wohnort.setPromptText("Wohnort");
        wohnort.setMaxWidth(Double.MAX_VALUE);
        wohnort.setText(kunde.getWohnort());
        grid.add(wohnort, 2, 1);
        GridPane.setMargin(wohnort, innerInset);

        //Telefon Privat
        Text telPrivText = new Text("Telefon privat (optional):");
        grid.add(telPrivText, 2, 2);
        GridPane.setMargin(telPrivText, groupInset);
        TextField telPriv = new TextField();
        telPriv.setMaxWidth(Double.MAX_VALUE);
        telPriv.setPromptText("Telefon privat");
        telPriv.setText(kunde.getTelefonPriv());
        grid.add(telPriv, 2, 3);
        GridPane.setMargin(telPriv, innerInset);

        //Telefon mobil
        Text telMobText = new Text("Telefon mobil:");
        grid.add(telMobText, 2, 4);
        GridPane.setMargin(telMobText, groupInset);
        TextField telMob = new TextField();
        telMob.setPromptText("Telefon mobil");
        telMob.setMaxWidth(Double.MAX_VALUE);
        telMob.setText(kunde.getTelefonMob());
        grid.add(telMob, 2, 5);
        GridPane.setMargin(telMob, innerInset);

        //email
        Text emailText = new Text("E-Mail:");
        grid.add(emailText, 2, 6);
        GridPane.setMargin(emailText, groupInset);
        TextField email = new TextField();
        email.setMaxWidth(Double.MAX_VALUE);
        email.setPromptText("E-Mail");
        email.setText(kunde.getEmail());
        grid.add(email, 2, 7);
        GridPane.setMargin(email, innerInset);

        //Geburtsdatum
        Text geburtsText = new Text("Geburtsdatum:");
        grid.add(geburtsText, 2, 8);
        GridPane.setMargin(geburtsText, groupInset);
        DatePicker geburtstag = new DatePicker();
        geburtstag.setMaxWidth(Double.MAX_VALUE);
        geburtstag.setValue(kunde.getGeburtsdatum());
        grid.add(geburtstag, 2, 9);
        GridPane.setMargin(geburtstag, innerInset);

        //Speichern
        Button speichern = new Button("Speichern");
        speichern.setMaxWidth(Double.MAX_VALUE);
        grid.add(speichern, 0, 10, 3, 10);
        GridPane.setMargin(speichern, groupInset);

        //wenn der user auf speichern klickt
        speichern.setOnAction(action -> {
            kunde.setName(name.getText());
            kunde.setVorname(vorname.getText());
            kunde.setPlz(plz.getValue());
            kunde.setWohnort(wohnort.getText());
            kunde.setEmail(email.getText());
            kunde.setTelefonMob(telMob.getText());
            kunde.setTelefonPriv(telPriv.getText());
            kunde.setGeburtsdatum(geburtstag.getValue());

            //Validierung des Kunden
            List<String> errors = kunde.validateCustomer();
            //Validate strasse und nr
            if (strasse.getText().equals("")) {
                errors.add("Strasse");
            }
            if (hausnummer.getText().equals("")) {
                errors.add("Hausnummer");
            }

            //Wenn alles erfolgreich validiert wurde, dann speichern sonst warnung
            if (errors.size() == 0) {
                PersistencyService ps = new PersistencyService();
                try {
                    ps.setKunde(index, kunde);
                    theStage.hide();
                } catch (IOException e) {
                    Alert alarm = new Alert(Alert.AlertType.ERROR);
                    alarm.setHeaderText("Speicherungsfehler");
                    alarm.setContentText("Es ist ein Fehler aufgetreten und der Kunde konnte nicht gespeichert werden!");
                    alarm.showAndWait();
                }
            } else {
                Alert warnung = new Alert(Alert.AlertType.WARNING);
                warnung.setHeaderText("Angaben beachten");
                warnung.setContentText("Bitte beachten Sie diese Felder: " + errors + "! Achten Sie darauf, dass die Felder nicht leer sind.");
                warnung.showAndWait();
            }
        });

        root.setCenter(grid);
        return root;
    }
}
