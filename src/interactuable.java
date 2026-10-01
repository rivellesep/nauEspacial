public abstract class interactuable{
    protected String nom;
    protected room zona;
    public interactuable(String nom, room zona){
        this.nom = nom;
        this.zona = zona;
    }

    public abstract boolean interaccio(String verb, String objecte, joc joc);

}