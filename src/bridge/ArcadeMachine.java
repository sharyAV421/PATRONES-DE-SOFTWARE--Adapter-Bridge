package bridge;

public class ArcadeMachine implements GameDevice {
    private String status = "STOPPED";

    public void start() {
        status = "RUNNING";
    }

    public void stop() {
        status = "STOPPED";
    }

    public void actionUp() {
        status = "MOVING UP";
    }

    public void actionDown() {
        status = "MOVING DOWN";
    }

    public String getStatus() {
        return status;
    }
}