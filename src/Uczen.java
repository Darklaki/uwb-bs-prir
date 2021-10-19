enum STAN_UCZNIA {
    W_SZKOLE, NA_LEKCJI, W_SKLEPIE
}

public class Uczen extends Thread{
    private STAN_UCZNIA stan = STAN_UCZNIA.NA_LEKCJI;
    private int id;

    public Uczen(int id) {
        this.id = id;
    }

    @Override
    public void run(){}
}
