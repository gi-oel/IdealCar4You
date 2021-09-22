package sample.java.fahrzeuge;

public class Auto extends Fahrzeug {
    private int kofferraumPlatz;
    private String aufbau;
    private Boolean navigation;

    protected Auto() {

    }

    public Auto(String marke, String modell, int hubraum, String treibstoffArtInt, String aussenfarbe, int kmStand, String kennzeichen, String kategorieInt, String verfuegbarAb, String verfuegbarBis, Boolean nichtVerfuegbar, int type, int kofferraumPlatz, String aufbauInt, Boolean navigation) {
        super(marke, modell, hubraum, treibstoffArtInt, aussenfarbe, kmStand, kennzeichen, kategorieInt, verfuegbarAb, verfuegbarBis, nichtVerfuegbar, type);
        this.kofferraumPlatz = kofferraumPlatz;
        this.navigation = navigation;
        this.aufbau = aufbauInt;
    }

    public int getKofferraumPlatz() {
        return kofferraumPlatz;
    }

    public void setKofferraumPlatz(int kofferraumPlatz) {
        this.kofferraumPlatz = kofferraumPlatz;
    }

    public String getAufbau() {
        return aufbau;
    }

    public void setAufbau(String aufbau) {
        this.aufbau = aufbau;
    }

    public Boolean getNavigation() {
        return navigation;
    }

    public void setNavigation(Boolean navigation) {
        this.navigation = navigation;
    }
}
