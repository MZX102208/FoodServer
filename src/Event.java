import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private Restaurant mRestaurant;
    private DateTime mDate;
    private User mHead;
    private List<User> mParticipants = new ArrayList<>();
    private List<User> mInvited = new ArrayList<>();

    public Event(Restaurant restaurant, DateTime date, User head) {
        mRestaurant = restaurant;
        mDate = date;
        mHead = head;
    }

    public void addParticipants(User user) {
        mInvited.remove(user);
        mParticipants.add(user);
    }

    public void addInvites(User user) {
        mInvited.add(user);
    }

    public List<User> getInvited() {
        return mInvited;
    }

    public Restaurant getRestaurant() {
        return mRestaurant;
    }

    public DateTime getDate() {
        return mDate;
    }

    public User getHead() {
        return mHead;
    }

    public List<User> getParticipants() {
        return mParticipants;
    }

    @Override
    public String toString() {
        String str = "";
        str += mRestaurant.toString();
        str += Utility.getDateTimeString(mDate);
        str += mHead.surfaceToString();
        str += mParticipants.size() + "\n";
        for (User u : mParticipants) {
            str += u.surfaceToString();
        }
        str += mInvited.size() + "\n";
        for (User u : mInvited) {
            str += u.surfaceToString();
        }
        return str;
    }

    public static Event readInEvent(BufferedReader input) {
        try {
            Restaurant r = Restaurant.getRestaurantFromInput(input);
            DateTime dateTime = Utility.readInDateTime(input);
            User head = User.surfaceUserFromInput(input);

            Event e = new Event(r, dateTime, head);
            int numParticipants = Integer.parseInt(input.readLine());
            for (int i = 0; i < numParticipants; i++) e.addParticipants(User.surfaceUserFromInput(input));
            int numInvited = Integer.parseInt(input.readLine());
            for (int i = 0; i < numInvited; i++) e.addInvites(User.surfaceUserFromInput(input));
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
