import java.util.Scanner;

public class joc {
    static Scanner sc = new Scanner(System.in);
    parser p = new parser(sc);

    //Mapa:
    // System.out.println("                             ┌──────────────────┐");
    // System.out.println("                             │ Dormitori Robert │");
    // System.out.println("                             └────────┬─────────┘");
    // System.out.println("                 ┌────────────────────┴────────────────────────┐");
    // System.out.println("                 │                   Passadis                  │─────────┐");
    // System.out.println("                 └───────┬─────────────────────────┬───────────┘         │");
    // System.out.println("                         │                         │                     │");
    // System.out.println(" ┌─────────────────┐ ┌───┴────────────────────┐  ┌─┴─────┐           ┌───┴────┐   ┌──────────────────┐  ┌──────┐");
    // System.out.println(" │ Dormitori Aider │ │                        ├──┤ Cuina │           │ Taller ├───┤ Sortida Exterior ├──┤ Fora │");
    // System.out.println(" └────────┬────────┘ │                        │  └───────┘           └───┬────┘   └──────────────────┘  └──────┘");
    // System.out.println("          │          │        Menjador        │                          │");
    // System.out.println(" ┌────────┴────────┐ │                        │                     ┌────┴─────┐");
    // System.out.println(" │    Navegacio    ├─┤                        │                     │ Sistemas │");
    // System.out.println(" └─────────────────┘ └──────┬──────────┬──────┘                     └──────────┘");
    // System.out.println("                            │          │");
    // System.out.println(" ┌────────────┐ ┌───────────┴──────┐ ┌─┴─────┐");
    // System.out.println(" │ Infermeria ├─┤ Dormitori Elster ├─┤ Banys │");
    // System.out.println(" └────────────┘ └──────────────────┘ └───────┘");

    //Colors per mes endavant...
    public static final String RESET = "\u001B[0m"; //Per reiniciar els colors.
    public static final String CROSS = "\u001B[9m";
    public static final String SUB = "\u001B[4m";
    public static final String ITALICS = "\u001B[3m";
    public static final String RED = "\u001B[31m"; //Elster
    public static final String GRAY = "\u001B[90m"; // Subtitols
    public static final String YELLOW = "\u001B[33m"; //iHall
    public static final String GREEN = "\u001B[32m"; //Objectes
    public static final String BLUE = "\u001B[34m"; //Ader
    public static final String PURPURE = "\u001B[35m"; //Robert

    objecte eina = new objecte(1, "Eina", "Una clau anglesa rovellada, encara prou resistent per fer servir."); //objecte
    objecte pistola = new objecte(2, "Pistola", "Una pistola làser de reglament."); //Interactuable
    objecte vestit = new objecte(3, "Vestit Espacial", "Un vestit espacial penjat a la paret, amb el casc una mica ratllat."); //objecte
    objecte donut1 = new objecte(4, "Donut", "Un donut amb glaçat de xocolata, encara té bon aspecte."); //objecte
    objecte donut2 = new objecte(5, "Donut", "Un donut de maduixa amb una mossegada, algú el va deixar a mitges."); //objecte
    objecte donut3 = new objecte(6, "Donut", "Un donut sec i una mica aixafat, fa dies que és aquí."); //objecte
    objecte xeringa1 = new objecte(7, "Xeringa", "Una xeringa segellada amb un líquid blau a dins."); //objecte
    objecte xeringa2 = new objecte(8, "Xeringa", "Una xeringa segellada amb un líquid blau a dins."); //objecte
    objecte xeringa3 = new objecte(9, "Xeringa", "Una xeringa segellada amb un líquid blau a dins."); //objecte
    objecte keycardTaller = new objecte(10, "Keycard Taller", "Una targeta d'accés groga amb el logotip del taller."); //Interactuable
    objecte keycardInfermeria = new objecte(11, "Keycard Infermeria", "Una targeta d'accés blanca amb una creu vermella."); //Interactuable
    objecte llanterna = new objecte(12, "Llanterna", "Una llanterna petita de metall, la llum parpelleja una mica."); //objecte

    room cuina = new room(1, "CUINA", new int[]{9,2}, new objecte[]{donut1}, new npc[]{});
    room menjador = new room(2, "MENJADOR", new int[]{1,7,4,9,6}, new objecte[]{donut2}, new npc[]{});
    room dAder = new room(3, "D.ADER", new int[]{6}, new objecte[]{}, new npc[]{});
    room dElster = new room(4, "D.ELSTER", new int[]{8,7,4}, new objecte[]{}, new npc[]{});
    room dRobert = new room(5, "D.ROBERT", new int[]{9}, new objecte[]{}, new npc[]{});
    room navegacio = new room(6, "NAVEGACIO", new int[]{3,2}, new objecte[]{}, new npc[]{});
    room banys = new room(7, "BANYS", new int[]{4,2}, new objecte[]{donut3}, new npc[]{});
    room infermeria = new room(8, "INFERMERIA", new int[]{4}, new objecte[]{xeringa1}, new npc[]{});
    room passadis = new room(9, "PASSADIS", new int[]{5,1,2,11}, new objecte[]{xeringa2}, new npc[]{});
    room sistemes = new room(10, "SISTEMES", new int[]{11}, new objecte[]{}, new npc[]{});
    room taller = new room(11, "TALLER", new int[]{10,12,9}, new objecte[]{xeringa3, eina, llanterna}, new npc[]{});
    room sortida = new room(12, "SORTIDA EXTERIOR", new int[]{11,13}, new objecte[]{vestit}, new npc[]{});
    room fora = new room(13, "FORA", new int[]{12}, new objecte[]{}, new npc[]{});

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

    player jugador = new player(null, 0, false, false, "D.ADER", false);

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
        do {
            String[] comanda;
            System.out.println("Estas en la habitacio: " + GREEN + SUB + jugador.zonaActual + RESET);
            comanda = p.getComanda();
            if (comanda[0].equals("ANAR")) {
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
                    System.out.println("Has anat a " + GREEN + SUB + jugador.zonaActual + RESET);
                } else{
                    System.out.println("No pots anar a aquesta zona");
                }
            } 
        } while (!(finalJoc));
    }

    public void comprovarFinal(){

    }
}
