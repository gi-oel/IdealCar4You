package UI.Kundeberater.Kunden;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CreateKunde {
    public static BorderPane create() {
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

        root.setCenter(grid);
        return root;
    }
}
