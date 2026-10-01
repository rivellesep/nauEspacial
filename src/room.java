public class room{
    int identificador;
    String nom;
    String descripcio;
    int[] sortides;
    objecte[] objectesPresents;
    npc[] npcPresents;


    public room(int identificador, String nom, String descripcio, int[] sortides, objecte[] objectesPresents, npc[] npcPresents) {
        this.identificador = identificador;
        this.nom = nom;
        this.descripcio = descripcio;
        this.sortides = sortides;
        this.objectesPresents = objectesPresents;
        this.npcPresents = npcPresents;
    }
}