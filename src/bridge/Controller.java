package bridge;

public class Controller {
    protected GameDevice device;

    public Controller(GameDevice device) {
        this.device = device;
    }

    public void start() {
        device.start();
    }

    public void stop() {
        device.stop();
    }

    public void up() {
        device.actionUp();
    }

    public void down() {
        device.actionDown();
    }

    public String getStatus() {
        return device.getStatus();
    }
}