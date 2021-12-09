package Domain.Fahrzeug;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Auto.class, name = "Auto"),
        @JsonSubTypes.Type(value = Transporter.class, name = "Transporter")
})
public abstract class Fahrzeug {
    private String marke;
    private String model;
    private int hubraum;
    private int treibstoffartID;
    private int aktuellerKMStand;
    private int ps;
    @JsonIgnore
    private LocalDate erstzulassung;
    private String erstzulassungString; //Um in json zu speichern
    private String color;
    private int leergewicht;
    @JsonIgnore
    private final String[] treibstoffart = {"Benzin", "Diesel", "Elektrisch"};

    protected Fahrzeug(String marke, String model, int hubraum, int treibstoffartID, int aktuellerKMStand, int ps, LocalDate erstzulassung, String color, int leergewicht) {
        this.marke = marke;
        this.model = model;
        this.hubraum = hubraum;
        this.treibstoffartID = treibstoffartID;
        this.aktuellerKMStand = aktuellerKMStand;
        this.ps = ps;
        this.erstzulassung = erstzulassung;
        this.color = color;
        this.leergewicht = leergewicht;
    }

    protected Fahrzeug() {
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHubraum() {
        return hubraum;
    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public int getTreibstoffartID() {
        return treibstoffartID;
    }

    public void setTreibstoffartID(String treibstoffart) {
        for (int i = 0; i < this.treibstoffart.length; i++) {
            if (Objects.equals(this.treibstoffart[i], treibstoffart)) {
                this.treibstoffartID = i;
                i = this.treibstoffart.length;
            }
        }
    }

    public int getAktuellerKMStand() {
        return aktuellerKMStand;
    }

    public void setAktuellerKMStand(int aktuellerKMStand) {
        this.aktuellerKMStand = aktuellerKMStand;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public LocalDate getErstzulassung() {
        return erstzulassung;
    }

    public void setErstzulassung(LocalDate erstzulassung) {
        this.erstzulassung = erstzulassung;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLeergewicht() {
        return leergewicht;
    }

    public void setLeergewicht(int leergewicht) {
        this.leergewicht = leergewicht;
    }

    public String[] getTreibstoffart() {
        return treibstoffart;
    }

    public String getErstzulassungString() {
        return erstzulassungString;
    }

    public void setErstzulassungString(String erstzulassungString) {
        this.erstzulassungString = erstzulassungString;
    }

    public List<String> validateInputSuper() {
        List<String> missing = new ArrayList<>();
        if (this.marke.equals("")) {
            missing.add("Marke");
        }
        if (this.model.equals("")) {
            missing.add("Model");
        }
        if (this.erstzulassung == null) {
            missing.add("Erstzulassung");
        }
        if (this.color.equals("")) {
            missing.add("Farbe");
        }
        return missing;
    }

    public abstract List<String> validateVehicle();
}
