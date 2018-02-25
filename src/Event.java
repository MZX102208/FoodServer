import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int mId;
    private Restaurant mRestaurant;
    private DateTime mDate;
    private User mHead;
    private List<User> mParticipants = new ArrayList<>();

    public Event(int id, Restaurant restaurant, DateTime date, User head) {
        mId = id;
        mRestaurant = restaurant;
        mDate = date;
        mHead = head;
    }

    public void addParticipants(User user) {
        mParticipants.add(user);
    }


    public int getId() {
        return mId;
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
        str += mId + "\n";
        str += mRestaurant.toString();
        str += Utility.getDateTimeString(mDate);
        str += mHead.toString();
        str += mParticipants.size() + "\n";
        for (User u : mParticipants) {
            str += u.toString();
        }
        return str;
    }

    public static Event readInEvent(BufferedReader input) {
        try {
            int id = Integer.parseInt(input.readLine());
            Restaurant r = Restaurant.getRestaurantFromInput(input);
            DateTime dateTime = Utility.readInDateTime(input);
            User head = User.userFromInput(input);

            Event e = new Event(id, r, dateTime, head);
            int numParticipants = Integer.parseInt(input.readLine());
            for (int i = 0; i < numParticipants; i++) e.addParticipants(User.userFromInput(input));
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
