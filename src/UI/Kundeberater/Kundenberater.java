package UI.Kundeberater;

import UI.Fahrzeugparkmanager.Fahrzeugparkmanager;
import UI.Kundeberater.Fahrzeuge.FahrzeugListe;
import UI.Kundeberater.Kunden.CreateKunde;
import UI.Kundeberater.Kunden.ListCustomers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Kundenberater extends Application {
    @Override
    public void start(Stage kundenberaterStage) {
        kundenberaterStage.setTitle("Kundenberater");
        kundenberaterStage.getIcons().add(new Image("logo.png"));

        //MenuBar
        MenuBar menuBar = new MenuBar();
        Menu vehicles = new Menu("Fahrzeuge");
        MenuItem suchenFahrzeug = new MenuItem("Fahrzeug suchen");
        MenuItem fahrzeugeAuflisten = new MenuItem("Fahrzeuge auflisten");
        vehicles.getItems().addAll(suchenFahrzeug, fahrzeugeAuflisten);
        Menu customers = new Menu("Kunden");
        MenuItem kundeErstellen = new MenuItem("Kunde erfassen");
        MenuItem kundenAuflisten = new MenuItem("Kunden auflisten");
        MenuItem kundenSuchen = new MenuItem("Kunden suchen");
        customers.getItems().addAll(kundeErstellen, kundenAuflisten, kundenSuchen);
        Menu appliaktion = new Menu("Applikation");
        MenuItem ausloggen = new MenuItem("Ausloggen");
        MenuItem beenden = new MenuItem("Schliessen");
        appliaktion.getItems().addAll(ausloggen, beenden);
        menuBar.getMenus().addAll(appliaktion, vehicles, customers);

        //Root layout
        BorderPane rootLayout = new BorderPane();
        rootLayout.setTop(menuBar);
        kundenberaterStage.setScene(new Scene(rootLayout, 500, 300));
        kundenberaterStage.show();

        //Benutzer logt sich aus
        ausloggen.setOnAction(actionEvent -> {
            kundenberaterStage.close();
        });
        //Benutzer beendet applikation
        beenden.setOnAction(actionEvent -> {
            Runtime.getRuntime().exit(0);
        });
        //Fahrzeuge auflisten
        vehicles.setOnAction(actionEvent -> {
            rootLayout.setCenter(FahrzeugListe.list());
        });
        //Fahrzeuge suchen (dasselbe wie auflisten, aber damit der Benutzer nicht verwirrt ist, wo die suchfunktion ist
        suchenFahrzeug.setOnAction(action -> {
            rootLayout.setCenter(FahrzeugListe.list());
        });
        //Kunden auflisten
        kundenAuflisten.setOnAction(event -> {
            rootLayout.setCenter(ListCustomers.list(kundenAuflisten));
        });
        //Kunden sucher auch dasselbe wie auflisten aber als option um den Benutzer nicht zu vewirren
        kundenSuchen.setOnAction(action -> {
            rootLayout.setCenter(ListCustomers.list(kundenAuflisten));
        });
        //kunde erstellen
        kundeErstellen.setOnAction(event -> {
            rootLayout.setCenter(CreateKunde.create(kundenAuflisten));
            kundenberaterStage.setMinHeight(rootLayout.getHeight() + 120);
            kundenberaterStage.setMinWidth(rootLayout.getWidth());
        });

        rootLayout.setCenter(FahrzeugListe.list());
    }
}
