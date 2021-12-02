import UI.Fahrzeugparkmanager;
import UI.Kundenberater;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    private static Map<String, String> logins;
    private static List<String> managers;

    public static void main(String[] args) {
        //Add logins to list
        logins = new HashMap<>();
        logins.put("hermann", "starkesPasswort");
        logins.put("fritz", "schwachesPasswort");
        logins.put("amanda", "besteBeraterin");
        logins.put("faeh", "dumm");

        //Setting managers
        managers = new ArrayList<>() {
        };
        managers.add("hermann");
        managers.add("fritz");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IdealCar4You");
        Text login = new Text("Login");
        primaryStage.getIcons().add(new Image("logo.png"));
        login.setStyle("-fx-font: 24 arial;");
        primaryStage.setResizable(false);

        //Grid
        GridPane outerLayout = new GridPane();
        outerLayout.setHgap(10);
        outerLayout.setPadding(new Insets(10, 10, 10, 10));
        outerLayout.setVgap(10);

        //Username
        TextField username = new TextField();
        username.setPromptText("Benutzername");

        //Password
        PasswordField password = new PasswordField();
        password.setPromptText("Passwort");

        //Login Button
        Button loginButton = new Button("Login");

        //Error Text
        Text error = new Text();

        //Onclick
        loginButton.setOnAction(actionEvent -> {
            String usernameString = username.getText();
            String passwordString = password.getText();

            //Check if user exists and password correct
            String errorString = "An Error occured";
            if (!logins.containsKey(usernameString)) {
                errorString = "Benutzer nicht gefunden";
            } else { //Check password
                if (!(Objects.equals(logins.get(usernameString), passwordString))) {
                    errorString = "Falsches Passwort";
                    password.setText("");
                } else { //If password was correct
                    try {
                        Stage newStage = new Stage();
                        //onclose handler
                        newStage.setOnHiding(event -> {
                            System.out.println("User logged out");
                            primaryStage.show();
                        });
                        if (managers.contains(usernameString)) { //Check if user is a manager
                            Fahrzeugparkmanager fahrzeugparkmanager = new Fahrzeugparkmanager();
                            fahrzeugparkmanager.start(newStage);
                        } else { //If user isn't a manager
                            Kundenberater kundenberater = new Kundenberater();
                            kundenberater.start(newStage);
                        }
                        errorString = "Wilkommen " + usernameString;
                        primaryStage.hide();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }
            error.setText(errorString);
        });

        //Default button machen
        loginButton.setDefaultButton(true);

        //Add text
        outerLayout.add(login, 0, 0);
        outerLayout.add(username, 0, 1);
        outerLayout.add(password, 0, 2);
        outerLayout.add(loginButton, 0, 3);
        outerLayout.add(error, 0, 4);

        //Setting scene and show
        primaryStage.setScene(new Scene(outerLayout, 300, 200));
        primaryStage.show();
    }
}
