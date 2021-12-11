package UI.Kundeberater.Fahrzeuge;

import Domain.Fahrzeug.Auto;
import Domain.Fahrzeug.Fahrzeug;
import Domain.Fahrzeug.Transporter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FahrzeugDetail {
    public static void detail(Fahrzeug fahrzeug) {
        //Stage
        Stage detailStage = new Stage();
        detailStage.setTitle("Detailansicht");
        detailStage.getIcons().add(new Image("logo.png"));

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 10, 10, 10));
        VBox vBox = new VBox(10);
        Insets groupInset = new Insets(0, 10, 0, 0);

        //Titel
        Text titel = new Text("Detailansicht");
        titel.setStyle("-fx-font: 24 arial;");
        titel.setTextAlignment(TextAlignment.CENTER);
        bp.setTop(titel);

        //Marke
        Text marketitel = new Text("Marke:");
        VBox.setMargin(marketitel, groupInset);
        Text marke = new Text();

        //Model
        Text modelTitel = new Text("Model:");
        VBox.setMargin(modelTitel, groupInset);
        Text model = new Text();

        //Hubraum
        Text hubraumTitel = new Text("Hubraum in ccm:");
        VBox.setMargin(hubraumTitel, groupInset);
        Text hubraum = new Text();

        //treibstoffart
        Text treibstoffartText = new Text("Treibstoffart:");
        VBox.setMargin(treibstoffartText, groupInset);
        Text treibstoffart = new Text();

        //Aktueller KM stand
        Text aktuellerKMText = new Text("Aktueller KM Stand:");
        VBox.setMargin(aktuellerKMText, groupInset);
        Text aktuellerKMStand = new Text();

        //Leistung
        Text leistungText = new Text("Leisgung in PS:");
        VBox.setMargin(leistungText, groupInset);
        Text leistung = new Text();

        //Erstzulassung
        Text erstzulassungText = new Text("Erstzulassung:");
        VBox.setMargin(erstzulassungText, groupInset);
        Text erstzulassung = new Text();

        //Farbe
        Text aussenfarbeText = new Text("Aussenfarbe:");
        VBox.setMargin(aussenfarbeText, groupInset);
        Text aussenfarbe = new Text();

        //Leergewicht
        Text leergewichtText = new Text("Leergewicht in KG:");
        VBox.setMargin(leergewichtText, groupInset);
        Text leergewicht = new Text();

        //Alles in UI
        vBox.getChildren().addAll(marketitel, marke, modelTitel, model,
                hubraumTitel, hubraum, treibstoffartText, treibstoffart, aktuellerKMText,
                aktuellerKMStand, leistungText, leistung,
                erstzulassungText, erstzulassung, aussenfarbeText, aussenfarbe,
                leergewichtText, leergewicht);

        //Aufbau & Navigation
        Text aufbau = new Text();
        CheckBox navigation = new CheckBox();
        navigation.setDisable(true);

        //Maximale Zuladung
        Text maxZuladung = new Text();

        //Wenn es ein Auto ist
        if (fahrzeug instanceof Auto) {
            Text aufbauText = new Text("Aufbau:");
            VBox.setMargin(aufbauText, groupInset);
            Text navigationText = new Text("Navigation:");
            VBox.setMargin(navigationText, groupInset);
            vBox.getChildren().addAll(aufbauText, aufbau, navigationText, navigation);

            //Daten
            aufbau.setText(((Auto) fahrzeug).getAufbau()[((Auto) fahrzeug).getAufbauID()]);
            navigation.setSelected(((Auto) fahrzeug).getNavigation());
        } else {
            Text maxZuladungText = new Text("Maximale Zuladung in KG:");
            VBox.setMargin(maxZuladungText, groupInset);
            vBox.getChildren().addAll(maxZuladungText, maxZuladung);

            //Daten
            maxZuladung.setText(String.valueOf(((Transporter) fahrzeug).getMaxZuladung()));
        }

        //Daten
        marke.setText(fahrzeug.getMarke());
        model.setText(fahrzeug.getModel());
        hubraum.setText(String.valueOf(fahrzeug.getHubraum()));
        treibstoffart.setText(fahrzeug.getTreibstoffart()[fahrzeug.getTreibstoffartID()]);
        aktuellerKMStand.setText(String.valueOf(fahrzeug.getAktuellerKMStand()));
        leistung.setText(String.valueOf(fahrzeug.getPs()));
        erstzulassung.setText(fahrzeug.getErstzulassung().toString());
        aussenfarbe.setText(fahrzeug.getColor());
        leergewicht.setText(String.valueOf(fahrzeug.getLeergewicht()));

        bp.setCenter(vBox);
        BorderPane.setAlignment(titel, Pos.TOP_CENTER);
        detailStage.setScene(new Scene(bp, 500, 700));
        detailStage.show();
    }
}
