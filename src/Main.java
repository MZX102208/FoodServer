import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static final int PORT_NUMBER = 3233;
    private static Map<String, ClientThread> clientThreads = new ConcurrentHashMap<>();
    public static Map<String, User> users = new ConcurrentHashMap<>();
    public static List<Cuisine> cuisineTypes;

    public static void main(String[] args) {
        cuisineTypes = Cuisine.initCuisines();
        startAcceptingConnections();
        removeClosedThreads();
    }

    private static void startAcceptingConnections() {
        Thread acceptThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
                while (true) {
                    ClientThread thread = new ClientThread(serverSocket.accept());
                    String userId = thread.getCurrentUser().getUserId();
                    if (clientThreads.containsKey(userId)) thread.reject();
                    else {
                        thread.run();
                        clientThreads.put(userId, thread);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        acceptThread.run();
    }

    private static void removeClosedThreads() {
        Thread removeThreads = new Thread(() -> {
            while (true) {
                for (String s : clientThreads.keySet()) {
                    if (clientThreads.get(s).isClosed()) clientThreads.remove(s);
                }
            }
        });
        removeThreads.run();
    }

    public static double[] computeCentroid(List<Double> latitudes, List<Double> longitudes) {
        double latitude = 0;
        double longitude = 0;
        int n = latitudes.size();

        for (double d : latitudes) latitude += d;
        for (double d : longitudes) longitude += d;

        return new double[] {latitude/n, longitude/n};
    }
}
