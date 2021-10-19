import java.util.ArrayList;

public class Sklep extends Thread{
    private static Sklep instance;
    private Szkola szkola;
    private ArrayList<KasaSklepowa> kasy;

    private Sklep(Szkola szkola, ArrayList<KasaSklepowa> kasy){
        this.kasy = kasy;
        this.szkola = szkola;
    }

    public static Sklep getInstance(Szkola szkola,  ArrayList<KasaSklepowa> kasy) {
        if (instance == null) {
            instance = new Sklep(szkola, kasy);
        }

        return instance;
    }

    public void narysujKasy() {
        for (KasaSklepowa k: kasy) {
            System.out.println(k.toString());
        }
    }

    @Override
    public void run(){
        if (instance != null) {
            while (true) {
                try {
                    sleep(500);
                    double mnoznik_klienta = 0.25;
                    if (szkola.getStan() == STAN_SZKOLY.PRZERWA) {
                        mnoznik_klienta = 0.65;
                    }
                    if (Math.random() <= mnoznik_klienta) {
                        // dodaj czlowieczka do sklepu
                        KasaSklepowa najkrotszaKasa = najkrotszaKasa();
                        najkrotszaKasa.przydzielDoKasy();
                    }
                    System.out.println("W szkole: " + szkola.getStan());
                    narysujKasy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public KasaSklepowa najkrotszaKasa() {
        KasaSklepowa kasa = kasy.get(0);
        for (KasaSklepowa k: kasy) {
            if (k.osobWKolejce < kasa.osobWKolejce) {
                kasa = k;
            }
        }
        return kasa;
    }
}
