public class MetodaProstokatow extends MetodaBazowa {

    public MetodaProstokatow(double a, double b, int n, Funkcja f) throws Exception {
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

        return wynik * dx;
    }

}
