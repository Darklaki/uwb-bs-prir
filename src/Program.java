import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        /*
        * Podczas przerwy między zajęciami, uczniowie pewnej szkoły chodzą do pobliskiego sklepu, żeby kupić sobie coś do jedzenia.
        *
        * Podczas przerwy w szkole, pracownicy sklepu mają więce pracy. Muszą otworzyć więcej kas aby obsłużyć wszystkich klientów.
        *
        * Symulacja przedstawia kasy w sklepie - normalnie lub kiedy uczniowie mają przerwę
        *
        * */

        Szkola szkola = Szkola.getInstance();

        ArrayList<KasaSklepowa> kasyWSklepie = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            KasaSklepowa k = new KasaSklepowa(i);
            kasyWSklepie.add(k);
            k.start();
        }
        Sklep sklep = Sklep.getInstance(szkola, kasyWSklepie);

        szkola.start();
        sklep.start();

    }
}
