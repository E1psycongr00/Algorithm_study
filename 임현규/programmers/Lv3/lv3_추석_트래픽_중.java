import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Solution {

    private final LogParser logParser = new LogParser();

    public int solution(String[] lines) {
        List<Interval> intervalList = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(" ");
            int seconds = logParser.parseDate(split[1]);
            int duration = logParser.parseDuration(split[2]);
            intervalList.add(new Interval(seconds, duration));
        }

        int maxValue = 0;
        for (int i = 0; i < intervalList.size(); ++i) {
            int cnt = 0;
            for (int j = i; j < intervalList.size(); ++j) {
                if (intervalList.get(i).end_time > intervalList.get(j).start_time - 1000) {
                    cnt++;
                }
            }
            maxValue = Integer.max(maxValue, cnt);
        }
        return maxValue;
    }

    static class Interval {
        private final int start_time;
        private final int end_time;

        Interval(int second, int duration) {
            this.end_time = second;
            this.start_time = second - duration + 1;
        }
    }
}



class LogParser {

    private static String DATE_REGEX = "(?<hour>\\d+):(?<minute>\\d+):(?<second>\\d+\\.?\\d*)";
    private static String DURATION_REGEX = "(?<second>\\d+\\.?\\d*)";


    public int parseDate(String s) {
        Matcher dateMatcher = Pattern.compile(DATE_REGEX).matcher(s);
        int result = 0;
        if (dateMatcher.find()) {
            int hour = Integer.parseInt(dateMatcher.group("hour"));
            int minute = Integer.parseInt(dateMatcher.group("minute"));
            double second = Double.parseDouble(dateMatcher.group("second"));
            second *= 1000;
            result += ((hour * 3600) + minute * 60) * 1000 + (int) second;
        }
        return result;
    }

    public int parseDuration(String s) {
        Matcher durationMatcher = Pattern.compile(DURATION_REGEX).matcher(s);
        if (durationMatcher.find()) {
            double second = Double.parseDouble(durationMatcher.group("second"));

            return (int) (second * 1000);
        }
        return 0;
    }
}