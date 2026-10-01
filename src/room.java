public class room{
    int identificador;
    String nom;
    String descripcio;
    int[] sortides;
    objecte[] objectesPresents;
    interactuable[] interactuablesPresents;


    public room(int identificador, String nom, String descripcio, int[] sortides, objecte[] objectesPresents, interactuable[] interactuablesPresents) {
        this.identificador = identificador;
        this.nom = nom;
        this.descripcio = descripcio;
        this.sortides = sortides;
        this.objectesPresents = objectesPresents;
        this.interactuablesPresents = interactuablesPresents;
    }
}