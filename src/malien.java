public class malien extends interactuable{
    public static final int TORNS_ENTRE_MOVIMENTS = 2;
    public static final int TORNS_INFECCIO = 7;
    public static final int TORNS_ATURDIT = 4;

    private boolean actiu;
    private boolean mort;
    private int tornsAturdit;
    private int tornsDesDeMoviment;
    private room zonaAparicio;
    private room salaActivacio;    // quan el jugador entra aqui (Taller) despres del tutorial, s'activa
    private reparable tutorial;    // la reparacio de Fora

    public malien(room zonaAparicio, room salaActivacio, reparable tutorial){
        super("MALIEN", null);   // no es a cap sala fins que s'activa
        this.zonaAparicio = zonaAparicio;
        this.salaActivacio = salaActivacio;
        this.tutorial = tutorial;
        this.actiu = false;
        this.mort = false;
        this.tornsAturdit = 0;
        this.tornsDesDeMoviment = 0;
    }

    public boolean isActiu(){
        return actiu;
    }

    public boolean isMort(){
        return mort;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!actiu || mort || !verb.equals("USAR")) {
            return false;
        }
        if (objectiu.equals("XERINGA")) {
            return usarXeringa(j);
        }
        if (objectiu.equals("PISTOLA")) {
            return usarPistola(j);
        }
        return false;
    }

    private boolean usarXeringa(joc j){
        if (!teObjecte(j, "Xeringa")) {
            System.out.println("No tens cap xeringa.");
            return true;
        }
        boolean mateixaSala = (zona == salaJugador(j));
        if (!j.jugador.infectat && !mateixaSala) {
            System.out.println("No cal gastar una xeringa ara mateix.");
            return true;
        }

        treureDeInventari(j, "Xeringa");
        if (j.jugador.infectat) {
            j.jugador.infectat = false;
            j.jugador.statusInfeccio = 0;
            System.out.println("Et claves la xeringa. El líquid blau et neteja la infecció.");
        }
        if (mateixaSala) {
            tornsAturdit = TORNS_ATURDIT + 1;
            System.out.println("El Malien es retorç i queda aturdit.");
        }
        return true;
    }

    private boolean usarPistola(joc j){
        if (!teObjecte(j, "Pistola")) {
            System.out.println("No tens cap pistola.");
            return true;
        }
        if (zona != salaJugador(j)) {
            System.out.println("El Malien no és aquí. Millor no malgastar la bala.");
            return true;
        }

        System.out.println("Apuntes i dispares. La bala pesada atravessa el Malien i cau a terra, immòbil.");
        mort = true;
        actiu = false;
        moureA(null);
        j.jugador.infectat = false;
        j.jugador.statusInfeccio = 0;
        return true;
    }

    @Override
    public void tornPassat(joc j){
        if (mort) {
            return;
        }
        if (!actiu) {
            comprovarActivacio(j);
            return;
        }

        if (j.jugador.infectat) {
            j.jugador.statusInfeccio++;
            int queden = TORNS_INFECCIO - j.jugador.statusInfeccio;
            if (queden <= 0) {
                matarJugador(j);
                return;
            }
            String paraula = "torns";
            if (queden == 1) {
                paraula = "torn";
            }
            System.out.println(joc.RED + "Et sents cada cop pitjor... Et queden " + queden + " " + paraula + "." + joc.RESET);
        }

        if (tornsAturdit > 0) {
            tornsAturdit--;
            if (tornsAturdit == 0) {
                System.out.println("El Malien es comença a moure un altre cop.");
            }
            return;
        }

        tornsDesDeMoviment++;
        if (tornsDesDeMoviment >= TORNS_ENTRE_MOVIMENTS) {
            tornsDesDeMoviment = 0;
            moureAleatori(j);
        }

        if (zona == salaJugador(j) && !j.jugador.infectat) {
            j.jugador.infectat = true;
            j.jugador.statusInfeccio = 0;
            System.out.println(joc.RED + "El Malien se't tira a sobre! Estàs infectat: tens " + TORNS_INFECCIO + " torns per usar una xeringa." + joc.RESET);
        }
    }

    private void comprovarActivacio(joc j){
        if (tutorial.isReparat() && salaJugador(j) == salaActivacio) {
            actiu = true;
            moureA(zonaAparicio);
            System.out.println(joc.RED + "Un soroll metàl·lic ressona per la nau... Alguna cosa ha entrat per aquell forat." + joc.RESET);
        }
    }

    private void moureAleatori(joc j){
        int pos = (int) (Math.random() * zona.sortides.length);
        room desti = salaPerId(j, zona.sortides[pos]);
        if (desti != null && !desti.nom.equals("FORA")) {
            moureA(desti);
        }
    }

    private void matarJugador(joc j){
        j.jugador.mort = true;
        j.finalJoc = true;
        System.out.println(joc.RED + "La infecció et consumeix per dins. Caus a terra... Has mort." + joc.RESET);
    }
}