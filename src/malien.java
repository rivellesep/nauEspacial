public class malien extends interactuable{
    public static final int TORNS_ENTRE_MOVIMENTS = 2;
    public static final int TORNS_INFECCIO = 7;
    public static final int TORNS_ATURDIT = 4;

    private boolean actiu;
    private boolean mort;
    private int tornsAturdit;
    private int tornsSenseMoure;
    private room zonaAparicio;
    private room salaActivacio;
    private reparable tutorial;

    public malien(room zonaAparicio, room salaActivacio, reparable tutorial){
        super("MALIEN", null);
        this.zonaAparicio = zonaAparicio;
        this.salaActivacio = salaActivacio;
        this.tutorial = tutorial;
        this.actiu = false;
        this.mort = false;
        this.tornsAturdit = 0;
        this.tornsSenseMoure = 0;
    }

    public boolean isActiu(){
        return actiu;
    }

    public boolean isMort(){
        return mort;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!actiu || !verb.equals("USAR")) {
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
        if (!j.jugador.teObjecte("XERINGA")) {
            System.out.println("No tens cap xeringa.");
            return true;
        }
        boolean aqui = jugadorAqui(j);
        if (!j.jugador.infectat && !aqui) {
            System.out.println("No cal gastar una xeringa ara mateix.");
            return true;
        }

        j.jugador.treureObjecte("XERINGA");
        if (j.jugador.infectat) {
            j.jugador.infectat = false;
            j.jugador.statusInfeccio = 0;
            System.out.println("Et claves la xeringa. El líquid blau et neteja la infecció.");
        }
        if (aqui) {
            tornsAturdit = TORNS_ATURDIT + 1;
            System.out.println("El Malien es retorç i queda aturdit.");
        }
        return true;
    }

    private boolean usarPistola(joc j){
        if (!j.jugador.teObjecte("PISTOLA")) {
            System.out.println("No tens cap pistola.");
            return true;
        }
        if (!jugadorAqui(j)) {
            System.out.println("El Malien no és aquí. Millor no malgastar el tret.");
            return true;
        }

        System.out.println("Apuntes i dispares. El Malien cau a terra, immòbil.");
        mort = true;
        actiu = false;
        zona = null;
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
            if (tutorial.isReparat() && j.jugador.zonaActual.equals(salaActivacio.nom)) {
                actiu = true;
                zona = zonaAparicio;
                System.out.println(joc.RED + "Un soroll metàl·lic ressona per la nau... Alguna cosa ha entrat per aquell forat." + joc.RESET);
            }
            return;
        }

        if (j.jugador.infectat) {
            j.jugador.statusInfeccio++;
            int queden = TORNS_INFECCIO - j.jugador.statusInfeccio;
            if (queden <= 0) {
                j.jugador.mort = true;
                j.finalJoc = true;
                System.out.println(joc.RED + "La infecció et consumeix per dins. Caus a terra... Has mort." + joc.RESET);
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

        tornsSenseMoure++;
        if (tornsSenseMoure >= TORNS_ENTRE_MOVIMENTS) {
            tornsSenseMoure = 0;
            moureAleatori(j);
        }

        if (j.donutsParats.contains(zona)) {
            j.donutsParats.remove(zona);
            tornsAturdit = TORNS_ATURDIT;
            System.out.println(joc.YELLOW + "iHall: \"El Malien s'ha distret amb un donut a " + zona.nom + " i s'ha quedat aturdit!\"" + joc.RESET);
            return;
        }

        if (jugadorAqui(j) && !j.jugador.infectat) {
            j.jugador.infectat = true;
            j.jugador.statusInfeccio = 0;
            System.out.println(joc.RED + "El Malien se't tira a sobre! Estàs infectat: tens " + TORNS_INFECCIO + " torns per usar una xeringa." + joc.RESET);
        }
    }

    private void moureAleatori(joc j){
        int idDesti = zona.sortides[(int) (Math.random() * zona.sortides.length)];
        for (int i = 0; i < j.mapaZones.length; i++) {
            if (j.mapaZones[i].identificador == idDesti && !j.mapaZones[i].nom.equals("FORA")) {
                zona = j.mapaZones[i];
                return;
            }
        }
    }
}