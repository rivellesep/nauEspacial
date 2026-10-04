import java.util.ArrayList;
import java.util.Scanner;

public class joc {
    static Scanner sc = new Scanner(System.in);
    parser p = new parser(sc);

    public static final String RESET = "\u001B[0m"; //Per reiniciar els colors.
    public static final String CROSS = "\u001B[9m";
    public static final String SUB = "\u001B[4m";
    public static final String ITALICS = "\u001B[3m";
    public static final String RED = "\u001B[31m"; //Elster
    public static final String GRAY = "\u001B[90m"; // Subtitols
    public static final String YELLOW = "\u001B[33m"; //iHall
    public static final String GREEN = "\u001B[32m"; //Objectes
    public static final String BLUE = "\u001B[34m"; //Ader
    public static final String SUB_BLUE = "\u001B[44m";
    public static final String PURPURE = "\u001B[35m"; //Robert
    public static final String SUB_WHITE = "\u001B[107m";
    public static final String SUB_GREEN = "\u001B[42m"; //Malien al mapa
    public static final String SUB_RED = "\u001B[41m";   //Llocs per reparar al mapa

    objecte eina = new objecte(1, "Eina", "Una clau anglesa rovellada, encara prou resistent per fer servir."); //objecte
    objecte pistola = new objecte(2, "Pistola", "Una pistola làser de reglament."); //Interactuable
    objecte vestit = new objecte(3, "Vestit Espacial", "Un vestit espacial penjat a la paret, amb el casc una mica ratllat."); //objecte
    objecte donut1 = new objecte(4, "Donut", "Un donut amb glaçat de xocolata, encara té bon aspecte."); //objecte
    objecte donut2 = new objecte(5, "Donut", "Un donut de maduixa amb una mossegada, algú el va deixar a mitges."); //objecte
    objecte donut3 = new objecte(6, "Donut", "Un donut sec i una mica aixafat, fa dies que és aquí."); //objecte
    objecte xeringa1 = new objecte(7, "Xeringa", "Una xeringa segellada amb un líquid blau a dins."); //objecte
    objecte xeringa2 = new objecte(8, "Xeringa", "Una xeringa segellada amb un líquid blau a dins."); //objecte
    objecte xeringa3 = new objecte(9, "Xeringa", "Una xeringa segellada amb un líquid blau a dins."); //objecte
    objecte keycardSistemes = new objecte(10, "Keycard Sistemes", "Una targeta d'accés groga amb el logotip de sistemes."); //la dona en Robert
    objecte keycardInfermeria = new objecte(11, "Keycard Infermeria", "Una targeta d'accés blanca amb una creu vermella."); //Interactuable
    objecte llanterna = new objecte(12, "Llanterna", "Una llanterna petita de metall, la llum parpelleja una mica."); //objecte
    objecte clau = new objecte(13, "Clau", "Una clau petita i greixosa. Sembla de la caixa forta del taller."); //surt en reparar SISTEMES

    room cuina = new room(1, "CUINA", "Entres a la cuina, encara hi han plats del sopar d’avui que no s’han rentat; pero millor no preocupar-se d'allò encara.", new int[]{9,2}, new objecte[]{donut1}, new interactuable[]{});
    room menjador = new room(2, "MENJADOR", "Estas al menjador, on la tripulació hi menja els seus apats i també usen el seu poc temps lliure per a jugar i estar junts.", new int[]{1,7,4,9,6}, new objecte[]{donut2}, new interactuable[]{});
    room dAder = new room(3, "D.ADER", "El dormitori apte d’un capita; té l’ho suficient i només l’ho suficient.", new int[]{6}, new objecte[]{}, new interactuable[]{});
    room dElster = new room(4, "D.ELSTER", "Un cop entres al seu dormitori, l'olor que entra al teu nas és refrescant i agradable; segurament es deu a les herbes al voltant de l’habitació.", new int[]{8,7,2}, new objecte[]{}, new interactuable[]{});
    room dRobert = new room(5, "D.ROBERT", "Al entrar al dormitori d’en Robert s nota un aire metalic un cop obres la porta; ha estat treballant tot el dia pel que es nota.", new int[]{9}, new objecte[]{}, new interactuable[]{});
    room navegacio = new room(6, "NAVEGACIO", "Entres a navegació. Mires a la varietat de botons i palanques davant teu, cada una porta el pes dels supervivents que vau deixar a la Terra.", new int[]{3,2}, new objecte[]{}, new interactuable[]{});
    room banys = new room(7, "BANYS", "Els banys son simples i sorprenentment nets, pero no hi ha gaire cosa aquí.", new int[]{4,2}, new objecte[]{donut3}, new interactuable[]{});
    room infermeria = new room(8, "INFERMERIA", "Entres a la infermeria i veus tecnologies que mai hi has entès; però mentre et facin sentir bé o t’ajudin a despertar-te demà: confies amb l'Ester que hi vagin bé.", new int[]{4}, new objecte[]{xeringa1}, new interactuable[]{});
    room passadis = new room(9, "PASSADIS", "El passadís. Cada pas que hi fas dins s’escolta el metall sota la teva bota.", new int[]{5,1,2,11}, new objecte[]{xeringa2}, new interactuable[]{});
    room sistemes = new room(10, "SISTEMES", "La part més crucial de la nau; els sistemes. En Robert és l’expert, pero tu de vegades t'encarregues de mirar-los.", new int[]{11}, new objecte[]{}, new interactuable[]{});
    room taller = new room(11, "TALLER", "El taller, pràcticament la casa d’en Robert; ple de aparts utils que ell et diria que no els toquessis. També hi ha una caixa forta amb una pistola especial, pero esperes mai haver de usar-la.", new int[]{10,12,9}, new objecte[]{xeringa3, eina, llanterna}, new interactuable[]{});
    room sortida = new room(12, "SORTIDA EXTERIOR", "A la sortida, tens una finestra on hi pots veure el teu planeta en ruina; esperes algun dia tornar com un heroi...", new int[]{11,13}, new objecte[]{vestit}, new interactuable[]{});
    room fora = new room(13, "FORA", "Estas a fora. Sempre que hi estàs aquí fora pensen qui primer va tenir la idea de sortir a l’espai.", new int[]{12}, new objecte[]{}, new interactuable[]{});

    // Textos extra de SISTEMES según el estado de la reparación
    String sistemesNoReparat = "En l’estat que hi son ara mateix, necesitaras la eina per a poder repara-los.";
    String sistemesReparat = "Penses que has acabat, pero veus el brillo d’una clau al terra. Toca embrutar-se les mans.";

    room[] mapaZones = new room[]{
        cuina, 
        menjador, 
        dAder, 
        dElster, 
        dRobert, 
        navegacio, banys, 
        infermeria, 
        passadis, 
        sistemes, 
        taller, 
        sortida, 
        fora
    };

    player jugador = new player(new objecte[6], 0, false, false, "D.ADER", false);

    // ===== INTERACTUABLES =====
    reparable reparacioFora = new reparable(fora, "Ajustes les plaques amb l'eina i l'avaria queda arreglada. Però al costat hi ha un forat a la paret de la nau...", null);
    reparable reparacioSistemes = new reparable(sistemes, sistemesReparat, clau);
    caixaForta caixa = new caixaForta(taller, pistola);
    malien elMalien = new malien(cuina, taller, reparacioFora);
    company elster = new company("Elster", dElster, RED, keycardInfermeria, new String[]{
        "\"Capità? Què passa? Per què fas aquesta cara?\"",
        "\"Hi ha alguna cosa a la nau. Necessito poder entrar a la infermeria.\"",
        "\"Agafa la meva targeta. Jo em tanco aquí dins.\""
    }, "\"Ja t'he donat la targeta, capità. Ves amb compte.\"");
    company robert = new company("Robert", dRobert, PURPURE, keycardSistemes, new String[]{
        "\"Has sentit aquell soroll? Venia de la cuina.\"",
        "\"Sí. I els sistemes estan fallant, necessito entrar-hi.\"",
        "\"Té la meva targeta. I no toquis res que no calgui!\""
    }, "\"Què més vols? Ja tens la targeta, ves a sistemes!\"");

    // Tots els que reben comandes i torns
    interactuable[] interactuables = {reparacioFora, reparacioSistemes, caixa, elMalien, elster, robert};
    // Per mostrar-los al mapa i a les sales
    reparable[] reparacions = {reparacioFora, reparacioSistemes};
    company[] companys = {elster, robert};

    // Sales on hi ha un donut parat com a esquer pel Malien
    ArrayList<room> donutsParats = new ArrayList<>();

    int contarTorns;
    boolean finalJoc;

    public joc(int contarTorns,boolean finalJoc){
        this.contarTorns = contarTorns;
        this.finalJoc = finalJoc;
    }

    public void iniciar(){
        System.out.println();
        System.out.println("  ==============================================");
        System.out.println("                    P I A . X X                 ");
        System.out.println("  ==============================================");
        System.out.println();
        System.out.println(GRAY + "  [Prem ENTER per continuar]" + RESET);
        sc.nextLine();
 
        System.out.println(ITALICS + "  La nau Pia.XX porta navegant les estrelles uns 50 cicles," + RESET);
        System.out.println(ITALICS + "  buscant recursos per ajudar les forces restants que" + RESET);
        System.out.println(ITALICS + "  segueixen a la Terra." + RESET);
        sc.nextLine();
 
        System.out.println(ITALICS + "  Construida simplement per l'indomable esperit huma," + RESET);
        System.out.println(ITALICS + "  la tripulacio de quatre que hi viu esta sota la pressio" + RESET);
        System.out.println(ITALICS + "  dels pocs milions que queden vius a la Terra." + RESET);
        sc.nextLine();
 
        System.out.println(ITALICS + "  Despres d'una altra cerca de rutina, aparca a la lluna" + RESET);
        System.out.println(ITALICS + "  arruinada per acabar el dia; un altre dia sense cap" + RESET);
        System.out.println(ITALICS + "  troballa important." + RESET);
        sc.nextLine();
 
        System.out.println(ITALICS + "  Pero encara no ho sabien: aquesta seria la nit mes" + RESET);
        System.out.println(ITALICS + "  intensa de les seves vides..." + RESET);
        sc.nextLine();
 
        System.out.println(GRAY + "  ----------------------------------------------" + RESET);
        System.out.println();
 
        System.out.println(ITALICS + "  El capita " + BLUE +  "Ader" + RESET + ITALICS +  ", dormint a la seva habitacio, rep de cop" + RESET);
        System.out.println(ITALICS + "  i volta una alarma de la IA de la nau, " + YELLOW + "iHall" + RESET + ITALICS + "." + RESET);
        System.out.println(ITALICS + "  Es desperta sobtadament i li fa un cop a l'aparell portatil." + RESET);
        sc.nextLine();
 
        System.out.println(YELLOW + "  iHall: " + RESET + YELLOW + "\"Capita " + BLUE + "Ader" + RESET + YELLOW + ", em disculpo per despertar-te a aquestes" + RESET);
        System.out.println(YELLOW + "          hores, pero sembla que han sorgit uns petits" + RESET);
        System.out.println(YELLOW + "          problemes a la nau.\"" + RESET);
        sc.nextLine();
 
        System.out.println(BLUE + "  Ader:  " + "\"Ugh. L'" + RED + "Elster" + RESET + BLUE + " ha tornat a acabar borratxa?\"" + RESET);
        System.out.println(GRAY + ITALICS + "         (respon grinyolant)" + RESET);
        sc.nextLine();
 
        System.out.println(YELLOW + "  iHall: " + RESET + YELLOW + "\"Allo nomes va passar un cop, capita. I si tothom d'aqui" + RESET);
        System.out.println(YELLOW + "          s'hi posés, ho faria en un instant..." + RESET);
        System.out.println(YELLOW + "          Pero recorda: sempre una actitud positiva!\"" + RESET);
        sc.nextLine();
 
        System.out.println(BLUE + "  Ader:  " + RESET + BLUE + "\"Si, si...\"" + RESET);
        System.out.println(ITALICS + "  Ader agafa l'iHall de terra i mira la seva pantalla, on" + RESET);
        System.out.println(ITALICS + "  apareix un " + RED + "error fora de la nau" + RESET + ITALICS + "." + RESET);
        sc.nextLine();
 
        System.out.println(BLUE + "  Ader:  " + RESET + BLUE + "\"Hm. Sembla que hi ha problemes a fora." + RESET);
        System.out.println(BLUE + "          Suposo que es millor mirar-ho.\"" + RESET);
        sc.nextLine();
 
        System.out.println(YELLOW + "  iHall: " + RESET + YELLOW + "\"No podria estar-hi mes d'acord, capita!\"" + RESET);
        System.out.println(ITALICS + "  Ader s'aixeca i se l'emporta a la ma." + RESET);
        sc.nextLine();
 
        System.out.println(GRAY + "  ----------------------------------------------" + RESET);
        System.out.println();
 
        System.out.println(YELLOW + "  iHall: " + RESET + YELLOW + "\"Anem al " + SUB + "Taller" + RESET + YELLOW + ". Hi trobaras l'" + GREEN + "Eina" + YELLOW + " i el " + GREEN + "vestit espacial" + YELLOW + ".\"" + RESET);
        System.out.println();
        System.out.println(GREEN + "  >> NOU OBJECTIU: Ves al Taller i agafa l'Eina." + RESET);
        sc.nextLine();

        bucle();
    }

    public void bucle(){
        System.out.println("Estas a: " + GREEN + SUB + jugador.zonaActual + RESET);
        do {
            String[] comanda;
            comanda = p.getComanda();
            if (comanda[0].equals("ANAR") && !portaOberta(comanda[1])) {
                // portaOberta() ja diu el missatge
            } else if (comanda[0].equals("ANAR")) {
                boolean sortidesIguals = false;
                for(int i = 0; i < mapaZones.length; i++){
                    if (mapaZones[i].nom.equals(comanda[1])){
                        room zonaI = mapaZones[i];
                        for(int w = 0; w < mapaZones.length; w++){
                            if (mapaZones[w].nom.equals(jugador.zonaActual)){
                                room zonaW = mapaZones[w];
                                for(int z = 0; z < zonaW.sortides.length; z++){
                                    if(zonaW.sortides[z] == zonaI.identificador){
                                        jugador.zonaActual = comanda[1];
                                        sortidesIguals = true;
                                    }
                                }
                            }
                        }
                    }
                }

                if(sortidesIguals == true){
                    System.out.println(GRAY + ITALICS + "Has anat a un altre zona..." + RESET);
                    System.out.println("============================================================");
                    System.out.println("Estas a: " + GREEN + SUB + jugador.zonaActual + RESET);
                    System.out.println("------------------------------------------------------------");
                    for(int i = 0; i < mapaZones.length; i++){
                        if(mapaZones[i].nom.equals(jugador.zonaActual)){
                            System.out.println(mapaZones[i].descripcio);
                            System.out.println("============================================================");
                        }
                    }
                    mostrarExtresSala();
                } else{
                    System.out.println("No pots anar a aquesta zona");
                }
            } else if(comanda[0].equals("USAR") && comanda[1].equals("IHALL")){
                jugador.iHall(this);
            } else if(comanda[0].equals("USAR") && comanda[1].equals("DONUT")){
                posarDonut();
            } else if(comanda[0].equals("AGAFAR")){
                agafar(comanda[1]);
            } else if(comanda[0].equals("DEIXAR")){
                deixar(comanda[1]);
            } else {
                // La resta de comandes les proven els interactuables fins que un la fa servir
                boolean fet = false;
                for (int i = 0; i < interactuables.length && !fet; i++) {
                    fet = interactuables[i].interaccio(comanda[0], comanda[1], this);
                }
                if (!fet) {
                    System.out.println("No pots fer això aquí.");
                }
            }

            // Passa un torn (si encara segueixes viu)
            contarTorns++;
            for (int i = 0; i < interactuables.length && !finalJoc; i++) {
                interactuables[i].tornPassat(this);
            }
        } while (!(finalJoc));

        if (jugador.mort) {
            System.out.println();
            System.out.println(RED + "  ==============================================" + RESET);
            System.out.println(RED + "                  H A S   M O R T               " + RESET);
            System.out.println(RED + "  ==============================================" + RESET);
        }
    }

    // Deixa un donut a terra. Si el Malien hi passa, es distreu i queda aturdit.
    public void posarDonut(){
        if (jugador.treureObjecte("DONUT") == null) {
            System.out.println("No tens cap donut.");
            return;
        }
        donutsParats.add(salaActual());
        System.out.println("Deixes un donut ben visible a terra. Potser distreu alguna cosa...");
    }

    public room salaActual(){
        for (int i = 0; i < mapaZones.length; i++) {
            if (mapaZones[i].nom.equals(jugador.zonaActual)) {
                return mapaZones[i];
            }
        }
        return null;
    }

    // INFERMERIA i SISTEMES necessiten la seva targeta, i FORA sense vestit et mata
    public boolean portaOberta(String desti){
        if (desti.equals("FORA") && jugador.zonaActual.equals("SORTIDA EXTERIOR") && !jugador.teObjecte("VESTIT")) {
            System.out.println(ITALICS + "Obres la comporta sense el vestit espacial..." + RESET);
            System.out.println(RED + "El buit de l'espai t'arrossega cap a fora. No hi ha aire. No hi ha res." + RESET);
            jugador.mort = true;
            finalJoc = true;
            return false;
        }
        if (desti.equals("INFERMERIA") && !jugador.teObjecte("KEYCARD INFERMERIA")) {
            System.out.println("La porta de la infermeria està tancada. Necessites la targeta de l'" + RED + "Elster" + RESET + ".");
            return false;
        }
        if (desti.equals("SISTEMES") && !jugador.teObjecte("KEYCARD SISTEMES")) {
            System.out.println("La porta de sistemes està tancada. Necessites la targeta d'en " + PURPURE + "Robert" + RESET + ".");
            return false;
        }
        return true;
    }

    // Text extra de SISTEMES, qui hi ha i objectes que hi ha a terra
    public void mostrarExtresSala(){
        room sala = salaActual();
        if (sala == sistemes && !reparacioSistemes.isReparat()) {
            System.out.println(sistemesNoReparat);
        }
        for (int i = 0; i < companys.length; i++) {
            if (companys[i].getZona() == sala) {
                System.out.println("Hi ha: " + companys[i].nomAmbColor());
            }
        }
        if (elMalien.isActiu() && elMalien.getZona() == sala) {
            System.out.println(RED + "El Malien és aquí!" + RESET);
        }
        if (donutsParats.contains(sala)) {
            System.out.println("Hi ha un " + GREEN + "donut" + RESET + " a terra fent d'esquer.");
        }
        if (!sala.objectesPresents.isEmpty()) {
            String llista = "";
            for (int i = 0; i < sala.objectesPresents.size(); i++) {
                llista += GREEN + sala.objectesPresents.get(i).nom + RESET + "  ";
            }
            System.out.println("Hi veus: " + llista);
        }
    }

    public void agafar(String nomObjecte){
        room sala = salaActual();
        objecte o = sala.buscarObjecte(nomObjecte);
        if (o == null) {
            System.out.println("Aquí no hi ha res amb aquest nom.");
            return;
        }
        if (jugador.inventariPle()) {
            System.out.println("Tens l'inventari ple.");
            return;
        }
        sala.objectesPresents.remove(o);
        jugador.afegirObjecte(o);
        System.out.println(GREEN + "  + " + o.nom + RESET);
    }

    public void deixar(String nomObjecte){
        objecte o = jugador.treureObjecte(nomObjecte);
        if (o == null) {
            System.out.println("No portes res amb aquest nom.");
            return;
        }
        salaActual().objectesPresents.add(o);
        System.out.println(RED + "  - " + o.nom + RESET);
    }

    public void comprovarFinal(){

    }

    public static boolean skillCheck(int velocitat) {
        int inici = (int) (Math.random() * 25); // la zona verda fa 5 caselles
        int pos = 0;
        int direccio = 1;

        System.out.println("Prem ENTER quan el cursor estigui a la zona verda!");
        try {
            while (true) {
                String barra = "";
                for (int i = 0; i < 30; i++) {
                    if (i == pos) barra += "|";
                    else if (i >= inici && i < inici + 5) barra += GREEN + "=" + RESET;
                    else barra += "-";
                }
                System.out.print("\r[" + barra + "]");

                Thread.sleep(velocitat);
                if (System.in.available() > 0) break; // ha premut ENTER

                pos += direccio;
                if (pos == 0 || pos == 29) direccio = -direccio;
            }
        } catch (Exception e) { }
        sc.nextLine(); // treu l'ENTER

        if (pos >= inici && pos < inici + 5) {
            System.out.println(GREEN + "Perfecte!" + RESET);
            return true;
        }
        System.out.println(RED + "Has fallat..." + RESET);
        return false;
    }
}