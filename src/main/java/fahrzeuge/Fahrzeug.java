package sample.java.fahrzeuge;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Auto.class, name = "Auto"),
        @JsonSubTypes.Type(value = Motorrad.class, name = "Motorrad"),
        @JsonSubTypes.Type(value = Transporter.class, name = "Transporter"),
})
public abstract class Fahrzeug {
    private String marke;
    private String modell;
    private int hubraum;
    private String treibstoffArt;
    private String aussenfarbe;
    private int kmStand;
    private String kennzeichen;
    private String kategorie;
    private String verfuegbarAb;
    private String verfuegbarBis;
    private Boolean nichtVerfuegbar;
    private int type;

    protected Fahrzeug() {

    }

    protected Fahrzeug(String marke, String modell, int hubraum, String treibstoffArtInt, String aussenfarbe, int kmStand, String kennzeichen, String kategorieInt, String verfuegbarAb, String verfuegbarBis, Boolean nichtVerfuegbar, int type) {
        this.marke = marke;
        this.modell = modell;
        this.hubraum = hubraum;
        this.treibstoffArt = treibstoffArtInt;
        this.kategorie = kategorieInt;
        this.aussenfarbe = aussenfarbe;
        this.kmStand = kmStand;
        this.kennzeichen = kennzeichen;
        this.verfuegbarAb = verfuegbarAb;
        this.verfuegbarBis = verfuegbarBis;
        this.nichtVerfuegbar = nichtVerfuegbar;
        this.type = type;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getHubraum() {
        return hubraum;
    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public String getTreibstoffArt() {
        return treibstoffArt;
    }

    public void setTreibstoffArt(String treibstoffArt) {
        this.treibstoffArt = treibstoffArt;
    }

    public String getAussenfarbe() {
        return aussenfarbe;
    }

    public void setAussenfarbe(String aussenfarbe) {
        this.aussenfarbe = aussenfarbe;
    }

    public int getKmStand() {
        return kmStand;
    }

    public void setKmStand(int kmStand) {
        this.kmStand = kmStand;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getVerfuegbarAb() {
        return verfuegbarAb;
    }

    public void setVerfuegbarAb(String verfuegbarAb) {
        this.verfuegbarAb = verfuegbarAb;
    }

    public String getVerfuegbarBis() {
        return verfuegbarBis;
    }

    public void setVerfuegbarBis(String verfuegbarBis) {
        this.verfuegbarBis = verfuegbarBis;
    }

    public Boolean getNichtVerfuegbar() {
        return nichtVerfuegbar;
    }

    public void setNichtVerfuegbar(Boolean nichtVerfuegbar) {
        this.nichtVerfuegbar = nichtVerfuegbar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
