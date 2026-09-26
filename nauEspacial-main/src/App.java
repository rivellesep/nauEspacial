import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // test parser
        parser p = new parser();
        String[] comanda = p.getComanda();
        System.out.println("Verb: " + comanda[0]);
        System.out.println("Objectiu: " + comanda[1]);
    }
}
