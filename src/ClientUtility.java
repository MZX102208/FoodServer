import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientUtility {


    public static void main(String[] args) {
        User u = getCurrentUserInfo("sghsri");
        System.out.print(u.getName());
        createGroup("sghsri", "title123", "12131321312321");
        joinGroup("mhykol", 1230);
        u.addCuisineDislike(Cuisine.Categories.AMERICAN_FOOD);
        u.addCuisinePref(Cuisine.Categories.ASIAN_FOOD);
        u.addDietaryRestrict(Cuisine.Categories.VEGETARIAN);
        updateServer(u.getUserId(), u);
        u = getCurrentUserInfo("mhykol");
        u.addCuisineDislike(Cuisine.Categories.ASIAN_FOOD);
        u.addCuisinePref(Cuisine.Categories.SOUTH_ASIA_FOOD);
        updateServer(u.getUserId(), u);
        System.out.println(getRestaurants(u.getUserId(), 1230));
    }

    private static final int PORT_NUMBER = 3233;

    interface Commands {
        String REJECTED = "Rejected";
        String CLIENT_GET_CURRENT_USER_INFO = "get_current_user_info";
        String UPDATE_SERVER_USER = "update_server_user";
        String CLIENT_JOIN_GROUP = "join_group";
        String CLIENT_CREATE_GROUP = "create_group";
        String CREATE_EVENT = "create_event";
        String GET_RESTAURANT_LIST = "get_restaurant_list";

        String JOIN_GROUP_SUCCESS = "join_success";
        String JOIN_GROUP_FAIL = "join_fail";
    }

    public static User getCurrentUserInfo(String userId) {
        try {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println(userId);
            out.println(Commands.CLIENT_GET_CURRENT_USER_INFO);
            return User.userFromInput(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User joinGroup(String userId, int id) {
        try {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println(userId);
            out.println(Commands.CLIENT_JOIN_GROUP);
            out.println(id);
            if (in.readLine().equals(Commands.JOIN_GROUP_FAIL)) return null;
            return User.userFromInput(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User createGroup(String userId, String title, String photoId) {
        try {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println(userId);
            out.println(Commands.CLIENT_CREATE_GROUP);
            out.println(title);
            out.println(photoId);
            return User.userFromInput(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User updateServer(String userId, User user) {
        try {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println(userId);
            out.println(Commands.UPDATE_SERVER_USER);
            out.println(user.toString());
            return User.userFromInput(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Restaurant> getRestaurants(String userId, int groupId) {
        try {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println(userId);
            out.println(Commands.GET_RESTAURANT_LIST);
            out.println(String.valueOf(groupId));
            int numRest = Integer.parseInt(in.readLine());
            List<Restaurant> list = new ArrayList<>();
            for (int i = 0; i < numRest; i++) {
                list.add(Restaurant.getRestaurantFromInput(in));
            }
            User.userFromInput(in);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User makeEvent(String mId, int groupId, Event event) {
        try {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println(mId);
            out.println(Commands.CREATE_EVENT);
            out.println(groupId);
            out.println(event.toString());
            return User.userFromInput(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
