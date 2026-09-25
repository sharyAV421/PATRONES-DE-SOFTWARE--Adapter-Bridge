package bridge;

public interface GameDevice {
    void start();
    void stop();
    void actionUp();
    void actionDown();
    String getStatus();
}