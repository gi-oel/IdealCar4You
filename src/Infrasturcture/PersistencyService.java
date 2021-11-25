package Infrasturcture;

import Domain.Fahrzeug.Fahrzeug;
import Domain.Kunde.Kunde;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
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

    public void saveData() throws IOException {
        //Objectmapper wird instanziert
        ObjectMapper om = new ObjectMapper();

        //Pfad der datei wird definiert
        File datenFile = new File("daten.json");

        //Wenn die file schon existiert
        if (datenFile.isFile()) {
            System.out.println("Datei existiert schon");
        } else {
            //file wird erstellt
            if (datenFile.createNewFile()) {
                System.out.println(datenFile.getName() + " wurde erstellt!");
            } else {
                System.out.println("Es wurde keine Datei erstellt");
            }
        }

        //Convert dates to strings
        for (int i = 0; i < this.listService.getFahrzeuge().size(); i++) {
            Fahrzeug fahrzeug = this.listService.getFahrzeug(i);
            fahrzeug.setErstzulassungString(fahrzeug.getErstzulassung().toString());
            this.listService.setFahrzeug(i, fahrzeug);
        }
        om.enable(SerializationFeature.INDENT_OUTPUT);
        om.writeValue(datenFile, listService);
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

    public Fahrzeug getFahrzeug(int index) {
        return this.fahrzeuge.get(index);
    }

    public void setFahrzeug(int index, Fahrzeug fahrzeug) {
        this.fahrzeuge.set(index, fahrzeug);
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

    public Kunde getKunde(int index) {
        return this.kunden.get(index);
    }

    public void setKunde(int index, Kunde kunde) {
        this.kunden.set(index, kunde);
    }
}
