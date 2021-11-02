public class Philosopher implements Runnable {

    // The forks on either side of this Philosopher
    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                doAction(System.nanoTime() + ": mysli");
                synchronized (leftFork) {
                    doAction(
                            System.nanoTime()
                                    + ": podniosl lewy widelec");
                    synchronized (rightFork) {
                        // eating
                        doAction(
                                System.nanoTime()
                                        + ": podniosl prawy widelec i zaczal jesc");

                        doAction(
                                System.nanoTime()
                                        + ": odlozyl prawy widelec");
                    }

                    // Back to thinking
                    doAction(
                            System.nanoTime()
                                    + ": odlozyl lewy widelec - wraca do rozmyslania");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

}