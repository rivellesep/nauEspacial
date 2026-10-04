import java.util.ArrayList;
import java.util.Arrays;

public class room{
    int identificador;
    String nom;
    String descripcio;
    int[] sortides;
    ArrayList<objecte> objectesPresents;
    interactuable[] interactuablesPresents;


    public room(int identificador, String nom, String descripcio, int[] sortides, objecte[] objectesPresents, interactuable[] interactuablesPresents) {
        this.identificador = identificador;
        this.nom = nom;
        this.descripcio = descripcio;
        this.sortides = sortides;
        this.objectesPresents = new ArrayList<>(Arrays.asList(objectesPresents));
        this.interactuablesPresents = interactuablesPresents;
    }

    // Busca un objecte pel nom que surt a la comanda (EINA, VESTIT, CLAU...)
    public objecte buscarObjecte(String nomComanda){
        for (int i = 0; i < objectesPresents.size(); i++) {
            if (objectesPresents.get(i).nom.toUpperCase().startsWith(nomComanda)) {
                return objectesPresents.get(i);
            }
        }
        return null;
    }
}