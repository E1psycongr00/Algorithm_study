import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;


class Solution {

    public int solution(int bridge_length, int limitWeight, int[] truckWeights) {
        Bridge bridge = new Bridge(bridge_length, limitWeight);
        Trucks dest = new Trucks();
        int time = update(bridge, dest, truckWeights);
        while (!bridge.isEmpty()) {
            bridge.push(0);
            time++;
        }
        return time;
    }

    private int update(Bridge bridge,Trucks dest, int[] truckWeights) {
        int time = 0;
        int i = 0;
        while (i < truckWeights.length) {
            int truckWeight = truckWeights[i];
            if (bridge.canPush(truckWeight)) {
                int value = bridge.push(truckWeight);
                if (dest.canAdd(value)) {
                    dest.add(value);
                }
                i++;
                time++;
                continue;
            }
            bridge.push(0);
            time++;
        }
        return time;
    }
}

class Trucks {
    private final List<Integer> truckWeights = new ArrayList<>();

    public Trucks() {}

    public boolean canAdd(int number) {
        return number != 0;
    }

    public void add(int number) {
        if(!canAdd(number)) {
            throw new IllegalArgumentException("no zero");
        }
        truckWeights.add(number);
    }
}

class Bridge {

    private final Queue<Integer> queue = new ArrayDeque<>();
    private final int length;
    private final int limitWeight;

    private int weight;

    public Bridge(int length, int limitWeight) {
        this.length = length;
        this.limitWeight = limitWeight;
        for (int i = 0; i < length; ++i) {
            queue.add(0);
        }
    }

    public boolean canPush(int number) {
        return !queue.isEmpty() && weight + number - queue.peek() <= limitWeight;
    }

    public boolean isEmpty() {
        return weight == 0;
    }

    public int push(int number) {
        if (!canPush(number)) {
            throw new IllegalArgumentException("Weight Exceed");
        }
        queue.add(number);
        int frontValue = Objects.requireNonNull(queue.poll());
        weight = weight - frontValue + number;
        return frontValue;
    }

    @Override
    public String toString() {
        return "Bridge{" +
            "queue=" + queue +
            ", length=" + length +
            ", limitWeight=" + limitWeight +
            ", weight=" + weight +
            '}';
    }
}