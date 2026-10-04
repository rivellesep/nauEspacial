public class player {
    objecte[] inventari = new objecte[6];
    int statusInfeccio;
    boolean infectat;
    boolean mort;
    String zonaActual;
    boolean teVestit;

    public player(objecte[] inventari, int statusInfeccio, boolean infectat, boolean mort, String zonaActual, boolean teVestit){
        this.inventari = inventari;
        this.statusInfeccio = statusInfeccio;
        this.infectat = infectat;
        this.mort = mort;
        this.zonaActual = zonaActual;
        this.teVestit = teVestit;
    }
}