public class Program {

    public static void main(String[] args) {

        Samochod s1 = new Samochod("BIA 1234", 5);
        Samochod s2 = new Samochod("BIA 2137", 8);
        Samochod s3 = new Samochod("XYZ 1234", 15);
        s1.start();
        s2.start();
        s3.start();

    }

}
