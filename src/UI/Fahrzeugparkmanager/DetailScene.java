package UI.Fahrzeugparkmanager;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Fahrzeug;
import Domain.Fahrzeug.Transporter;
import Infrasturcture.PersistencyService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class DetailScene {
    public static BorderPane editScene(Fahrzeug fahrzeug, int index) {
        //layout
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        Text titel = new Text("Detailansicht / Bearbeiten");
        titel.setStyle("-fx-font: 24 arial;");
        titel.setTextAlignment(TextAlignment.CENTER);
        root.setTop(titel);
        root.setPadding(new Insets(10, 10, 10, 10));
        BorderPane.setAlignment(titel, Pos.CENTER);

        //Gridpane
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(45);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(45);
        grid.getColumnConstraints().addAll(column0, column1, column2);
        Insets insertGroup = new Insets(5, 0, 0, 0);
        Insets groupSeparator = new Insets(10, 0, 0, 0);

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
        TextFormatter<Integer> numberFormatter2 = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        TextFormatter<Integer> numberFormatter3 = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        TextFormatter<Integer> numberFormatter4 = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        TextFormatter<Integer> numberFormatter5 = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999);
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999);
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999);
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999);
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999);

        //Marke
        Text markeText = new Text("Marke:");
        grid.add(markeText, 0, 1);
        TextField marke = new TextField();
        marke.setText(fahrzeug.getMarke());
        GridPane.setMargin(marke, insertGroup);
        marke.setPromptText("Marke");
        grid.add(marke, 0, 2);

        //Model
        Text modelText = new Text("Model:");
        GridPane.setMargin(modelText, groupSeparator);
        grid.add(modelText, 0, 3);
        TextField model = new TextField();
        model.setText(fahrzeug.getModel());
        model.setPromptText("Model");
        grid.add(model, 0, 4);
        GridPane.setMargin(model, insertGroup);

        //Hubraum
        Text hubraumText = new Text("Hubraum in ccm:");
        grid.add(hubraumText, 0, 5);
        GridPane.setMargin(hubraumText, groupSeparator);
        Spinner<Integer> hubraum = new Spinner<>();
        hubraum.setValueFactory(spinnerValueFactory);
        hubraum.getEditor().setTextFormatter(numberFormatter);
        hubraum.setEditable(true);
        hubraum.getValueFactory().setValue(fahrzeug.getHubraum());
        hubraum.setMaxWidth(Double.MAX_VALUE);
        hubraum.setPromptText("Hubraum in ccm");
        grid.add(hubraum, 0, 6);
        GridPane.setMargin(hubraum, insertGroup);

        //Treibstoffart
        Text treibstoffText = new Text("Treibstoffart:");
        grid.add(treibstoffText, 0, 7);
        GridPane.setMargin(treibstoffText, groupSeparator);
        ChoiceBox<String> treibstoffart = new ChoiceBox<>(FXCollections.observableArrayList(Arrays.asList(fahrzeug.getTreibstoffart())));
        grid.add(treibstoffart, 0, 8);
        treibstoffart.setValue(fahrzeug.getTreibstoffart()[fahrzeug.getTreibstoffartID()]);
        treibstoffart.setMaxWidth(Double.MAX_VALUE);
        GridPane.setMargin(treibstoffart, insertGroup);

        //Aktueller KM Stand
        Text aktuellerKMText = new Text("Aktueller KM Stand:");
        grid.add(aktuellerKMText, 0, 9);
        GridPane.setMargin(aktuellerKMText, groupSeparator);
        Spinner<Integer> kmStand = new Spinner<>();
        kmStand.setValueFactory(spinnerValueFactory2);
        kmStand.setEditable(true);
        kmStand.setMaxWidth(Double.MAX_VALUE);
        kmStand.setPromptText("Aktueller KM Stand");
        kmStand.getEditor().setTextFormatter(numberFormatter2);
        kmStand.getValueFactory().setValue(fahrzeug.getAktuellerKMStand());
        grid.add(kmStand, 0, 10);
        GridPane.setMargin(kmStand, insertGroup);

        //Leistung
        Text leistungsText = new Text("Leistung in PS:");
        grid.add(leistungsText, 2, 1);
        GridPane.setMargin(leistungsText, groupSeparator);
        Spinner<Integer> leistung = new Spinner<>();
        leistung.setEditable(true);
        leistung.setMaxWidth(Double.MAX_VALUE);
        leistung.getEditor().setTextFormatter(numberFormatter3);
        leistung.setValueFactory(spinnerValueFactory3);
        leistung.getValueFactory().setValue(fahrzeug.getPs());
        grid.add(leistung, 2, 2);

        //Erstzulassung
        Text erstzulassungsText = new Text("Erstzulassung");
        grid.add(erstzulassungsText, 2, 3);
        GridPane.setMargin(erstzulassungsText, groupSeparator);
        DatePicker erstzulassung = new DatePicker();
        erstzulassung.setValue(fahrzeug.getErstzulassung());
        grid.add(erstzulassung, 2, 4);
        erstzulassung.setMaxWidth(Double.MAX_VALUE);
        GridPane.setMargin(erstzulassung, insertGroup);

        //Aussenfarbe
        Text aussenfarbetext = new Text("Aussenfarbe:");
        grid.add(aussenfarbetext, 2, 5);
        GridPane.setMargin(aussenfarbetext, groupSeparator);
        TextField aussenfarbe = new TextField();
        aussenfarbe.setText(fahrzeug.getColor());
        aussenfarbe.setPromptText("Aussenfarbe");
        grid.add(aussenfarbe, 2, 6);
        GridPane.setMargin(aussenfarbe, insertGroup);

        //Leergewicht
        Text leergewichtText = new Text("Leergewicht in KG:");
        grid.add(leergewichtText, 2, 7);
        GridPane.setMargin(leergewichtText, groupSeparator);
        Spinner<Integer> leergewicht = new Spinner<>();
        leergewicht.setValueFactory(spinnerValueFactory4);
        leergewicht.getEditor().setTextFormatter(numberFormatter4);
        leergewicht.setEditable(true);
        leergewicht.getValueFactory().setValue(fahrzeug.getLeergewicht());
        grid.add(leergewicht, 2, 8);
        leergewicht.setMaxWidth(Double.MAX_VALUE);
        GridPane.setMargin(leergewicht, insertGroup);

        //Aufbau
        Text aufbauText = new Text("Aufbau:");
        ChoiceBox<String> aufbau = new ChoiceBox<>();

        //Navigation
        Text navigationText = new Text("Navigation:");
        CheckBox navigation = new CheckBox();

        //Maximale zuladung
        Text maximalezuladungText = new Text("Maximale Zuladung in KG:");
        Spinner<Integer> maximalezuladung = new Spinner<>();

        //if user is creating a car
        if (fahrzeug instanceof Auto) {
            //Aufbau
            aufbau.setValue(((Auto) fahrzeug).getAufbau()[((Auto) fahrzeug).getAufbauID()]);
            grid.add(aufbauText, 2, 9);
            GridPane.setMargin(aufbauText, groupSeparator);
            aufbau.setItems(FXCollections.observableList((Arrays.asList(((Auto) fahrzeug).getAufbau()))));
            aufbau.setMaxWidth(Double.MAX_VALUE);
            grid.add(aufbau, 2, 10);
            GridPane.setMargin(aufbau, insertGroup);

            //Navigationsystem
            navigation.setSelected(((Auto) fahrzeug).getNavigation());
            grid.add(navigationText, 2, 11);
            GridPane.setMargin(navigationText, groupSeparator);
            grid.add(navigation, 2, 12);
            GridPane.setMargin(navigation, insertGroup);
        } else {
            //Maximale zuladung
            grid.add(maximalezuladungText, 2, 9);
            GridPane.setMargin(maximalezuladungText, groupSeparator);
            maximalezuladung.setValueFactory(spinnerValueFactory5);
            maximalezuladung.getValueFactory().setValue(((Transporter) fahrzeug).getMaxZuladung());
            maximalezuladung.getEditor().setTextFormatter(numberFormatter5);
            maximalezuladung.setEditable(true);
            maximalezuladung.setMaxWidth(Double.MAX_VALUE);
            grid.add(maximalezuladung, 2, 10);
            GridPane.setMargin(maximalezuladung, insertGroup);
        }

        //Speichern
        Button saveButton = new Button("Speichern");
        saveButton.setMaxWidth(Double.MAX_VALUE);
        grid.add(saveButton, 2, 13);
        GridPane.setMargin(saveButton, groupSeparator);

        //Angaben speichern
        saveButton.setOnAction(event -> {
            try {
                fahrzeug.setMarke(marke.getText());
                fahrzeug.setModel(model.getText());
                fahrzeug.setHubraum(hubraum.getValue());
                fahrzeug.setTreibstoffartID(treibstoffart.getValue());
                fahrzeug.setAktuellerKMStand(kmStand.getValue());
                fahrzeug.setPs(leistung.getValue());
                fahrzeug.setErstzulassung(erstzulassung.getValue());
                fahrzeug.setColor(aussenfarbe.getText());
                fahrzeug.setLeergewicht(leergewicht.getValue());

                if (fahrzeug instanceof Auto) {
                    ((Auto) fahrzeug).setNavigation(navigation.isSelected());
                    ((Auto) fahrzeug).setAufbauID(aufbau.getValue());
                } else {
                    ((Transporter) fahrzeug).setMaxZuladung(maximalezuladung.getValue());
                }

                //Validierung
                List<String> meldung = fahrzeug.validateVehicle();
                if (meldung.size() != 0) {
                    Alert allesFuellen = new Alert(Alert.AlertType.WARNING);
                    allesFuellen.setHeaderText("Angaben überprüfen");
                    allesFuellen.setContentText("Bitte überprüfen Sie folgende Angaben: " + meldung + " und beachten Sie, dass alle Felder ausgefüllt sind");
                    allesFuellen.showAndWait();
                } else {
                    //Fahrzeug speichern
                    PersistencyService ps = new PersistencyService();
                    ps.setFahrzeug(index, fahrzeug);
                    saveButton.getScene().getWindow().hide();
                }
            } catch (Exception e) {
                Alert alarm = new Alert(Alert.AlertType.ERROR);
                alarm.setHeaderText("Speicherungsfehler");
                alarm.setContentText("Es ist ein unerwarteter Fehler passiert! Fahrzeug konnte nicht gespeichert werden.");
                alarm.showAndWait();
            }
        });

        //Abbrechen
        Button abbrechenButton = new Button("Abbrechen");
        abbrechenButton.setMaxWidth(Double.MAX_VALUE);
        grid.add(abbrechenButton, 0, 13);
        GridPane.setMargin(abbrechenButton, groupSeparator);

        abbrechenButton.setOnAction(action -> {
            abbrechenButton.getScene().getWindow().hide();
        });
        root.setCenter(grid);
        return root;
    }
}
