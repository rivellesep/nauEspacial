import java.util.ArrayList;
import java.util.List;

/**
 * VERSIÓ PROVISIONAL, només perquè puguis provar App.java ja mateix.
 * Quan el teu company acabi el Room.java definitiu, substitueix aquest
 * fitxer pel seu, sempre que tingui aquests mateixos mètodes.
 */
public class RoomTest {
    private int identificador;
    private String nom;
    private String descripcio;
    private RoomTest[] sortides;
    private List<Item> objectesPresents;

    public RoomTest(int identificador, String nom, String descripcio) {
        this.identificador = identificador;
        this.nom = nom;
        this.descripcio = descripcio;
        this.sortides = new RoomTest[0];
        this.objectesPresents = new ArrayList<>();
    }

    public void setSortides(RoomTest[] sortides) {
        this.sortides = sortides;
    }

    public void afegirObjecte(Item item) {
        objectesPresents.add(item);
    }

    // Igual que al diagrama de classes: gestionarObjectes(Items obj, boolean afegir)
    public void gestionarObjectes(Item obj, boolean afegir) {
        if (afegir) {
            objectesPresents.add(obj);
        } else {
            objectesPresents.remove(obj);
        }
    }

    public void mostrarDescripcio() {
        System.out.println("== " + nom + " ==");
        System.out.println(descripcio);
        if (!objectesPresents.isEmpty()) {
            System.out.print("Veus: ");
            for (int i = 0; i < objectesPresents.size(); i++) {
                System.out.print(objectesPresents.get(i).getNom() + " ");
            }
            System.out.println();
        }
    }

    public String getNom() {
        return nom;
    }

    public RoomTest[] getSortides() {
        return sortides;
    }

    public List<Item> getObjectesPresents() {
        return objectesPresents;
    }
}
