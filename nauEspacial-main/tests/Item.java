/**
 * Classe pare de tots els objectes del joc.
 * La clau és el mètode usar(): cada subclasse (Llanterna, Donuts, Keycard...)
 * el sobreescriu amb el seu propi comportament. Així, quan App.java fa
 * "item.usar()" no li cal saber DE QUIN item es tracta: cadascú sap fer
 * la seva pròpia cosa.
 */
public abstract class Item {
    private int identificador;
    private String nom;
    private String descripcio;
    private boolean esAgafable;

    public Item(int identificador, String nom, String descripcio, boolean esAgafable) {
        this.identificador = identificador;
        this.nom = nom;
        this.descripcio = descripcio;
        this.esAgafable = esAgafable;
    }

    // Cada subclasse decideix què vol dir "usar-me"
    public abstract void usar();

    public int getIdentificador() {
        return identificador;
    }

    public String getNom() {
        return nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public boolean isEsAgafable() {
        return esAgafable;
    }
}