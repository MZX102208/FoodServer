import org.joda.time.DateTime;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static final int PORT_NUMBER = 3233;
    public static Map<String, User> users = new ConcurrentHashMap<>();
    public static Map<Integer, Group> groups = new ConcurrentHashMap<>();
    public static List<Cuisine> cuisineTypes;

    public static void main(String[] args) {
        addMockUsers();
        cuisineTypes = Cuisine.initCuisines();
        startAcceptingConnections();
        while (true) {}
    }

    private static void startAcceptingConnections() {
        Thread acceptThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
                while (true) {
                    System.out.println("1111112");
                    ClientThread thread = new ClientThread(serverSocket.accept());
                    thread.run();
                    System.out.println("22222221");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        acceptThread.run();
    }

    public static double[] computeCentroid(List<Double> latitudes, List<Double> longitudes) {
        double latitude = 0;
        double longitude = 0;
        int n = latitudes.size();

        for (double d : latitudes) latitude += d;
        for (double d : longitudes) longitude += d;

        return new double[] {latitude/n, longitude/n};
    }

    public static Group generateNewGroup(String name, String photoId) {
        int id = 1230;
        List<Integer> sortedIds = new ArrayList<>(groups.keySet());
        Collections.sort(sortedIds);
        for (int i : sortedIds) if (id == i) id++;

        Group g = new Group(name, photoId, id);
        groups.put(id, g);
        return g;
    }

    public static void addMockUsers() {
        users.put("sghsri", new User("sghsri", "Sriram"));
        users.put("mhykol", new User("mhykol", "Michael"));
        users.put("yamden", new User("yamden", "Camden"));
        //--------------------------------------------------------------------------------------------------------------
        users.get("sghsri").addHistory(new PastEvent("Restaurant A", new DateTime(2012,2,1,4,50)));
        users.get("sghsri").getHistory().get(0).addParticipants(users.get("mhykol"));
        users.get("sghsri").getHistory().get(0).addParticipants(users.get("sghsri"));
        users.get("sghsri").addHistory(new PastEvent("Restaurant B", new DateTime(2012,2,2,13,30)));
        users.get("sghsri").getHistory().get(1).addParticipants(users.get("yamden"));
        users.get("sghsri").getHistory().get(1).addParticipants(users.get("sghsri"));
    }
}
