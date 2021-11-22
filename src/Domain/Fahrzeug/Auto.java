package Domain.Fahrzeug;

import java.time.LocalDate;

public class Auto extends Fahrzeug {
    private int aufbauID;
    private boolean navigation;
    private final String[] aufbau = {"Kleinwagen", "Limousine", "Kombi", "SUV", "Cabriolet"};

    public Auto() {
    }

    public Auto(int aufbauID, boolean navigation, String marke, String model, int hubraum, int treibstoffartID, int aktuellerKMStand, int ps, LocalDate erstzulassung, String color, int leergewicht) {
        super(marke, model, hubraum, treibstoffartID, aktuellerKMStand, ps, erstzulassung, color, leergewicht);
        this.aufbauID = aufbauID;
        this.navigation = navigation;
    }

    public int getAufbauID() {
        return aufbauID;
    }

    public void setAufbauID(int aufbauID) {
        this.aufbauID = aufbauID;
    }

    public boolean isNavigation() {
        return navigation;
    }

    public void setNavigation(boolean navigation) {
        this.navigation = navigation;
    }

    public String[] getAufbau() {
        return aufbau;
    }
}
