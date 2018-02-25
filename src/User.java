import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String mName;
    private String mUserId;
    private String mPhotoId;
    private double mLongitude, mLatitude;
    private List<Group> mGroups = new ArrayList<>();
    private double mRadius;
    private Set<String> mCuisinePrefs = new HashSet<>();
    private Set<String> mCuisineDislikes = new HashSet<>();
    private Set<String> mDietaryRestrictions = new HashSet<>();
    private List<PastEvent> mHistory = new ArrayList<>();

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

    public double getRadius() {
        return mRadius;
    }

    public void setRadius(double r) {
        mRadius = r;
    }

    public void addCuisinePref(String c) {
        mCuisinePrefs.add(c);
    }

    public void removeCuisinePref(String c) {
        mCuisinePrefs.remove(c);
    }

    public void addCuisineDislike(String c) {
        mCuisineDislikes.add(c);
    }

    public void removeCuisineDislike(String c) {
        mCuisineDislikes.remove(c);
    }

    public void addDietaryRestrict(String d) {
        mDietaryRestrictions.add(d);
    }

    public void removeDietaryRestrict(String d) {
        mDietaryRestrictions.remove(d);
    }

    public void setPhotoId(String id) {
        mPhotoId = id;
    }

    public String getPhotoId() {
        return mPhotoId;
    }

    public Set<String> getCuisinePrefs() {
        return mCuisinePrefs;
    }

    public Set<String> getCuisineDislikes() {
        return mCuisineDislikes;
    }

    public Set<String> getDietaryRestrictions() {
        return mDietaryRestrictions;
    }

    public void removeGroup(int groupId) {
        for (int i = 0; i < mGroups.size(); i++) {
            if (mGroups.get(i).getId() == groupId) {
                mGroups.remove(i);
                break;
            }
        }
    }

    public void addGroup(Group g) {
        mGroups.add(g);
    }

    public List<Group> getGroups() {
        return mGroups;
    }

    public void addHistory(PastEvent p) {
        mHistory.add(p);
    }

    public List<PastEvent> getHistory() {
        return mHistory;
    }

    @Override
    public String toString() {
        String str = "";
        str += mUserId + "\n";
        str += mName + "\n";
        str += mLatitude + "\n";
        str += mLongitude + "\n";
        str += mGroups.size() + "\n";
        for (Group g : mGroups) str += g.toString();
        str += mCuisinePrefs.size() + "\n";
        for (String s : mCuisinePrefs) str += s + "\n";
        str += mCuisineDislikes.size() + "\n";
        for (String s : mCuisineDislikes) str += s + "\n";
        str += mDietaryRestrictions.size() + "\n";
        for (String s : mDietaryRestrictions) str += s + "\n";
        str += mHistory.size() + "\n";
        for (PastEvent s : mHistory) str += s.toString();
        str += mRadius + "\n";
        str += mPhotoId + "\n";
        return str;
    }

    public static User userFromInput(BufferedReader input) {
        try {
            System.out.println("lololol12333333");
            User user = new User(input.readLine(), input.readLine());
            System.out.println("test12333333");
            user.setLatitude(Double.parseDouble(input.readLine()));
            user.setLongitude(Double.parseDouble(input.readLine()));

            Integer numGroups = Integer.parseInt(input.readLine());
            for (int i = 0; i < numGroups; i++) user.addGroup(Group.getGroupFromInput(input));
            Integer numPrefs = Integer.parseInt(input.readLine());
            for (int i = 0; i < numPrefs; i++) user.addCuisinePref(input.readLine());
            Integer numDislikes = Integer.parseInt(input.readLine());
            for (int i = 0; i < numDislikes; i++) user.addCuisineDislike(input.readLine());
            Integer numRestrict = Integer.parseInt(input.readLine());
            for (int i = 0; i < numRestrict; i++) user.addDietaryRestrict(input.readLine());
            Integer numHistory = Integer.parseInt(input.readLine());
            for (int i = 0; i < numHistory; i++) user.addHistory(PastEvent.getEvent(input));

            user.setRadius(Double.parseDouble(input.readLine()));
            user.setPhotoId(input.readLine());
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User surfaceUserFromInput(BufferedReader input) {
        try {
            User user = new User(input.readLine(), input.readLine());
            user.setLatitude(Double.parseDouble(input.readLine()));
            user.setLongitude(Double.parseDouble(input.readLine()));

            Integer numPrefs = Integer.parseInt(input.readLine());
            for (int i = 0; i < numPrefs; i++) user.addCuisinePref(input.readLine());
            Integer numDislikes = Integer.parseInt(input.readLine());
            for (int i = 0; i < numDislikes; i++) user.addCuisineDislike(input.readLine());
            Integer numRestrict = Integer.parseInt(input.readLine());
            for (int i = 0; i < numRestrict; i++) user.addDietaryRestrict(input.readLine());

            user.setRadius(Double.parseDouble(input.readLine()));
            user.setPhotoId(input.readLine());
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String surfaceToString() {
        String str = "";
        str += mUserId + "\n";
        str += mName + "\n";
        str += mLatitude + "\n";
        str += mLongitude + "\n";
        str += mCuisinePrefs.size() + "\n";
        for (String s : mCuisinePrefs) str += s + "\n";
        str += mCuisineDislikes.size() + "\n";
        for (String s : mCuisineDislikes) str += s + "\n";
        str += mDietaryRestrictions.size() + "\n";
        for (String s : mDietaryRestrictions) str += s + "\n";
        str += mRadius + "\n";
        str += mPhotoId + "\n";
        return str;
    }
}
