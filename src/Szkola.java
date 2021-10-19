enum STAN_SZKOLY {
    LEKCJE,
    PRZERWA,
}

public final class Szkola extends Thread{
    private STAN_SZKOLY stan = STAN_SZKOLY.LEKCJE;
    private static Szkola instance;
    private int licznik = 0;

    private Szkola(){}

    public static Szkola getInstance(){
        if (instance == null) {
            instance = new Szkola();
        }
        return instance;
    }

    @Override
    public void run(){
        if (instance != null) {
            while (true) {
                try {
                    sleep(500);
                    if (stan == STAN_SZKOLY.LEKCJE) {
                        if (licznik == 60) {
                            licznik = 0;
                            stan = STAN_SZKOLY.PRZERWA;
                        }
                    } else if (stan == STAN_SZKOLY.PRZERWA) {
                        if (licznik == 15) {
                            licznik = 0;
                            stan = STAN_SZKOLY.LEKCJE;
                        }
                    }
                    licznik += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public STAN_SZKOLY getStan() {
        return stan;
    }

    public void setStan(STAN_SZKOLY stan) {
        this.stan = stan;
    }
}
