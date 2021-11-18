package Domain.Fahrzeug;

import java.time.LocalDate;

public abstract class Fahrzeug {
    private String marke;
    private String Model;
    private int hubraum;
    private int treibstoffartID;
    private int aktuellerKMStand;
    private int ps;
    private LocalDate erstzulassung;
    private String color;
    private int leergewicht;
    private String[] treibstoffart = {"Benzin", "Diesel", "Elektrisch"};
}
