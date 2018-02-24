import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket mSocket;
    private PrintWriter mOutput;
    private BufferedReader mInput;

    private User currentUser;

    interface Commands {
        String REJECTED = "Rejected";
        String CLIENT_TO_SERVER_FRIENDS = "server_friends_list";
        String SERVER_TO_CLIENT_FRIENDS = "client_friends_list";
    }

    ClientThread(Socket soc) {
        mSocket = soc;
        try {
            mOutput = new PrintWriter(mSocket.getOutputStream(), true);
            mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        currentUser = Main.users.get(nextLine());
    }

    @Override
    public void run() {
        while(!mSocket.isClosed()) {
            switch(nextLine()) {
                case Commands.SERVER_TO_CLIENT_FRIENDS:
                    sendContactsToClient();
                    break;
            }
        }
    }

    private void sendContactsToClient() {
        mOutput.println(currentUser.getContacts().size());
        for (int i = 0; i < currentUser.getContacts().size(); i++) {
            mOutput.println(currentUser.getContacts().get(i));
        }
    }

    public void reject() {
        mOutput.println("Rejected");
    }

    public User getCurrentUser() {
        return currentUser;
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
        mOutput.println(user.getUserId());
        mOutput.println(user.getName());
        mOutput.println(user.getLatitude());
        mOutput.println(user.getLongitude());
    }
}
