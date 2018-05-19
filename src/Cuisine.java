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
        String AMERICAN = "American Food";
        String ASIAN = "Asian Food";
        String EUROPEAN = "European Food";
        String AFRICAN = "African Food";
        String MIDDLE_EASTERN = "Middle-Eastern Food";
        String LATIN_AMERICAN = "South American/Central American Food";
        String FAST_FOOD = "Fast Food";
        String BAR_CAFE = "Bar/Cafe";
        String BAKERY_DESSERTS = "Dessert & Pastries";
        String VEGETARIAN = "Vegetarian";
    }

    public static Map<String, Integer> cuisineMap() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put(Categories.AMERICAN, 0);
        map.put(Categories.ASIAN, 0);
        map.put(Categories.EUROPEAN, 0);
        map.put(Categories.AFRICAN, 0);
        map.put(Categories.LATIN_AMERICAN, 0);
        map.put(Categories.MIDDLE_EASTERN, 0);
        map.put(Categories.FAST_FOOD, 0);
        map.put(Categories.BAR_CAFE, 0);
        map.put(Categories.BAKERY_DESSERTS, 0);
        map.put(Categories.VEGETARIAN, 0);
        return map;
    }

    public static List<Cuisine> initCuisines() {
        List<Cuisine> cuisines = new ArrayList<>();
        cuisines.add(new Cuisine(6, "Afghani", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(152, "African", Categories.AFRICAN));
        cuisines.add(new Cuisine(1, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(151, "South American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(175, "Mediterranean", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(3, "Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(193, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(955, "Breakfast", Categories.BAKERY_DESSERTS));
        cuisines.add(new Cuisine(5, "Baker", Categories.BAKERY_DESSERTS));
        cuisines.add(new Cuisine(227, "Bar", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(132, "Central European", Categories.EUROPEAN));
        cuisines.add(new Cuisine(270, "Bar", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(159, "South American", Categories.LATIN_AMERICAN);
        cuisines.add(new Cuisine(133, "UK", Categories.EUROPEAN));
        cuisines.add(new Cuisine(247, "Boba", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(168, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(22, "Southeast Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(30, "Cafe", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(491, "Southern", Categories.AMERICAN));
        cuisines.add(new Cuisine(956, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(121, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(158, "Caribbean", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(25, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(161, "Cafe", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(287, "South", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(928, "Central American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(881, "French", Categories.EUROPEAN));
        cuisines.add(new Cuisine(153, "Carribbean", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(203, "Scandanavian", Categories.EUROPEAN));
        cuisines.add(new Cuisine(192, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(100, "Desserts", Categories.BAKERY_DESSERTS));
        cuisines.add(new Cuisine(411, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(541, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(958, "Caribbean", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(959, "Bakery", Categories.BAKERY_DESSERTS));
        cuisines.add(new Cuisine(268, "Bars", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(651, "Eastern European", Categories.EUROPEAN));
        cuisines.add(new Cuisine(149, "African", Categories.AFRICAN));
        cuisines.add(new Cuisine(38, "European", Categories.EUROPEAN));
        cuisines.add(new Cuisine(40, "Fast Food", Categories.FAST_FOOD));
        cuisines.add(new Cuisine(112, "Southeast Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(298, "UK", Categories.EUROPEAN));
        cuisines.add(new Cuisine(45, "French", Categories.EUROPEAN));
        cuisines.add(new Cuisine(501, "Frozen Desserts", Categories.BAKERY_DESSERTS));
        cuisines.add(new Cuisine(134, "Central European", Categories.EUROPEAN));
        cuisines.add(new Cuisine(156, "Mediterranean", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(521, "Pacific Islander", Categories.ASIAN));
        cuisines.add(new Cuisine(233, "Frozen Desserts", Categories.BAKERY_DESSERTS));
        cuisines.add(new Cuisine(148, "South Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(114, "Southeast Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(140, "Persian", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(135, "UK", Categories.EUROPEAN));
        cuisines.add(new Cuisine(55, "Italian", Categories.EUROPEAN));
        cuisines.add(new Cuisine(207, "Caribbean", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(60, "Japanese", Categories.ASIAN));
        cuisines.add(new Cuisine(411, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(265, "Jewish", Categories.EUROPEAN));
        cuisines.add(new Cuisine(1645, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(178, "Mediterranean", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(67, "Korean", Categories.ASIAN));
        cuisines.add(new Cuisine(901, "Southeast Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(136, "Latin American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(66, "Lebanese", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(69, "Southeast Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(70, "Mediterranean", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(73, "Central American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(137, "Middle Eastern", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(74, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(996, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(296, "African", Categories.AFRICAN));
        cuisines.add(new Cuisine(139, "South Asian", Categories.ASIAN));
        cuisines.add(new Cuisine(162, "South American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(82, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(983, "Bar", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(361, "Caribbean", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(320, "Japanese", Categories.ASIAN));
        cuisines.add(new Cuisine(84, "Eastern European", Categories.EUROPEAN));
        cuisines.add(new Cuisine(998, "Salad", Categories.AMERICAN));
        cuisines.add(new Cuisine(304, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(691, "Scandanavian", Categories.EUROPEAN));
        cuisines.add(new Cuisine(141, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(177, "Japanese", Categories.ASIAN));
        cuisines.add(new Cuisine(997, "Central American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(210, "UK", Categories.EUROPEAN));
        cuisines.add(new Cuisine(83, "American", Categories.AMERICAN));
        cuisines.add(new Cuisine(267, "African", Categories.AFRICAN));
        cuisines.add(new Cuisine(972, "South American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(471, "Southern", Categories.AMERICAN));
        cuisines.add(new Cuisine(89, "Spanish", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(163, "Cafe", Categories.BAR_CAFE));
        cuisines.add(new Cuisine(964, "Chinese", Categories.ASIAN));
        cuisines.add(new Cuisine(150, "Tex-Mex", Categories.AMERICAN));
        cuisines.add(new Cuisine(95, "Thai", Categories.ASIAN));
        cuisines.add(new Cuisine(142, "Mediterranean", Categories.MIDDLE_EASTERN));
        cuisines.add(new Cuisine(451, "Eastern European", Categories.EUROPEAN));
        cuisines.add(new Cuisine(641, "South American", Categories.LATIN_AMERICAN));
        cuisines.add(new Cuisine(99, "Vietnamese", Categories.ASIAN));




        return cuisines;
    }

}