public class MetodaTrapezow extends MetodaBazowa{

    public MetodaTrapezow(double a, double b, int n, Funkcja f) throws Exception {
        super(a, b, n, f);
    }

    @Override
    public double policz() {
        double dystans = this.przedzial.policzDystans();
        double dx = dystans / this.n;
        double wynik = 0;
        for (int i = 1; i <= this.n; i++) {
            wynik += this.f.wartosc(this.przedzial.getA() + (dx * i));
        }

        return (wynik + (this.f.wartosc(this.przedzial.getA() + this.przedzial.getB()))/2) * dx;
    }
}
