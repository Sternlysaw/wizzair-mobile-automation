package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {

    private DateUtils() {}

    /** Returns the next Friday (if today is Friday, returns today). */
    public static LocalDate nextOrSameFriday() {
        return LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    }

    /** Returns the Friday one week after the given Friday. */
    public static LocalDate nextWeekFriday(LocalDate friday) {
        return friday.plusWeeks(1);
    }
}