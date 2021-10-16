import Utils.Przedzial;

abstract public class MetodaBazowa {
    protected Przedzial przedzial;
    protected int n;
    protected Funkcja f;

    public MetodaBazowa(double a, double b, int n, Funkcja f) throws Exception {
        this.przedzial = new Przedzial(a, b);
        this.n = n;
        this.f = f;
    }

    abstract public double policz();
}
