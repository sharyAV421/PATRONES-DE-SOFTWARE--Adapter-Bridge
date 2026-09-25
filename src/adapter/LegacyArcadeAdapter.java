package adapter;

import bridge.GameDevice;

public class LegacyArcadeAdapter implements GameDevice {
    private LegacyArcadeMachine machine;

    public LegacyArcadeAdapter(LegacyArcadeMachine machine) {
        this.machine = machine;
    }

    public void start() {
        machine.powerOn();
    }

    public void stop() {
        machine.powerOff();
    }

    public void actionUp() {
        machine.moveJoystickUp();
    }

    public void actionDown() {
        machine.moveJoystickDown();
    }

    public String getStatus() {
        return machine.getLegacyStatus();
    }
}