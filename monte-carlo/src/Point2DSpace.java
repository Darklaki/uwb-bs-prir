public class Point2DSpace {
    private double x;
    private double y;

    public Point2DSpace(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceFrom(Point2DSpace point) {
        return Math.sqrt(Math.pow((point.getX() - this.x), 2) + Math.pow((point.getY() - this.y), 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
