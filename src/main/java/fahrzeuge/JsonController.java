package fahrzeuge;

import logic.Kunde;
import logic.Vermietung;

import java.util.List;

public class JsonController {
    private List<Fahrzeug> vehicles;
    private List<Kunde> kunden;
    private List<Vermietung> vermietungen;

    public List<Vermietung> getVermietungen() {
        return vermietungen;
    }

    public void setVermietungen(List<Vermietung> vermietungen) {
        this.vermietungen = vermietungen;
    }

    public List<Fahrzeug> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Fahrzeug> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(List<Kunde> kunden) {
        this.kunden = kunden;
    }
}
