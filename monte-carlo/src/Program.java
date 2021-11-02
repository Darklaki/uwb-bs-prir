import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
    public static void main(String[] args) throws InterruptedException {

        final int THREADS = 4;
        final int TRIALS = 100000;
        final double CIRCLE_RADIUS = 3;
        final Point2DSpace squareCenter = new Point2DSpace(CIRCLE_RADIUS, CIRCLE_RADIUS);
        final double squareSide = 2 * CIRCLE_RADIUS;
        final double squareArea = Math.pow(squareSide, 2);
        final double PRECISE_CIRCLE_AREA = Math.PI * Math.pow(CIRCLE_RADIUS, 2);

        final int TRIALS_IN_THREAD = TRIALS / THREADS;

        ArrayList<MonteCarloCircleArea> monteCarloThreads = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            MonteCarloCircleArea t = new MonteCarloCircleArea(TRIALS_IN_THREAD, squareSide, squareCenter, CIRCLE_RADIUS, squareArea);
            monteCarloThreads.add(t);
            monteCarloThreads.get(i).start();
            monteCarloThreads.get(i).join();
        }

        double sumOfAreas = 0;
        for (MonteCarloCircleArea monteCardloResult: monteCarloThreads
             ) {
            sumOfAreas += monteCardloResult.getResult();
        }

        double predictedCircleArea = (double)sumOfAreas / (double)THREADS;

        System.out.println("Dokładne pole koła: " + PRECISE_CIRCLE_AREA);
        System.out.println("Pole koła obliczone za pomocą symulacji: " + predictedCircleArea);
    }
}
