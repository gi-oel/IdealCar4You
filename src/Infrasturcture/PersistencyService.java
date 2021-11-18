package Infrasturcture;

import Domain.Fahrzeug.Fahrzeug;
import Domain.Kunde.Kunde;

import java.util.ArrayList;
import java.util.List;

public class PersistencyService {
    private ListService listService;

    public PersistencyService() {
        listService = new ListService();
    }

    public PersistencyService(List<Fahrzeug> fahrzeuge, List<Kunde> kunden) {
        listService = new ListService(kunden, fahrzeuge);
    }

    public void saveToJson() {

    }
}

class ListService {
    private List<Fahrzeug> fahrzeuge;
    private List<Kunde> kunden;

    public ListService() {
        this.fahrzeuge = new ArrayList<>();
        this.kunden = new ArrayList<>();
    }

    public ListService(List<Kunde> kunden, List<Fahrzeug> fahrzeuge) {
        this.fahrzeuge = fahrzeuge;
        this.kunden = kunden;
    }

    public List<Fahrzeug> getFahrzeuge() {
        return fahrzeuge;
    }

    public void setFahrzeuge(List<Fahrzeug> fahrzeuge) {
        this.fahrzeuge = fahrzeuge;
    }

    public List<Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(List<Kunde> kunden) {
        this.kunden = kunden;
    }
}
