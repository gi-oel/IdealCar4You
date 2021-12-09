package Domain.Fahrzeug;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Auto extends Fahrzeug {
    private int aufbauID;
    private boolean navigation;
    @JsonIgnore
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

    public void setAufbauID(String aufbau) {
        for (int i = 0; i < this.aufbau.length; i++) {
            if (Objects.equals(this.aufbau[i], aufbau)) {
                this.aufbauID = i;
            }
        }
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

    @Override
    public List<String> validateVehicle() {
        return super.validateInputSuper();
    }
}
