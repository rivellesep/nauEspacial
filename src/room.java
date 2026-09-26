public class room{
    int identificador;
    String nom;
    int[] sortides;
    objecte[] objectesPresents;
    npc[] npcPresents;

    public room(int identificador, String nom, int[] sortides, objecte[] objectesPresents, npc[] npcPresents) {
        this.identificador = identificador;
        this.nom = nom;
        this.sortides = sortides;
        this.objectesPresents = objectesPresents;
        this.npcPresents = npcPresents;
    }
}