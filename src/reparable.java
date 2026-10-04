public class reparable extends interactuable{
    private boolean reparat;
    private String missatgeReparat;
    private objecte recompensa;

    public reparable(room zona, String missatgeReparat, objecte recompensa){
        super("REPARABLE", zona);
        this.missatgeReparat = missatgeReparat;
        this.recompensa = recompensa;
        this.reparat = false;
    }

    public boolean isReparat(){
        return reparat;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!verb.equals("USAR") || !objectiu.equals("EINA") || !jugadorAqui(j)) {
            return false;
        }
        if (reparat) {
            System.out.println("Això ja està reparat.");
            return true;
        }
        if (!j.jugador.teObjecte("EINA")) {
            System.out.println("Necessites l'eina per reparar això.");
            return true;
        }
        if (!joc.skillCheck(60)) {
            System.out.println("Se t'escapa l'eina. Torna-ho a provar.");
            return true;
        }

        reparat = true;
        System.out.println(missatgeReparat);
        if (recompensa != null) {
            zona.objectesPresents.add(recompensa);
            System.out.println("Hi ha " + joc.GREEN + recompensa.nom + joc.RESET + " a terra.");
        }
        return true;
    }
}