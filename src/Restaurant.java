import java.io.BufferedReader;

public class Restaurant {
    private String mId;
    private String mName;
    private String mAddress;
    private double mLatitude, mLongitude;
    private String mCuisine;
    private double mAverageCostForTwo;
    private String mSelectionDescription;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public String getCuisine() {
        return mCuisine;
    }

    public void setCuisine(String cuisine) {
        this.mCuisine = cuisine;
    }

    public double getAverageCostForTwo() {
        return mAverageCostForTwo;
    }

    public void setAverageCostForTwo(double averageCostForTwo) {
        this.mAverageCostForTwo = averageCostForTwo;
    }

    public String getSelectionDescription() {
        return mSelectionDescription;
    }

    public void setSelectionDescription(String mSelectionDescription) {
        this.mSelectionDescription = mSelectionDescription;
    }

    @Override
    public String toString() {
        String str = "";
        str += mId + "\n";
        str += mName + "\n";
        str += mAddress + "\n";
        str += mLatitude + "\n";
        str += mLongitude + "\n";
        str += mCuisine + "\n";
        str += mAverageCostForTwo + "\n";
        str += mSelectionDescription + "\n";
        return str;
    }

    public static Restaurant getRestaurantFromInput(BufferedReader input) {
        try {
            Restaurant r = new Restaurant();
            r.setId(input.readLine());
            r.setName(input.readLine());
            r.setAddress(input.readLine());
            r.setLatitude(Double.parseDouble(input.readLine()));
            r.setLongitude(Double.parseDouble(input.readLine()));
            r.setCuisine(input.readLine());
            r.setAverageCostForTwo(Double.parseDouble(input.readLine()));
            r.setSelectionDescription(input.readLine());
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
