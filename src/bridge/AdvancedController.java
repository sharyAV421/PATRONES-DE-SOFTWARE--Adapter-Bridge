package bridge;

public class AdvancedController extends Controller {

    public AdvancedController(GameDevice device) {
        super(device);
    }

    public void specialAction() {
        device.actionUp();
    }
}