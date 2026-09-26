public class TestMoviment {

    public static void main(String[] args) {

        // ----- Mapa petit d'exemple -----
        RoomTest taller = new RoomTest(1, "TALLER", "Un taller ple d'eines. Fa una mica de fosca.");
        RoomTest passadis = new RoomTest(2, "PASSADIS", "Un passadis llarg i buit.");
        RoomTest dormitori = new RoomTest(3, "DORMITORI", "La teva habitacio, ordenada com sempre.");
        RoomTest cuina = new RoomTest(4, "CUINA", "La cuina de la nau, plena d'estris.");

        taller.setSortides(new RoomTest[] { passadis });
        passadis.setSortides(new RoomTest[] { taller, dormitori, cuina });
        dormitori.setSortides(new RoomTest[] { passadis });
        cuina.setSortides(new RoomTest[] { passadis });

        RoomTest[] totesLesZones = { taller, passadis, dormitori, cuina };

        // ----- Parser -----
        parser p = new parser();
        p.setZonesPermeses(nomsDe(totesLesZones));
        p.setItemsPermesos(new String[] {}); // encara no ens calen items per aquest test

        // ----- Zona actual (aqui no fem servir Player, nomes una variable) -----
        RoomTest zonaActual = dormitori;
        zonaActual.mostrarDescripcio();

        while (true) {
            String[] comanda = p.getComanda();
            String verb = comanda[0];
            String objectiu = comanda[1];

            if (verb.equals("ANAR")) {
                RoomTest desti = trobarSortida(zonaActual, objectiu);
                if (desti == null) {
                    System.out.println("No pots anar cap a " + objectiu + " des d'aqui.");
                } else {
                    zonaActual = desti;
                    zonaActual.mostrarDescripcio();
                }
            } else {
                System.out.println("De moment aquest test nomes prova el moviment (ANAR).");
            }
        }
    }

    private static String[] nomsDe(RoomTest[] zones) {
        String[] noms = new String[zones.length];
        for (int i = 0; i < zones.length; i++) {
            noms[i] = zones[i].getNom();
        }
        return noms;
    }

    // Comprova si "nomZona" es una sortida REAL des de "actual"
    private static RoomTest trobarSortida(RoomTest actual, String nomZona) {
        for (int i = 0; i < actual.getSortides().length; i++) {
            RoomTest sortida = actual.getSortides()[i];
            if (sortida.getNom().equals(nomZona)) {
                return sortida;
            }
        }
        return null;
    }
}