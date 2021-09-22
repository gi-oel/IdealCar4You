package sample.java.fahrzeuge;

public class Motorrad extends Fahrzeug {
    private int tankvolumen;

    protected Motorrad() {

    }

    public Motorrad(String marke, String modell, int hubraum, String treibstoffArtInt, String aussenfarbe, int kmStand, String kennzeichen, String kategorieInt, String verfuegbarAb, String verfuegbarBis, Boolean nichtVerfuegbar, int type, int tankvolumen) {
        super(marke, modell, hubraum, treibstoffArtInt, aussenfarbe, kmStand, kennzeichen, kategorieInt, verfuegbarAb, verfuegbarBis, nichtVerfuegbar, type);
        this.tankvolumen = tankvolumen;
    }

    public int getTankvolumen() {
        return tankvolumen;
    }

    public void setTankvolumen(int tankvolumen) {
        this.tankvolumen = tankvolumen;
    }
}
