import java.io.BufferedReader;
import java.io.IOException;

public class User {
    private String mName;
    private String mUserId;
    private double mLongitude, mLatitude;

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

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    @Override
    public String toString() {
        String str = "";
        str += mUserId + "\n";
        str += mName + "\n";
        str += mLatitude + "\n";
        str += mLongitude + "\n";
        return str;
    }

    public static User userFromInput(BufferedReader input) {
        try {
            User user = new User(input.readLine(), input.readLine());
            user.setLatitude(Double.parseDouble(input.readLine()));
            user.setLongitude(Double.parseDouble(input.readLine()));
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

}
