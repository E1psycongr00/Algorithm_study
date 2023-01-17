import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        Date realToday = Date.parse(today);
        Map<String, Integer> realTerms = makeTerms(terms);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < privacies.length; ++i) {
            String[] split =privacies[i].split(" ");
            PrivateDate privateDate = new PrivateDate(Date.parse(split[0]), realTerms.get(split[1]));
            if (privateDate.isExpired(realToday)) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private Map<String, Integer> makeTerms(String[] terms) {
        Map<String, Integer> result = new HashMap<>();
        Arrays.stream(terms)
            .forEach(s -> {
                String[] split = s.split(" ");
                result.put(split[0], Integer.parseInt(split[1]));
            });
        return result;
    }
}

class PrivateDate {


    private final Date date;
    private final int monthTerms;

    public PrivateDate(Date date, int monthTerms) {
        this.date = date;
        this.monthTerms = monthTerms;
    }

    public boolean isExpired(Date today) {
        Date between = Date.periodBetween(date, today);
        Date terms = Date.fromMonth(monthTerms);
        return between.getDays() >= terms.getDays();
    }
}

class Date {

    private static final Pattern DATE_FORMAT = Pattern.compile(
        "^(?<year>\\d{4})\\.(?<month>\\d{2})\\.(?<day>\\d{2})$");

    private static final int MAX_DAY = 28;
    private static final int MAX_MONTH = 12;

    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Date parse(String date) {
        Matcher matcher = DATE_FORMAT.matcher(date);
        boolean matches = matcher.matches();
        if (matches) {
            int year = Integer.parseInt(matcher.group("year"));
            int month = Integer.parseInt(matcher.group("month"));
            int day = Integer.parseInt(matcher.group("day"));
            return new Date(year, month, day);
        }
        throw new IllegalArgumentException();
    }

    public static Date fromMonth(int month) {
        if (month > MAX_MONTH) {
            int nextYear = month / MAX_MONTH;
            int nextMonth = month % MAX_MONTH;
            return new Date(nextYear, nextMonth, 0);
        }
        return new Date(0, month, 0);
    }

    public static Date periodBetween(Date left, Date right) {
        int year = right.year - left.year;
        int month = right.month - left.month;
        int day = right.day - left.day;

        if (day < 0) {
            day += MAX_DAY;
            month -= 1;
        }

        if (month < 0) {
            month += MAX_MONTH;
            year -= 1;
        }
        return new Date(year, month, day);
    }

    public int getDays() {
        return year * MAX_MONTH * MAX_DAY
            + month * MAX_DAY
            + day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Date{" +
            "year=" + year +
            ", month=" + month +
            ", day=" + day +
            '}';
    }
}