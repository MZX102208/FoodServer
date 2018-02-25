import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PastEvent {
    private String mName;
    private DateTime mDate;
    private List<User> mParicipants = new ArrayList<>();

    public PastEvent(String name, DateTime date) {
        mName = name;
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public DateTime getDate() {
        return mDate;
    }

    public List<User> getParicipants() {
        return mParicipants;
    }

    public void addParticipants(User u) {
        mParicipants.add(u);
    }

    @Override
    public String toString() {
        String str = mName + "\n";
        str += Utility.getDateTimeString(mDate);
        str += mParicipants.size() + "\n";
        for (User u : mParicipants) str += u.surfaceToString();
        return str;
    }

    public static PastEvent getEvent(BufferedReader input) {
        try {
            String name = input.readLine();
            PastEvent p = new PastEvent(name, Utility.readInDateTime(input));

            int numParticipants = Integer.parseInt(input.readLine());
            for (int i = 0; i < numParticipants; i++) {
                p.addParticipants(User.userFromInput(input));
            }
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
