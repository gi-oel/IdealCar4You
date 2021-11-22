package Domain.Fahrzeug;

public class Transporter extends Fahrzeug {
    private int maxZuladung;

    public Transporter() {
    }

    public Transporter(int maxZuladung) {
        super();
        this.maxZuladung = maxZuladung;
    }

    public int getMaxZuladung() {
        return maxZuladung;
    }

    public void setMaxZuladung(int maxZuladung) {
        this.maxZuladung = maxZuladung;
    }
}
