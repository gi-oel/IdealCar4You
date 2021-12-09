package Domain.Fahrzeug;

import java.time.LocalDate;
import java.util.List;

public class Transporter extends Fahrzeug {
    private int maxZuladung;

    public Transporter() {
    }

    public Transporter(int maxZuladung, String marke, String model, int hubraum, int treibstoffartID, int aktuellerKMStand, int ps, LocalDate erstzulassung, String color, int leergewicht) {
        super(marke, model, hubraum, treibstoffartID, aktuellerKMStand, ps, erstzulassung, color, leergewicht);
        this.maxZuladung = maxZuladung;
    }

    public int getMaxZuladung() {
        return maxZuladung;
    }

    public void setMaxZuladung(int maxZuladung) {
        this.maxZuladung = maxZuladung;
    }

    public List<String> validateVehicle() {
        return super.validateInputSuper();
    }
}
