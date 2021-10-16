public class MetodaSimpsona extends MetodaBazowa{
    public MetodaSimpsona(double a, double b, int n, Funkcja f) throws Exception {
        super(a, b, n, f);
    }

    @Override
    public double policz() {
        double dystans = this.przedzial.policzDystans();
        double dx = dystans / this.n;
        double wynikWPktSrodkowych = 0;
        double wynik = 0;
        for (int i = 1; i <= this.n; i++) {
            double x = this.przedzial.getA() + (dx * i);
            wynikWPktSrodkowych += this.f.wartosc(x - (dx/2));
            if (i < n) {
                wynik += this.f.wartosc(x);
            }
        }

        return (dx/6)*(this.f.wartosc(this.przedzial.getA()) + this.f.wartosc(this.przedzial.getB()) + 2*wynik + 4*wynikWPktSrodkowych);
    }
}
