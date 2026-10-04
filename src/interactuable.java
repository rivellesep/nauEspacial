public abstract class interactuable{
    protected String nom;
    protected room zona;

    public interactuable(String nom, room zona){
        this.nom = nom;
        this.zona = zona;
    }

    public room getZona(){
        return zona;
    }

    public abstract boolean interaccio(String verb, String objectiu, joc j);

    public void tornPassat(joc j){
    }

    protected boolean jugadorAqui(joc j){
        return zona != null && zona.nom.equals(j.jugador.zonaActual);
    }
}