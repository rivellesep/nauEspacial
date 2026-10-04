public class caixaForta extends interactuable{
    private boolean obert;
    private objecte clau;
    private objecte contingut;

    public caixaForta(String nom, room zona, objecte clau, objecte contingut){
        super(nom, zona);
        this.clau = clau;
        this.contingut = contingut;
        this.obert = false;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!verb.equals("USAR") || !objectiu.equalsIgnoreCase(clau.nom)) {
            return false;
        }
        if (obert) {
            System.out.println("La caixa forta ja està oberta i buida.");
            return true;
        }
        if (!teObjecte(j, clau.nom)) {
            System.out.println("No tens la clau de la caixa forta.");
            return true;
        }

        obert = true;
        treureDeInventari(j, clau.nom);
        afegirAInventari(j, contingut);
        System.out.println("Gires la clau i la caixa forta s'obre amb un clic.");
        System.out.println(joc.GREEN + "  + " + contingut.nom + joc.RESET);
        return true;
    }
}