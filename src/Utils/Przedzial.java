package Utils;

public class Przedzial {
    private double a;
    private double b;

    public Przedzial(double a, double b) throws Exception {
        if (b < a) {
            throw new Exception("Koniec przedzialu musi byc wiekszy");
        }
        this.a = a;
        this.b = b;
    }

    public Przedzial[] podzielNaNPrzedzialow(int n) throws Exception {
        Przedzial[] przedzialy = new Przedzial[n];

        double dx = this.policzDystans() / n;

        for (int i = 0; i < n; i++) {
            przedzialy[i] = new Przedzial(this.a + (i * dx), this.a + (i * dx) + dx);
        }

        return przedzialy;
    }

    public double policzDystans() {
        return this.b - this.a;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
