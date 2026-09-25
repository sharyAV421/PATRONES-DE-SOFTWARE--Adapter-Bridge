package bridge;

public class RacingMachine implements GameDevice {
    private String status = "STOPPED";

    public void start() {
        status = "RACE STARTED";
    }

    public void stop() {
        status = "RACE STOPPED";
    }

    public void actionUp() {
        status = "ACCELERATING";
    }

    public void actionDown() {
        status = "BRAKING";
    }

    public String getStatus() {
        return status;
    }
}