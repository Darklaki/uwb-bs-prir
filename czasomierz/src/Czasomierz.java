public class Czasomierz extends Thread {
    private static Czasomierz instancja = new Czasomierz();
    private int sekudny = 0;

    private Czasomierz(){}

    @Override
    public void run() {
        try {
            while (true) {
                sleep(1000);
                sekudny += 1;
                System.out.println("Uplynelo " + sekudny + " sekund");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Czasomierz getInstancja() {
        return instancja;
    }
}
