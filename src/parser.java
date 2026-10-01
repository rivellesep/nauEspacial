import java.util.ArrayList;
import java.util.Scanner;

public class parser{
    private String[] verbsPermesosItems = {"USAR", "DEIXAR", "AGAFAR"};
    private String[] verbsPermesosZones = {"ANAR"};
    private String[] verbsPermesosLlanterna = {"ENCENDRE", "APAGAR", "USAR", "DEIXAR", "AGAFAR"};
    private String[] zonesPermeses = {"MENJADOR", "D.ADER", "D.ELSTER", "D.ROBERT", "NAVEGACIO", "BANYS", "INFERMERIA", "PASSADIS", "SISTEMES", "TALLER", "SORTIDA", "FORA", "CUINA"};
    private String[] itemsPermesos = {"LLANTERNA", "PISTOLA", "VESTIT", "EINA", "DONUT", "XERINGA", "C.ELSTER", "C.ROBERT"};
    public static final String BOLD = "\u001B[1m";
    private Scanner sc;

    public parser(Scanner sc) {
        this.sc = sc;
    }

    public String[] getComanda() {
        System.out.print(BOLD + "> ");
        String input = sc.nextLine().toUpperCase().trim();
        String[] parts = input.split("\\s+", 2);

        if (parts.length < 2) {
            System.out.println("Comanda invàlida. Si us plau, introdueix un verb i un objectiu.");
            return getComanda();
        }
        String verb = parts[0];
        String objectiu = parts[1];

        if (!isVerbPermes(verb)) {
            System.out.println("Verb invàlid. Si us plau, introdueix un verb vàlid.");
            return getComanda();
        }
        if (!isObjectiuPermes(objectiu)) {
            System.out.println("Objectiu invàlid. Si us plau, introdueix un objectiu vàlid.");
            return getComanda();
        }

        return new String[] { verb, objectiu };
    }

    private boolean isVerbPermes(String verb) {
        return conteix(verbsPermesosItems, verb) || conteix(verbsPermesosZones, verb) || conteix(verbsPermesosLlanterna, verb);
    }

    private boolean isObjectiuPermes(String objectiu) {
        return conteix(zonesPermeses, objectiu) || conteix(itemsPermesos, objectiu);
    }

    private boolean conteix(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void setZonesPermeses(String[] zones) {
        this.zonesPermeses = zones;
    }

    public void setItemsPermesos(String[] items) {
        this.itemsPermesos = items;
    }
}