package Traffic;

public class TrafficLightController implements Runnable {
    private final String[] signals = {"Red", "Green", "Yellow"};
    private int currentSignalIndex = 0;
    private volatile boolean running = true;

    public String getCurrentSignal() {
        return signals[currentSignalIndex];
    }

    public void stop() {
        running = false;
    }

    public void run() {
        while (running) {
            try {
                currentSignalIndex = (currentSignalIndex + 1) % signals.length;
                System.out.println("Signal: " + getCurrentSignal());
                Thread.sleep(5000); // Change every 5 sec
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
