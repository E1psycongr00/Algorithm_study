import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.ToIntFunction;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> timeMap = countMinuteDuration(records);
        return timeMap.entrySet().stream()
            .sorted(Entry.comparingByKey())
            .mapToInt(updateEntryToMoneyCost(fees))
            .toArray();
    }

    private Map<Integer, Integer> countMinuteDuration(String[] records) {
        Map<Integer, Integer> temp = new HashMap<>();
        Map<Integer, Integer> costMap = new HashMap<>();
        for (String record : records) {
            String[] split = record.split(" ");
            int carNumber = Integer.parseInt(split[1]);
            if (temp.containsKey(carNumber)) {
                costMap.put(carNumber,
                    costMap.getOrDefault(carNumber, 0) + Time.toMinutes(split[0]) - temp.get(
                        carNumber));
                temp.remove(carNumber);
                continue;
            }
            temp.put(carNumber, Time.toMinutes(split[0]));
        }
        lastUpdate(temp, costMap);
        return costMap;
    }

    private ToIntFunction<Entry<Integer, Integer>> updateEntryToMoneyCost(int[] fees) {
        return entry -> {
            if (entry.getValue() <= fees[0]) {
                return fees[1];
            }
            return (int) (fees[1]
                + Math.ceil((double) (entry.getValue() - fees[0]) / fees[2]) * fees[3]);
        };
    }

    private void lastUpdate(Map<Integer, Integer> temp, Map<Integer, Integer> costMap) {
        for (int carNumber : temp.keySet()) {
            costMap.put(carNumber,
                costMap.getOrDefault(carNumber, 0) + Time.toMinutes("23:59") - temp.get(carNumber));
        }
    }
}

class Time {

    private static final int HOUR_TO_MINUTES = 60;

    public static int toMinutes(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * HOUR_TO_MINUTES + Integer.parseInt(split[1]);
    }
}
