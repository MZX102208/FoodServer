import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
        switch(nextLine()) {
            case Commands.CLIENT_GET_CURRENT_USER_INFO:
                break;
            case Commands.UPDATE_SERVER_USER:
                Main.users.replace(mCurrentUser.getUserId(), User.userFromInput(mInput));
                mCurrentUser = Main.users.get(mCurrentUser.getUserId());
                break;
            case Commands.CLIENT_JOIN_GROUP:
                 int groupId = Integer.parseInt(nextLine());
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
                Group g = Main.generateNewGroup(title, photoId);
                g.addPeople(mCurrentUser);
                mCurrentUser.addGroup(g);
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
