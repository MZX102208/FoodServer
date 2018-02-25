import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientThread implements Runnable {

    private Socket mSocket;
    private PrintWriter mOutput;
    private BufferedReader mInput;

    private User mCurrentUser;

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

    ClientThread(Socket soc) {
        mSocket = soc;
        try {
            mOutput = new PrintWriter(mSocket.getOutputStream(), true);
            mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = nextLine();
        mCurrentUser = Main.users.get(s);
    }

    @Override
    public void run() {
        int groupId;
        Group g;
        switch(nextLine()) {
            case Commands.CLIENT_GET_CURRENT_USER_INFO:
                break;
            case Commands.UPDATE_SERVER_USER:
                User newUser = User.userFromInput(mInput);
                mCurrentUser.setPhotoId(newUser.getPhotoId());
                mCurrentUser.setLatitude(newUser.getLatitude());
                mCurrentUser.setLongitude(newUser.getLatitude());
                mCurrentUser.setRadius(newUser.getRadius());
                mCurrentUser.setCuisinePrefs(newUser.getCuisinePrefs());
                mCurrentUser.setCuisineDislikes(newUser.getCuisineDislikes());
                mCurrentUser.setDietaryRestrictions(newUser.getDietaryRestrictions());
                break;
            case Commands.CLIENT_JOIN_GROUP:
                 groupId = Integer.parseInt(nextLine());
                 if (Main.groups.containsKey(groupId)) {
                     Main.groups.get(groupId).addPeople(mCurrentUser);
                     mCurrentUser.addGroup(Main.groups.get(groupId));
                     mOutput.println(Commands.JOIN_GROUP_SUCCESS);
                 } else {
                     mOutput.println(Commands.JOIN_GROUP_FAIL);
                 }
                 break;
            case Commands.CLIENT_CREATE_GROUP:
                String title = nextLine();
                String photoId = nextLine();
                g = Main.generateNewGroup(title, photoId);
                g.addPeople(mCurrentUser);
                mCurrentUser.addGroup(g);
                break;
            case Commands.GET_RESTAURANT_LIST:
                groupId = Integer.parseInt(nextLine());
                List<Restaurant> restaurantList = Main.generateSelection(groupId, mCurrentUser);
                mOutput.println(restaurantList.size());
                for (Restaurant r : restaurantList) mOutput.print(r.toString());
                mOutput.println();
                break;
        }
        mOutput.println(mCurrentUser.toString());

        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void reject() {
        mOutput.println("Rejected");
    }

    public boolean isClosed() {
        return mSocket.isClosed();
    }

    private String nextLine() {
        try {
            return mInput.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendUserInfo(User user) {
        mOutput.println(user.toString());
    }
}
