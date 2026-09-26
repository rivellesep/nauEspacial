import java.util.Scanner;

public class joc {
    static Scanner sc = new Scanner(System.in);
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
    public static final String RED = "\u001B[31m";
    public static final String GRAY = "\u001B[90m"; // 30m es negre, 90m es gris de veritat
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPURE = "\u001B[35m";

    room cuina = new room(1, "Cuina", new int[]{9,2}, new objecte[]{}, new npc[]{});
    room menjador = new room(2, "Menjador", new int[]{1,7,4,9,6}, new objecte[]{}, new npc[]{});
    room dormitoriAder = new room(3, "Dormitori Ader", new int[]{6}, new objecte[]{}, new npc[]{});
    room dormitoriElster = new room(4, "Dormitori Elster", new int[]{8,7,4}, new objecte[]{}, new npc[]{});
    room dormitoriRobert = new room(5, "Dormitori Robert", new int[]{9}, new objecte[]{}, new npc[]{});
    room navegacio = new room(6, "Navegacio", new int[]{3,2}, new objecte[]{}, new npc[]{});
    room banys = new room(7, "Banys", new int[]{4,2}, new objecte[]{}, new npc[]{});
    room infermeria = new room(8, "Infermeria", new int[]{4}, new objecte[]{}, new npc[]{});
    room pasadis = new room(9, "Passadis", new int[]{5,1,2,11}, new objecte[]{}, new npc[]{});
    room sistemas = new room(10, "Sistemas", new int[]{11}, new objecte[]{}, new npc[]{});
    room taller = new room(11, "Taller", new int[]{10,12,9}, new objecte[]{}, new npc[]{});
    room sortidaExterior = new room(12, "Sortida Exterior", new int[]{11,13}, new objecte[]{}, new npc[]{});
    room fora = new room(13, "Fora", new int[]{12}, new objecte[]{}, new npc[]{});

    room[] mapaZones = new room[]{
        cuina, 
        menjador, 
        dormitoriAder, 
        dormitoriElster, 
        dormitoriRobert, 
        navegacio, banys, 
        infermeria, 
        pasadis, 
        sistemas, 
        taller, 
        sortidaExterior, 
        fora
    };

    player jugador = new player(null, 0, false, false, dormitoriAder, false);

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
            
        } while (finalJoc);
    }

    public void comprovarFinal(){

    }
}
