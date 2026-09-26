public class player {
    objecte[] inventari;
    int statusInfeccio;
    boolean infectat;
    boolean mort;
    room zonaActual;
    boolean teVestit;

    public player(objecte[] inventari, int statusInfeccio, boolean infectat, boolean mort, room zonaActual, boolean teVestit){
        this.inventari = inventari;
        this.statusInfeccio = statusInfeccio;
        this.infectat = infectat;
        this.mort = mort;
        this.zonaActual = zonaActual;
        this.teVestit = teVestit;
    }
}