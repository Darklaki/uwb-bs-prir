import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloCircleArea extends Thread{
    private int TRIALS;
    private double result;
    private double squareSide;
    private Point2DSpace squareCenter;
    private double circleRadius;
    private double squareArea;

    public MonteCarloCircleArea(int t, double squareSide, Point2DSpace squareCenter, double circRadius, double squareArea) {
        this.squareSide = squareSide;
        this.squareCenter = squareCenter;
        this.circleRadius = circRadius;
        this.squareArea = squareArea;
        this.TRIALS = t;
    }

    @Override
    public void run() {
        int insideCircle = 0;
        for (int i = 0; i < TRIALS; i++) {
            double x = ThreadLocalRandom.current().nextDouble(0, squareSide);
            double y = ThreadLocalRandom.current().nextDouble(0, squareSide);
            Point2DSpace randomPoint = new Point2DSpace(x, y);
            double distanceToCircleCenter = randomPoint.distanceFrom(squareCenter);
            if (distanceToCircleCenter <= circleRadius) {
                insideCircle += 1;
            }
        }

        double probabilityOfPointInCircle = (double)insideCircle / (double)TRIALS;
        double simulatedCircleArea = probabilityOfPointInCircle * squareArea;
        this.result = simulatedCircleArea;
    }

    public double getResult() {
        return this.result;
    }
}
