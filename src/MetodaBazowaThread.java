public class MetodaBazowaThread extends Thread {
    private MetodaBazowa metoda;
    public double wynik = 0;

    public MetodaBazowaThread(MetodaBazowa m) {
        this.metoda = m;
    }

    @Override
    public void run() {
        this.wynik = this.metoda.policz();
    }

}
