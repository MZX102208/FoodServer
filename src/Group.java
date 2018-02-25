import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private String mName;
    private int mId;
    private List<PastEvent> mHistory;
    private List<User> mPeople;
    private double mScore;
    private String mPhotoId;

    public Group(String name, String photoId) { //Mock Group
        mName = name;
        mPhotoId = photoId;
        mScore = Math.random()*30.0;
    }

    public Group(String name, String photoId, int id) { //Mock Group
        mName = name;
        mPhotoId = photoId;
        mId = id;
        mScore = Math.random()*30.0;
    }


    public String getName() {
        return mName;
    }

    public void addPeople(User user) {
        mPeople.add(user);
    }

    public List<User> getPeople() {
        return mPeople;
    }

    public int getId() {
        return mId;
    }

    public void addHistory(PastEvent p) {
        mHistory.add(p);
    }

    public List<PastEvent> getEats() {
        return mHistory;
    }

    public double getScore() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return Double.parseDouble(formatter.format(mScore));
    }

    public void setScore(double d) {
        mScore = d;
    }

    public String getPhoto() {
        return mPhotoId;
    }

    public double calculateScore(ArrayList<PastEvent> e){
        return 0.0;
    }

    @Override
    public String toString() {
        String str = mName + "\n";
        str += mPhotoId + "\n";
        str += mId + "\n";
        str += mHistory.size() + "\n";
        for (PastEvent p : mHistory) str += p.toString();
        str += mPeople.size() + "\n";
        for (User u : mPeople) str += u.surfaceToString();
        str += mScore + "\n";
        return str;
    }

    public static Group getGroupFromInput(BufferedReader input) {
        try {
            String name = input.readLine();
            String photoId = input.readLine();
            int id = Integer.parseInt(input.readLine());
            Group g = new Group(name, photoId, id);

            int numHistory = Integer.parseInt(input.readLine());
            for (int i = 0; i < numHistory; i++) g.addHistory(PastEvent.getEvent(input));
            int numPeople = Integer.parseInt(input.readLine());
            for (int i = 0; i < numPeople; i++) g.addPeople(User.surfaceUserFromInput(input));
            g.setScore(Double.parseDouble(input.readLine()));
            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
