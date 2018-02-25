import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;

public class Utility {

    public static DateTime readInDateTime(BufferedReader input) {
        try {
            int year = Integer.parseInt(input.readLine());
            int month = Integer.parseInt(input.readLine());
            int day = Integer.parseInt(input.readLine());
            int hour = Integer.parseInt(input.readLine());
            int minute = Integer.parseInt(input.readLine());
            return new DateTime(year, month, day, hour, minute);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateTimeString(DateTime date) {
        String str = date.getYear() + "\n";
        str += date.monthOfYear().get() + "\n";
        str += date.dayOfMonth() + "\n";
        str += date.getHourOfDay() + "\n";
        str += date.getMinuteOfHour() + "\n";
        return str;
    }
}
