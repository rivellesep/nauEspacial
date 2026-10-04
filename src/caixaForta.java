public class caixaForta extends interactuable{
    private boolean oberta;
    private objecte contingut;

    public caixaForta(room zona, objecte contingut){
        super("CAIXA", zona);
        this.contingut = contingut;
        this.oberta = false;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!verb.equals("USAR") || !objectiu.equals("CLAU") || !jugadorAqui(j)) {
            return false;
        }
        if (oberta) {
            System.out.println("La caixa forta ja està oberta i buida.");
            return true;
        }
        if (!j.jugador.teObjecte("CLAU")) {
            System.out.println("No tens la clau de la caixa forta.");
            return true;
        }

        // Primer treiem la clau, així sempre hi ha lloc pel contingut
        oberta = true;
        j.jugador.treureObjecte("CLAU");
        j.jugador.afegirObjecte(contingut);
        System.out.println("Gires la clau i la caixa forta s'obre amb un clic.");
        System.out.println(joc.GREEN + "  + " + contingut.nom + joc.RESET);
        return true;
    }
}