import adapter.LegacyArcadeAdapter;
import adapter.LegacyArcadeMachine;
import bridge.AdvancedController;
import bridge.ArcadeMachine;
import bridge.Controller;
import bridge.GameDevice;
import bridge.RacingMachine;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static GameDevice device = new ArcadeMachine();
    private static Controller controller = new Controller(device);

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api", Main::handleApi);
        server.createContext("/", Main::handleWeb);

        server.start();

        System.out.println("Arcade Control Center running at http://localhost:8080");
    }

    private static void handleApi(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();

        if (query != null) {
            String action = getValue(query, "action");
            String selectedDevice = getValue(query, "device");
            String selectedController = getValue(query, "controller");

            if (selectedDevice != null) {
                createDevice(selectedDevice, selectedController);
            }

            if ("start".equals(action)) {
                controller.start();
            } else if ("stop".equals(action)) {
                controller.stop();
            } else if ("up".equals(action)) {
                controller.up();
            } else if ("down".equals(action)) {
                controller.down();
            } else if ("special".equals(action) && controller instanceof AdvancedController) {
                ((AdvancedController) controller).specialAction();
            }
        }

        String response = controller.getStatus();

        exchange.getResponseHeaders().add("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, response.length());

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(response.getBytes());
        }
    }

    private static void createDevice(String selectedDevice, String selectedController) {
        if ("legacy".equals(selectedDevice)) {
            device = new LegacyArcadeAdapter(new LegacyArcadeMachine());
        } else if ("racing".equals(selectedDevice)) {
            device = new RacingMachine();
        } else {
            device = new ArcadeMachine();
        }

        if ("advanced".equals(selectedController)) {
            controller = new AdvancedController(device);
        } else {
            controller = new Controller(device);
        }
    }

    private static String getValue(String query, String key) {
        for (String parameter : query.split("&")) {
            String[] parts = parameter.split("=");

            if (parts.length == 2 && parts[0].equals(key)) {
                return parts[1];
            }
        }

        return null;
    }

    private static void handleWeb(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        if (path.equals("/")) {
            path = "/index.html";
        }

        Path file = Path.of("web" + path);

        if (!Files.exists(file)) {
            exchange.sendResponseHeaders(404, 0);
            exchange.close();
            return;
        }

        String contentType = "text/html";

        if (path.endsWith(".css")) {
            contentType = "text/css";
        } else if (path.endsWith(".js")) {
            contentType = "application/javascript";
        }

        byte[] data = Files.readAllBytes(file);

        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(200, data.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(data);
        }
    }
}