import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setTitle("IdealCar4You");
        primaryStage.setScene(new Scene(root, 500, 250));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.png")));
        primaryStage.show();

        //Die Stage wird dem Controller übergeben
        ControllerVariables.setStageLogin(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }

}

