package UI.Kundeberater.Fahrzeuge;

import Domain.Fahrzeug.Fahrzeug;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FahrzeugDetail {
    public static void detail(Fahrzeug fahrzeug) {
        //Stage
        Stage detailStage = new Stage();
        detailStage.setTitle("Detailansicht");
        detailStage.getIcons().add(new Image("logo.png"));

        //Grid
        GridPane grid = new GridPane();
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(45);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(45);
        grid.getColumnConstraints().addAll(column0, column1, column2);
        Insets groupInset = new Insets(0, 10, 0, 0);
        Insets inGroupInset = new Insets(0, 5, 0, 0);

        //Titel
        Text titel = new Text("Detailansicht");
        titel.setStyle("-fx-font: 24 arial;");
        titel.setTextAlignment(TextAlignment.CENTER);
        grid.add(titel, 1, 0, 3, 1);
        GridPane.setMargin(titel, groupInset);

        //Marke
        Text marketitel = new Text("Marke:");
        grid.add(marketitel, 0, 1);
        GridPane.setMargin(marketitel, groupInset);
        Text marke = new Text();
        grid.add(marke, 0, 2);
        GridPane.setMargin(marke, inGroupInset);

        //Model
        Text modelTitel = new Text("Model:");
        grid.add(modelTitel, 0, 3);
        GridPane.setMargin(modelTitel, groupInset);
        Text model = new Text();
        grid.add(model, 0, 4);
        GridPane.setMargin(model, inGroupInset);

        //Hubraum
        Text hubraumTitel = new Text("Hubraum in ccm:");
        grid.add(hubraumTitel, 0, 5);
        GridPane.setMargin(hubraumTitel, groupInset);
        Text hubraum = new Text();
        grid.add(hubraum, 0, 6);
        GridPane.setMargin(hubraum, inGroupInset);

        //treibstoffart
        Text treibstoffartText = new Text("Treibstoffart:");
        grid.add(treibstoffartText, 0, 7);
        GridPane.setMargin(treibstoffartText, groupInset);
        Text treibstoffart = new Text();
        grid.add(treibstoffart, 0, 8);
        GridPane.setMargin(treibstoffart, inGroupInset);

        detailStage.setScene(new Scene(grid, 500, 700));

        detailStage.show();
    }
}
