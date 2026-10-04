public class reparable extends interactuable{
    private boolean reparat;
    private String missatgeReparat;
    private objecte recompensa;

    public reparable(String nom, room zona, String missatgeReparat, objecte recompensa){
        super(nom, zona);
        this.missatgeReparat = missatgeReparat;
        this.recompensa = recompensa;
        this.reparat = false;
    }

    public boolean isReparat(){
        return reparat;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!verb.equals("USAR") || !objectiu.equals("EINA")) {
            return false;
        }
        if (reparat) {
            System.out.println("Això ja ho has reparat.");
            return true;
        }
        if (!teObjecte(j, "Eina")) {
            System.out.println("Necessites l'eina per reparar això.");
            return true;
        }

        reparat = true;
        System.out.println(missatgeReparat);
        if (recompensa != null) {
            deixarASala(zona, recompensa);
        }
        return true;
    }
}