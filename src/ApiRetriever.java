import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiRetriever {

    public static OkHttpClient client = new OkHttpClient();
    public static Gson gson = new Gson();

    private static final int NUM_RESULTS = 15;
    private static final String API_KEY = "5e24b0da5d788df79149e3ecce987897";
    private static final String CITY_ID = "11005";

    public static void main(String[] args) {
        List<Restaurant> l = getNearbyRestaurants(100, 3);
        l.forEach((restaurant -> System.out.println(restaurant.getName())));
    }

    public static List<Restaurant> getNearbyRestaurants(double radiusMeters, int... cuisineId) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://developers.zomato.com/api/v2.1/search").newBuilder();
        urlBuilder.addQueryParameter("entity_id", CITY_ID);
        urlBuilder.addQueryParameter("entity_type", "city");
        urlBuilder.addQueryParameter("count", String.valueOf(NUM_RESULTS));
        urlBuilder.addQueryParameter("radius", String.valueOf(radiusMeters));
        String cuisines = "";
        for (int i = cuisineId.length - 1; i >= 0; i--) {
            cuisines +=  cuisineId[i] + (i == 0 ? "" : "%2C" );
        }
        urlBuilder.addQueryParameter("cuisines", cuisines);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .addHeader("Accept", "application/json")
                .addHeader("user-key", API_KEY)
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JsonArray jsonList = gson.fromJson(response.body().string(), JsonObject.class).getAsJsonArray("restaurants");
            List<Restaurant> restaurants = new ArrayList<>();
            for (int i = 0; i < jsonList.size(); i++) {
                Restaurant r = new Restaurant();
                JsonObject obj = jsonList.get(i).getAsJsonObject().getAsJsonObject("restaurant");
                r.setId(obj.getAsJsonPrimitive("id").getAsString());
                r.setName(obj.getAsJsonPrimitive("name").getAsString());
                r.setAddress(obj.getAsJsonObject("location").getAsJsonPrimitive("address").getAsString());
                r.setLatitude(obj.getAsJsonObject("location").getAsJsonPrimitive("latitude").getAsDouble());
                r.setLongitude(obj.getAsJsonObject("location").getAsJsonPrimitive("longitude").getAsDouble());
                r.setCuisine(obj.getAsJsonPrimitive("cuisines").getAsString());
                r.setAverageCostForTwo(obj.getAsJsonPrimitive("average_cost_for_two").getAsDouble());
                restaurants.add(r);
            }
            return restaurants;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}