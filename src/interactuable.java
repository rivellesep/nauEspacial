public abstract class interactuable{
    protected String nom;
    protected room zona;
 
    public interactuable(String nom, room zona){
        this.nom = nom;
        this.zona = zona;
        if (zona != null) {
            afegirASala(zona);
        }
    }
 
    public String getNom(){
        return nom;
    }
 
    public room getZona(){
        return zona;
    }
 
    public abstract boolean interaccio(String verb, String objectiu, joc j);
 
    public void tornPassat(joc j){
        // buit
    }
 
    public void moureA(room novaZona){
        if (zona != null) {
            treureDeSala(zona);
        }
        zona = novaZona;
        if (novaZona != null) {
            afegirASala(novaZona);
        }
    }
 
 
    private void afegirASala(room r){
        interactuable[] nou = new interactuable[r.interactuablesPresents.length + 1];
        for (int i = 0; i < r.interactuablesPresents.length; i++) {
            nou[i] = r.interactuablesPresents[i];
        }
        nou[nou.length - 1] = this;
        r.interactuablesPresents = nou;
    }
 
    private void treureDeSala(room r){
        int pos = -1; // -1 perque 
        for (int i = 0; i < r.interactuablesPresents.length; i++) {
            if (r.interactuablesPresents[i] == this) {
                pos = i;
            }
        }
        if (pos == -1) {
            return;
        }
        interactuable[] nou = new interactuable[r.interactuablesPresents.length - 1];
        int k = 0;
        for (int i = 0; i < r.interactuablesPresents.length; i++) {
            if (i != pos) {
                nou[k] = r.interactuablesPresents[i];
                k++;
            }
        }
        r.interactuablesPresents = nou;
    }
 
    // Deixa un objecte al terra d'una sala
    protected void deixarASala(room r, objecte o){
        objecte[] nou = new objecte[r.objectesPresents.length + 1];
        for (int i = 0; i < r.objectesPresents.length; i++) {
            nou[i] = r.objectesPresents[i];
        }
        nou[nou.length - 1] = o;
        r.objectesPresents = nou;
    }
 
 
    protected room salaJugador(joc j){
        for (int i = 0; i < j.mapaZones.length; i++) {
            if (j.mapaZones[i].nom.equals(j.jugador.zonaActual)) {
                return j.mapaZones[i];
            }
        }
        return null;
    }
 
    protected room salaPerId(joc j, int id){
        for (int i = 0; i < j.mapaZones.length; i++) {
            if (j.mapaZones[i].identificador == id) {
                return j.mapaZones[i];
            }
        }
        return null;
    }
 
    // L'inventari del jugador arriba a ser null a joc; aixi no peta
    private void assegurarInventari(joc j){
        if (j.jugador.inventari == null) {
            j.jugador.inventari = new objecte[6];
        }
    }
 
    protected boolean teObjecte(joc j, String nomObjecte){
        assegurarInventari(j);
        for (int i = 0; i < j.jugador.inventari.length; i++) {
            if (j.jugador.inventari[i] != null && j.jugador.inventari[i].nom.equalsIgnoreCase(nomObjecte)) {
                return true;
            }
        }
        return false;
    }
 
    protected boolean inventariPle(joc j){
        assegurarInventari(j);
        for (int i = 0; i < j.jugador.inventari.length; i++) {
            if (j.jugador.inventari[i] == null) {
                return false;
            }
        }
        return true;
    }
 
    protected boolean afegirAInventari(joc j, objecte o){
        assegurarInventari(j);
        for (int i = 0; i < j.jugador.inventari.length; i++) {
            if (j.jugador.inventari[i] == null) {
                j.jugador.inventari[i] = o;
                return true;
            }
        }
        return false;
    }
 
    protected void treureDeInventari(joc j, String nomObjecte){
        assegurarInventari(j);
        for (int i = 0; i < j.jugador.inventari.length; i++) {
            if (j.jugador.inventari[i] != null && j.jugador.inventari[i].nom.equalsIgnoreCase(nomObjecte)) {
                j.jugador.inventari[i] = null;
                return;
            }
        }
    }
}