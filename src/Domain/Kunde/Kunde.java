package Domain.Kunde;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Kunde {
    private String name;
    private String vorname;
    private String strasseUndNr;
    private int plz;
    private String wohnort;
    private String telefonPriv;
    private String telefonMob;
    private String email;
    private String geburtsdatumString; //Für json speicherung
    @JsonIgnore
    private LocalDate geburtsdatum;

    public Kunde() {

    }

    public Kunde(String name, String vorname, String strasseUndNr, int plz, String wohnort, String telefonPriv, String telefonMob, String email, LocalDate geburtsdatum) {
        this.name = name;
        this.vorname = vorname;
        this.strasseUndNr = strasseUndNr;
        this.plz = plz;
        this.wohnort = wohnort;
        this.telefonPriv = telefonPriv;
        this.telefonMob = telefonMob;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getStrasseUndNr() {
        return strasseUndNr;
    }

    public void setStrasseUndNr(String strasseUndNr) {
        this.strasseUndNr = strasseUndNr;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getWohnort() {
        return wohnort;
    }

    public void setWohnort(String wohnort) {
        this.wohnort = wohnort;
    }

    public String getTelefonPriv() {
        return telefonPriv;
    }

    public void setTelefonPriv(String telefonPriv) {
        this.telefonPriv = telefonPriv;
    }

    public String getTelefonMob() {
        return telefonMob;
    }

    public void setTelefonMob(String telefonMob) {
        this.telefonMob = telefonMob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getGeburtsdatumString() {
        return geburtsdatumString;
    }

    public void setGeburtsdatumString(String geburtsdatumString) {
        this.geburtsdatumString = geburtsdatumString;
    }
}
