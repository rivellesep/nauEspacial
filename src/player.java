public class player {
    objecte[] inventari = new objecte[6];
    int statusInfeccio;
    boolean infectat;
    boolean mort;
    String zonaActual;
    boolean teVestit;
    String mapaDigital =
        "                             ┌──────────────────┐\n" +
        "                             │     D.ROBERT     │\n" +
        "                             └────────┬─────────┘\n" +
        "                 ┌────────────────────┴────────────────────────┐\n" +
        "                 │                   PASSADIS                  │─────────┐\n" +
        "                 └───────┬─────────────────────────┬───────────┘         │\n" +
        "                         │                         │                     │\n" +
        " ┌─────────────────┐ ┌───┴────────────────────┐  ┌─┴─────┐           ┌───┴────┐   ┌──────────────────┐  ┌──────┐\n" +
        " │     D.ADER      │ │                        ├──┤ CUINA │           │ TALLER ├───┤ SORTIDA EXTERIOR ├──┤ FORA │\n" +
        " └────────┬────────┘ │                        │  └───────┘           └───┬────┘   └──────────────────┘  └──────┘\n" +
        "          │          │        MENJADOR        │                          │\n" +
        " ┌────────┴────────┐ │                        │                     ┌────┴─────┐\n" +
        " │    NAVEGACIO    ├─┤                        │                     │ SISTEMES │\n" +
        " └─────────────────┘ └──────┬──────────┬──────┘                     └──────────┘\n" +
        "                            │          │\n" +
        " ┌────────────┐ ┌───────────┴──────┐ ┌─┴─────┐\n" +
        " │ INFERMERIA ├─┤     D.ELSTER     ├─┤ BANYS │\n" +
        " └────────────┘ └──────────────────┘ └───────┘";



    public player(objecte[] inventari, int statusInfeccio, boolean infectat, boolean mort, String zonaActual, boolean teVestit){
        this.inventari = inventari;
        this.statusInfeccio = statusInfeccio;
        this.infectat = infectat;
        this.mort = mort;
        this.zonaActual = zonaActual;
        this.teVestit = teVestit;
    }

    public void iHall(joc j){
        String opcio = "0";
        do {
            System.out.println("1. Veure mapa");
            System.out.println("2. Comprovar malien");
            System.out.println("3. salir");
            System.out.println("4. Minijuego");
            System.out.println("5. Veure inventari");
            System.out.print("> ");
            opcio = joc.sc.nextLine().trim();
            switch (opcio) {
                case "1":
                    veureMapa(j);
                    break;
                case "2":
                    veureMalien(j);
                    break;

                case "3":

                    break;

                case "4":
                    if (joc.skillCheck(15)) {
                        System.out.println("Has superat el minijoc!");

                    } else {
                        System.out.println("Has fallat el minijoc...");
                    }
                    break;

                case "5":
                    mostrarInventari();
                    break;

                default:
                    break;
            }
        } while (!(opcio.equals("3")));
        opcio = "0";
    }

    // Pinta una zona del mapa amb un color de fons
    private String marcar(String mapa, String nomZona, String color){
        String zona = " " + nomZona + " ";
        return mapa.replace(zona, color + zona + joc.RESET);
    }

    // On ets tu (blau) i on queda reparar (vermell)
    public void veureMapa(joc j){
        String mapa = mapaDigital;
        for (int i = 0; i < j.reparacions.length; i++) {
            if (!j.reparacions[i].isReparat()) {
                mapa = marcar(mapa, j.reparacions[i].getZona().nom, joc.SUB_RED);
            }
        }
        mapa = marcar(mapa, zonaActual, joc.SUB_BLUE); // l'últim, perquè guanyi el blau
        System.out.println(mapa);
        System.out.println(joc.SUB_BLUE + "  " + joc.RESET + " Ets aquí   " + joc.SUB_RED + "  " + joc.RESET + " Per reparar");
    }

    // Només surt el Malien (verd)
    public void veureMalien(joc j){
        if (j.elMalien.isMort()) {
            System.out.println(joc.YELLOW + "iHall: \"El senyal del Malien ha desaparegut. Ja no es mou.\"" + joc.RESET);
            return;
        }
        if (!j.elMalien.isActiu()) {
            System.out.println(joc.YELLOW + "iHall: \"No detecto cap forma de vida estranya a la nau.\"" + joc.RESET);
            return;
        }
        String sala = j.elMalien.getZona().nom;
        System.out.println(marcar(mapaDigital, sala, joc.SUB_GREEN));
        System.out.println(joc.YELLOW + "iHall: \"El Malien és a " + sala + ".\"" + joc.RESET);
    }

    // ===== INVENTARI =====
    // nomComanda és com surt a la comanda: EINA, XERINGA, CLAU...

    private int posicioObjecte(String nomComanda){
        for (int i = 0; i < inventari.length; i++) {
            if (inventari[i] != null && inventari[i].nom.toUpperCase().startsWith(nomComanda)) {
                return i;
            }
        }
        return -1;
    }

    public boolean teObjecte(String nomComanda){
        return posicioObjecte(nomComanda) != -1;
    }

    // Treu l'objecte de l'inventari i el retorna (null si no el tenies)
    public objecte treureObjecte(String nomComanda){
        int pos = posicioObjecte(nomComanda);
        if (pos == -1) {
            return null;
        }
        objecte o = inventari[pos];
        inventari[pos] = null;
        return o;
    }

    public boolean inventariPle(){
        for (int i = 0; i < inventari.length; i++) {
            if (inventari[i] == null) {
                return false;
            }
        }
        return true;
    }

    // Retorna false si no hi cap
    public boolean afegirObjecte(objecte o){
        for (int i = 0; i < inventari.length; i++) {
            if (inventari[i] == null) {
                inventari[i] = o;
                return true;
            }
        }
        return false;
    }

    public void mostrarInventari(){
        System.out.println("Inventari:");
        boolean buit = true;
        for (int i = 0; i < inventari.length; i++) {
            if (inventari[i] != null) {
                System.out.println(joc.GREEN + "  - " + inventari[i].nom + joc.RESET + ": " + inventari[i].descripcio);
                buit = false;
            }
        }
        if (buit) {
            System.out.println("  (buit)");
        }
    }
}