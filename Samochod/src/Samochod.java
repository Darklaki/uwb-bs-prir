import java.util.concurrent.ThreadLocalRandom;

public class Samochod extends Thread {

    private String nrRej;
    private int pojZbiornika;
    private int paliwo;

    public Samochod(String nr, int poj){
        this.nrRej = nr;
        this.pojZbiornika = poj;
        this.paliwo = poj;
    }

    @Override
    public void run() {
        try {
            boolean maPaliwo = true;
            while(maPaliwo) {
                // doo
                if (this.paliwo == 0) {
                    System.out.println("Brak paliwa, samochod "+this.nrRej+" stanął");
                    // Zatrzymujemy watek
                    maPaliwo = false;
                } else if (this.paliwo / this.pojZbiornika < 0.2 ) {
                    System.out.println("Poziom paliwa ponizej 20% w samochodzie " + this.nrRej);
                }
                System.out.println("Poziom paliwa " + this.paliwo + " w samochodzie " + this.nrRej);
                this.paliwo -= 1;
                sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void start() {
        super.start();
    }
}
