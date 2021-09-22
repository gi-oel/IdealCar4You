package fahrzeuge;

public class Transporter extends Fahrzeug {
    private int ladegewicht;

    protected Transporter() {

    }

    public Transporter(String marke, String modell, int hubraum, String treibstoffArtInt, String aussenfarbe, int kmStand, String kennzeichen, String kategorieInt, String verfuegbarAb, String verfuegbarBis, Boolean nichtVerfuegbar, int type, int ladegewichtInt) {
        super(marke, modell, hubraum, treibstoffArtInt, aussenfarbe, kmStand, kennzeichen, kategorieInt, verfuegbarAb, verfuegbarBis, nichtVerfuegbar, type);
        this.ladegewicht = ladegewichtInt;
    }

    public int getLadegewicht() {
        return ladegewicht;
    }

    public void setLadegewicht(int ladegewicht) {
        this.ladegewicht = ladegewicht;
    }
}
