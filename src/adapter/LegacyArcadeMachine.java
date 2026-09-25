package adapter;

public class LegacyArcadeMachine {
    private String status = "OFF";

    public void powerOn() {
        status = "ON";
    }

    public void powerOff() {
        status = "OFF";
    }

    public void moveJoystickUp() {
        status = "JOYSTICK UP";
    }

    public void moveJoystickDown() {
        status = "JOYSTICK DOWN";
    }

    public String getLegacyStatus() {
        return status;
    }
}