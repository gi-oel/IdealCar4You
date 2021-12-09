package Infrasturcture;

import Domain.Fahrzeug.Fahrzeug;
import Domain.Kunde.Kunde;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistencyService {
    private ListService listService;

    public PersistencyService() {
        listService = new ListService();

        //Liste wird geladen
        loadList();
    }

    public PersistencyService(List<Fahrzeug> fahrzeuge, List<Kunde> kunden) {
        listService = new ListService(kunden, fahrzeuge);

        //Liste wird geladen
        loadList();
    }

    public void loadList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.listService = objectMapper.readValue(Paths.get("daten.json").toFile(), ListService.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Daten werden in datum konvertiert
        for (int i = 0; i < this.listService.getFahrzeuge().size(); i++) {
            Fahrzeug changer = this.listService.getFahrzeug(i);
            changer.setErstzulassung(LocalDate.parse(changer.getErstzulassungString()));
            this.listService.setFahrzeug(i, changer);
        }
        for (int i = 0; i < this.listService.getKunden().size(); i++) {
            Kunde changer = this.listService.getKunde(i);
            changer.setGeburtsdatum(LocalDate.parse(changer.getGeburtsdatumString()));
            this.listService.setKunde(i, changer);
        }


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

        //Datum zu strings konvertieren
        for (int i = 0; i < this.listService.getFahrzeuge().size(); i++) {
            Fahrzeug fahrzeug = this.listService.getFahrzeug(i);
            fahrzeug.setErstzulassungString(fahrzeug.getErstzulassung().toString());
            this.listService.setFahrzeug(i, fahrzeug);
        }
        for (int i = 0; i < this.listService.getKunden().size(); i++) {
            Kunde kunde = this.listService.getKunde(i);
            kunde.setGeburtsdatumString(kunde.getGeburtsdatum().toString());
            this.listService.setKunde(i, kunde);
        }
        om.enable(SerializationFeature.INDENT_OUTPUT);
        om.writeValue(datenFile, listService);
    }

    public void addFahrzeug(Fahrzeug fahrzeug) throws IOException {
        this.listService.addFahrzeug(fahrzeug);
        saveData();
    }

    public void addKunde(Kunde kunde) throws IOException {
        this.listService.addKunde(kunde);
        saveData();
    }

    public void deleteKunde(Kunde kunde) throws IOException {
        this.listService.deleteKunde(kunde);
        saveData();
    }

    public void deleteFahrzeug(Fahrzeug fahrzeug) throws IOException {
        this.listService.deleteFahrzeug(fahrzeug);
        saveData();
    }

    public void setFahrzeug(int index, Fahrzeug fahrzeug) throws IOException {
        this.listService.setFahrzeug(index, fahrzeug);
        saveData();
    }

    public Fahrzeug getFahrzeug(int index) {
        return this.listService.getFahrzeug(index);
    }

    public Kunde getkunde(int index) {
        return this.listService.getKunde(index);
    }

    public void setKunde(int index, Kunde kunde) throws IOException {
        this.listService.setKunde(index, kunde);
        saveData();
    }

    public List<Fahrzeug> getFahrzeuge() {
        return this.listService.getFahrzeuge();
    }

    public List<Kunde> getKunden() {
        return this.listService.getKunden();
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

    public void addFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeuge.add(fahrzeug);
    }

    public void deleteFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeuge.remove(fahrzeug);
    }

    public List<Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(List<Kunde> kunden) {
        this.kunden = kunden;
    }

    public void addKunde(Kunde kunde) {
        this.kunden.add(kunde);
    }

    public void deleteKunde(Kunde kunde) {
        this.kunden.remove(kunde);
    }

    public Kunde getKunde(int index) {
        return this.kunden.get(index);
    }

    public void setKunde(int index, Kunde kunde) {
        this.kunden.set(index, kunde);
    }
}
