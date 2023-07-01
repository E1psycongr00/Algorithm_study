import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> parkingQueue = new HashMap<>();
        Map<Integer, Integer> parkingTime = new HashMap<>();

        for (String record : records) {
            String[] split = record.split(" ");
            int time = LocalTime.parse(split[0]).toSecondOfDay() / 60;
            int carNumber = Integer.parseInt(split[1]);
            String state = split[2];
            if (state.equals("IN")) {
                parkingQueue.put(carNumber, time);
            } else {
                int parkingStartTime = parkingQueue.get(carNumber);
                parkingTime.put(carNumber,
                    parkingTime.getOrDefault(carNumber, 0) + time - parkingStartTime);
                parkingQueue.remove(carNumber);
            }
        }
        for (int carNumber : parkingQueue.keySet()) {
            int parkingStartTime = parkingQueue.get(carNumber);
            parkingTime.put(carNumber, parkingTime.getOrDefault(carNumber, 0)
                + LocalTime.parse("23:59").toSecondOfDay() / 60 - parkingStartTime);
        }

        return parkingTime.entrySet().stream()
            .sorted(Entry.comparingByKey())
            .mapToInt(entry -> calculateFee(fees, entry.getValue()))
            .toArray();
    }

    private int calculateFee(int[] fees, int time) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        int exceedTime = time - basicTime;
        if (exceedTime <= 0) {
            return basicFee;
        }
        return basicFee + (int) Math.ceil((double) exceedTime / unitTime) * unitFee;
    }
}