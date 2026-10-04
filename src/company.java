public class company extends interactuable{
    private String color;
    private objecte regal;
    private String[] dialeg;
    private String fraseDespres;
    private boolean jaHaParlat;

    public company(String nom, room zona, String color, objecte regal, String[] dialeg, String fraseDespres){
        super(nom, zona);
        this.color = color;
        this.regal = regal;
        this.dialeg = dialeg;
        this.fraseDespres = fraseDespres;
        this.jaHaParlat = false;
    }

    public String nomAmbColor(){
        return color + nom + joc.RESET;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        boolean ambMi = objectiu.equals(nom.toUpperCase()) || objectiu.equals("C." + nom.toUpperCase());
        if (!verb.equals("PARLAR") || !ambMi || !jugadorAqui(j)) {
            return false;
        }
        if (!j.elMalien.isActiu()) {
            System.out.println(nomAmbColor() + " està dormint, millor no molestar.");
            return true;
        }
        if (jaHaParlat) {
            System.out.println(color + "  " + nom + ": " + fraseDespres + joc.RESET);
            return true;
        }
        if (j.jugador.inventariPle()) {
            System.out.println(color + "  " + nom + ": \"Tens les mans plenes! Deixa alguna cosa i torna.\"" + joc.RESET);
            return true;
        }

        for (int i = 0; i < dialeg.length; i++) {
            if (i % 2 == 0) {
                System.out.println(color + "  " + nom + ": " + dialeg[i] + joc.RESET);
            } else {
                System.out.println(joc.BLUE + "  Ader: " + dialeg[i] + joc.RESET);
            }
            joc.sc.nextLine();
        }

        j.jugador.afegirObjecte(regal);
        System.out.println(joc.GREEN + "  + " + regal.nom + joc.RESET);
        jaHaParlat = true;
        return true;
    }
}