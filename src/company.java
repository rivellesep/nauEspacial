public class company extends interactuable{
    private objecte keycard;
    private String color;       // color del nom (joc.RED per Elster, joc.PURPURE per Robert)
    private String[] dialeg;    // linies alternades: 0 = company, 1 = Ader, 2 = company, 3 = Ader...
    private boolean amagat;

    public company(String nom, room zona, objecte keycard, String color, String[] dialeg){
        super(nom, zona);
        this.keycard = keycard;
        this.color = color;
        this.dialeg = dialeg;
        this.amagat = false;
    }

    @Override
    public boolean interaccio(String verb, String objectiu, joc j){
        if (!verb.equals("PARLAR") || !objectiu.equals(nom)) {
            return false;
        }

        // Abans que entri el Malien dormen
        if (!j.elMalien.isActiu()) {
            System.out.println(nom + " esta dormint, millor no molestar-los.");
            return true;
        }

        // Despres del dialeg ja s'han amagat
        if (amagat) {
            System.out.println(nom + " s'ha amagat i no vol sortir. Millor deixar-lo tranquil.");
            return true;
        }

        // Cal tenir lloc per la keycard abans de comencar
        if (inventariPle(j)) {
            System.out.println(nom + ": \"Tens les mans plenes! Deixa alguna cosa i torna.\"");
            return true;
        }

        // Dialeg (ENTER per passar de linia, com a la intro)
        for (int i = 0; i < dialeg.length; i++) {
            if (i % 2 == 0) {
                System.out.println(color + "  " + nom + ": " + dialeg[i] + joc.RESET);
            } else {
                System.out.println(joc.BLUE + "  Ader: " + dialeg[i] + joc.RESET);
            }
            joc.sc.nextLine();
        }

        afegirAInventari(j, keycard);
        System.out.println(joc.GREEN + "  + " + keycard.nom + joc.RESET);
        System.out.println(nom + " s'amaga.");
        amagat = true;
        return true;
    }
}