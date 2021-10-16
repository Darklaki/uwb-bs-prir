import Utils.Przedzial;

public class Program {

    public static void main(String[] args) {

        try {
            double POCZATEK_PRZEDZIALU = 0;
            double KONIEC_PRZEDZIALU = 1;
            int CZESCI_CALKI = 1000;
            int ILOSC_WATKOW = 4;

            Funkcja fun = new Funkcja() {
                public double wartosc(double x) {
                    return x * x + 2 * x;
                }
            };

            MetodaProstokatow mp = new MetodaProstokatow(POCZATEK_PRZEDZIALU, KONIEC_PRZEDZIALU, CZESCI_CALKI, fun);
            MetodaTrapezow mt = new MetodaTrapezow(POCZATEK_PRZEDZIALU, KONIEC_PRZEDZIALU, CZESCI_CALKI, fun);
            MetodaSimpsona ms = new MetodaSimpsona(POCZATEK_PRZEDZIALU, KONIEC_PRZEDZIALU, CZESCI_CALKI, fun);

            System.out.println("---- BEZ WĄTKÓW ----");
            long startTime = System.nanoTime();
            System.out.println("Metoda prostokątow wynik = " + mp.policz());
            System.out.println("Metoda trapezów wynik = " + mt.policz());
            System.out.println("Metoda Simpsona wynik = " + ms.policz());
            System.out.println("Upłynelo czasu: " + (System.nanoTime() - startTime)/10000);

            long startTime2 = System.nanoTime();
            System.out.println("---- Z WĄTKAMI ----");
            Przedzial przedzialCalki = new Przedzial(POCZATEK_PRZEDZIALU, KONIEC_PRZEDZIALU);
            Przedzial[] przedzialyWatkow = przedzialCalki.podzielNaNPrzedzialow(ILOSC_WATKOW);
            MetodaBazowaThread[] mp_tablica_watkow = new MetodaBazowaThread[ILOSC_WATKOW];
            MetodaBazowaThread[] mt_tablica_watkow = new MetodaBazowaThread[ILOSC_WATKOW];
            MetodaBazowaThread[] ms_tablica_watkow = new MetodaBazowaThread[ILOSC_WATKOW];
            for (int j = 0; j < przedzialyWatkow.length; j++) {
                Przedzial p = przedzialyWatkow[j];
                MetodaProstokatow mp_2 = new MetodaProstokatow(p.getA(), p.getB(), CZESCI_CALKI, fun);
                MetodaTrapezow mt_2 = new MetodaTrapezow(p.getA(), p.getB(), CZESCI_CALKI, fun);
                MetodaSimpsona ms_2 = new MetodaSimpsona(p.getA(), p.getB(), CZESCI_CALKI, fun);
                mp_tablica_watkow[j] = new MetodaBazowaThread(mp_2);
                mt_tablica_watkow[j] = new MetodaBazowaThread(mt_2);
                ms_tablica_watkow[j] = new MetodaBazowaThread(ms_2);
                mp_tablica_watkow[j].run();
                mt_tablica_watkow[j].run();
                ms_tablica_watkow[j].run();
                mp_tablica_watkow[j].join();
                mt_tablica_watkow[j].join();
                ms_tablica_watkow[j].join();
            }

            double mp_t_wynik = 0;
            double mt_t_wynik = 0;
            double ms_t_wynik = 0;
            for (int j = 0; j < przedzialyWatkow.length; j++) {
                mp_t_wynik += mp_tablica_watkow[j].wynik;
                mt_t_wynik += mt_tablica_watkow[j].wynik;
                ms_t_wynik += ms_tablica_watkow[j].wynik;
            }

            System.out.println("Metoda prostokątow wynik = " + mp_t_wynik);
            System.out.println("Metoda trapezów wynik = " + mt_t_wynik);
            System.out.println("Metoda Simpsona wynik = " + ms_t_wynik);
            System.out.println("Upłynelo czasu: " + (System.nanoTime() - startTime2)/10000);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
