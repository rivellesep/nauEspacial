public class App {
    public static void main(String[] args) throws Exception {
        boolean tornarAJugar = new joc(0, false).iniciar(true);
        while (tornarAJugar) {
            tornarAJugar = new joc(0, false).iniciar(false);
        }
        System.out.println("Fins aviat, capità.");
    }
}