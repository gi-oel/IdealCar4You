package logic;

import fahrzeuge.Fahrzeug;

public class Vermietung {
    private Fahrzeug fahrzeug;
    private Kunde kunde;
    private float mietdauer;
    private double preis;
    private String status;

    public Vermietung(){

    }

    public Vermietung(Fahrzeug fahrzeug, Kunde kunde, float mietdauer, double preis, String status) {
        this.fahrzeug = fahrzeug;
        this.kunde = kunde;
        this.mietdauer = mietdauer;
        this.preis = preis;
        this.status = status;
    }

    public Fahrzeug getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeug = fahrzeug;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public float getMietdauer() {
        return mietdauer;
    }

    public void setMietdauer(float mietdauer) {
        this.mietdauer = mietdauer;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
