import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cuisine {
    private int mId;
    private String mName;
    private List<String> mCategories = new ArrayList<>();

    public Cuisine(int id, String name, String... categories) {
        mId = id;
        mName = name;
        for (String c : categories) {
            mCategories.add(c);
        }
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    interface Categories {
        String AMERICAN_FOOD = "American Food";
        String ASIAN_FOOD = "Asian Food";
        String EUROPEAN_FOOD = "European Food";
        String AFRICAN_MIDDLE_FOOD = "African/Middle Eastern Food";
        String SOUTH_CENTRAL_AMERICAN = "South American/Central American Food";
        String SOUTH_ASIA_FOOD = "South Asia Food";
        String FAST_FOOD = "Fast Food";
        String BAR_CAFE = "Bar/Cafe";
        String PASTRIES_DESSERT = "Dessert & Pastries";
        String VEGETARIAN = "Vegetarian";
    }

    public static Map<String, Integer> cuisineMap() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put(Categories.AMERICAN_FOOD, 0);
        map.put(Categories.ASIAN_FOOD, 0);
        map.put(Categories.EUROPEAN_FOOD, 0);
        map.put(Categories.AFRICAN_MIDDLE_FOOD, 0);
        map.put(Categories.SOUTH_CENTRAL_AMERICAN, 0);
        map.put(Categories.SOUTH_ASIA_FOOD, 0);
        map.put(Categories.FAST_FOOD, 0);
        map.put(Categories.BAR_CAFE, 0);
        map.put(Categories.PASTRIES_DESSERT, 0);
        map.put(Categories.VEGETARIAN, 0);
        return map;
    }

    public static List<Cuisine> initCuisines() {
        List<Cuisine> cuisines = new ArrayList<>();
        cuisines.add(new Cuisine(1, "American", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(82, "Pizza", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(141, "Steak", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(150, "Tex-Mex", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(168, "Burger", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(193, "BBQ", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(471, "Southern", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(491, "Cajun", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(966, "Southwestern", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(996, "New American", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(3, "Asian", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(25, "Chinese", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(60, "Japanese", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(67, "Korean Food", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(95, " Thai", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(99, "Vietnamese", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(177, "Sushi", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(190, "Taiwanese", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(320, "Ramen", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(411, "Dim Sum", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(964, "Teriyaki", Categories.ASIAN_FOOD));
        cuisines.add(new Cuisine(38, "European", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(45, "French", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(55, "Italian", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(70, "Mediterranean", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(135, "Irish", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(142, "Turkish", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(156, "Greek", Categories.EUROPEAN_FOOD));
        cuisines.add(new Cuisine(6, "Afghani", Categories.AFRICAN_MIDDLE_FOOD));
        cuisines.add(new Cuisine(66, "Lebanese", Categories.AFRICAN_MIDDLE_FOOD));
        cuisines.add(new Cuisine(137, "Middle Eastern", Categories.AFRICAN_MIDDLE_FOOD));
        cuisines.add(new Cuisine(149, "Ethiopian", Categories.AFRICAN_MIDDLE_FOOD));
        cuisines.add(new Cuisine(73, "Mexican", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(136, "Latin American", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(158, "Caribbean", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(159, "Brazilian", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(162, "Peruvian", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(207, "Jamaican", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(601, "Salvadorean", Categories.SOUTH_CENTRAL_AMERICAN));
        cuisines.add(new Cuisine(81, "Persian", Categories.SOUTH_ASIA_FOOD));
        cuisines.add(new Cuisine(139, "Pakistani", Categories.SOUTH_ASIA_FOOD));
        cuisines.add(new Cuisine(148, "Indian", Categories.SOUTH_ASIA_FOOD));
        cuisines.add(new Cuisine(40, "Fast Food", Categories.FAST_FOOD));
        cuisines.add(new Cuisine(997, "Taco", Categories.FAST_FOOD));
        cuisines.add(new Cuisine(959, "Donuts", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(995, "Bagels", Categories.AMERICAN_FOOD));
        cuisines.add(new Cuisine(192, "Deli", Categories.FAST_FOOD));
        cuisines.add(new Cuisine(304, "Sandwich", Categories.FAST_FOOD));
        cuisines.add(new Cuisine(30, "Cafe", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(161, "Coffee and Tea", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(164, "Juices", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(179, "Tapas", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(227, "Bar Food", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(5, "Bakery", Categories.PASTRIES_DESSERT));
        cuisines.add(new Cuisine(100, "Desserts", Categories.PASTRIES_DESSERT));
        cuisines.add(new Cuisine(233, "Ice Cream", Categories.PASTRIES_DESSERT));
        cuisines.add(new Cuisine(247, "Bubble Tea", Categories.PASTRIES_DESSERT));
        cuisines.add(new Cuisine(270, "Beverages", Categories.PASTRIES_DESSERT));
        cuisines.add(new Cuisine(501, "Frozen Yogurt", Categories.PASTRIES_DESSERT));
        cuisines.add(new Cuisine(308, "Vegetarian", Categories.VEGETARIAN));
        cuisines.add(new Cuisine(82, "Pizza", Categories.FAST_FOOD));

        return cuisines;
    }

}