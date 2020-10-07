package org.sunt.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    private static final Pattern monthDay = Pattern.compile("(?<month>0?[1-9]|1[0-2])[/\\-\\\\ 月]*(?<day>[0-2][0-9]|3[0-1])");
    private static final Pattern yearMonthDay = Pattern.compile("(?:20)?(?<year>[1-9]\\d)[\\-/ 年]*(?<month>0?[1-9]|1[0-2])[\\-/ 月]*(?<day>[0-2][0-9]|3[0-1])[号日天]?");
    private static final Pattern yearMonth = Pattern.compile("(?:20)?(?<year>[1-9]\\d)[\\-/ 年]*(?<month>0?[1-9]|1[0-2])[\\-/ 月]*");

    public static String detectDateTime(String string) {
        if (string == null || string.equals("")) {
            return null;
        }
        Matcher matcher;
        int year = 0, month = 0, day = 0;
        if ((matcher = yearMonthDay.matcher(string)).matches()) {
            year = Integer.parseInt("20" + matcher.group("year"));
            month = Integer.parseInt(matcher.group("month"));
            day = Integer.parseInt(matcher.group("day"));
        } else if ((matcher = monthDay.matcher(string)).matches()) {
            year = LocalDate.now().getYear();
            month = Integer.parseInt(matcher.group("month"));
            day = Integer.parseInt(matcher.group("day"));
        } else if ((matcher = yearMonth.matcher(string)).matches()) {
            year = Integer.parseInt("20" + matcher.group("year"));
            month = Integer.parseInt(matcher.group("month"));
        } else {
            return null;
        }
        if (month <= 0 || month > 12) {
            return null;
        }
        return String.format("%d-%02d-%s", year, month, day == 0 ? "%" : day < 10 ? "0" + day : String.valueOf(day));
    }

    public static Date localDateToDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalTime dateToLocalTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
