import java.util.ArrayList;
import java.util.List;

public class User {
    private String mName;
    private String mUserId;
    private double mLongitude, mLatitude;
    private List<String> mContacts = new ArrayList<>();

    public User(String userId, String name) {
        mUserId = userId;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getUserId() {
        return mUserId;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public List<String> getContacts() {
        return mContacts;
    }

    public void addContact(String userId) {
        mContacts.add(userId);
    }
}
