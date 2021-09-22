import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.java.fahrzeuge.Fahrzeug;

public class ControllerVariables {
    private static Stage stageLogin;
    private static Stage stageUser;
    private static Scene sceneUser;
    private static Scene sceneAuswahl;
    private static int selectedIndex;
    private static Fahrzeug selectedVehicle;
    private static Kunde selectedCustomer;

    public static Fahrzeug getSelectedVehicle() {
        return selectedVehicle;
    }

    public static void setSelectedVehicle(Fahrzeug selectedVehicle) {
        ControllerVariables.selectedVehicle = selectedVehicle;
    }

    public static Kunde getSelectedCustomer() {
        return selectedCustomer;
    }

    public static void setSelectedCustomer(Kunde selectedCustomer) {
        ControllerVariables.selectedCustomer = selectedCustomer;
    }

    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static void setSelectedIndex(int selectedIndex) {
        ControllerVariables.selectedIndex = selectedIndex;
    }

    public static Scene getSceneAuswahl() {
        return sceneAuswahl;
    }

    public static void setSceneAuswahl(Scene sceneAuswahl) {
        ControllerVariables.sceneAuswahl = sceneAuswahl;
    }

    public static Scene getSceneUser() {
        return sceneUser;
    }

    public static void setSceneUser(Scene sceneUser) {
        ControllerVariables.sceneUser = sceneUser;
    }

    public static Stage getStageUser() {
        return stageUser;
    }

    public static void setStageUser(Stage userStage) {
        ControllerVariables.stageUser = userStage;
    }

    public static void setStageLogin(Stage stagee) {
        stageLogin = stagee;
    }

    public static Stage getStageLogin() {
        return stageLogin;
    }

    public static void closeApplication() {
        Platform.exit();
    }
}
