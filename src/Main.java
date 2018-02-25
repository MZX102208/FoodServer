import org.joda.time.DateTime;

import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static final int NON_RESTRICT = -1, DISLIKE = -1, LIKE = 1;

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
                    ClientThread thread = new ClientThread(serverSocket.accept());
                    thread.run();
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

    public static List<Restaurant> generateSelection(int groupId, User user) {
        Group g = groups.get(groupId);
        List<Double> latitudes = new ArrayList<>(), longitudes = new ArrayList<>();
        Map<String, Integer> cuisineMap = Cuisine.cuisineMap();
        for (User u : g.getPeople()) {
            latitudes.add(u.getLatitude());
            longitudes.add(u.getLongitude());
        }
        double[] latLng = computeCentroid(latitudes, longitudes);
        double lat = latLng[0], lon = latLng[1];
        Map<Restaurant, ArrayList<String>> restaurants = new ConcurrentHashMap<>();

        List<Cuisine> cuisineList = Cuisine.initCuisines();
        for (String s : cuisineMap.keySet()) {
            List<Integer> ids = new ArrayList<>();
            for (Cuisine c : cuisineList) {
                if (c.getCategories().contains(s)) ids.add(c.getId());
            }
            int[] arr = new int[ids.size()];
            for (int i = 0; i < arr.length; i++) arr[i] = ids.get(i);
            List<Restaurant> restArr = ApiRetriever.getNearbyRestaurants(user.getRadius(), arr);
            for (Restaurant r : restArr) {
                if (!restaurants.containsKey(r)) {
                    restaurants.put(r, new ArrayList<>());
                }
                restaurants.get(r).add(s);
            }
        }

        List<RatingSystem> ratings = new ArrayList<>();
        for (Restaurant r : restaurants.keySet()) {
            RatingSystem rs = new RatingSystem();
            rs.restaurant = r;
            if (r.getName().contains("Dhaba"))
                System.out.println("abc123");
            for (User u : g.getPeople()) {
                for (String s : restaurants.get(r)) {
                    if (!u.getDietaryRestrictions().contains(s) && !u.getDietaryRestrictions().isEmpty()) rs.numRestrict++;
                    if (u.getCuisineDislikes().contains(s)) rs.numHate++;
                    if (u.getCuisinePrefs().contains(s)) rs.numLike++;
                }
            }
            String desc = "";
            if (rs.numLike > 0) desc += (rs.numLike == 1 ? rs.numLike + " person is " : rs.numLike + " people are ") +
                    "likely to enjoy it";
            if (desc.length() > 0 && rs.numHate > 0) desc += ", ";
            if (rs.numHate > 0) desc += (rs.numHate == 1 ? rs.numHate + " person is " : rs.numHate + " people are ") +
                    "less likely to enjoy it";
            if (desc.length() > 0 && rs.numRestrict > 0) desc += ", ";
            if (rs.numRestrict > 0) desc += (rs.numRestrict == 1 ? rs.numRestrict + " person " : rs.numRestrict + " people ") +
                    "might have dietary restrictions";
            r.setSelectionDescription(desc);
            ratings.add(rs);
        }
        Collections.sort(ratings);
        List<Restaurant> restArr = new ArrayList<>();
        for (RatingSystem rs : ratings) restArr.add(rs.restaurant);
        return restArr;
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

    private static class RatingSystem implements Comparable<RatingSystem> {
        int numLike = 0;
        int numHate = 0;
        int numRestrict = 0;

        Restaurant restaurant;

        @Override
        public int compareTo(RatingSystem o) {
            return (o.numLike - o.numHate - o.numRestrict) - (numLike - numHate - numRestrict);
        }
    }
}
