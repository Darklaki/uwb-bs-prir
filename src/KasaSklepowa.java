import java.util.concurrent.ThreadLocalRandom;

public class KasaSklepowa extends Thread {
    private int id;
    public int osobWKolejce = 0;

    public KasaSklepowa(int id) {
        this.id = id;
    }

    public void przydzielDoKasy(){
        this.osobWKolejce += 1;
    }

    @Override
    public String toString() {
        String str = "Kasa " + this.id + "\n";
        for (int i = 0; i < osobWKolejce; i++) {
            str += "#";
        }
        return str;
    }

    @Override
    public void run(){
        try {
            while (true) {
                sleep(ThreadLocalRandom.current().nextInt(3000, 10000));
                if (osobWKolejce > 0) {
                    osobWKolejce -= 1;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
