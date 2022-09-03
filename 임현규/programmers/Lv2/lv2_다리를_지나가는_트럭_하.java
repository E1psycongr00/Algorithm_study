import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;


class Solution {

    public int solution(int bridge_length, int limit, int[] truck_weights) {
        Subscriber subscriber = new Subscriber();
        Publisher publisher = new Publisher(truck_weights);
        Buffer buffer = new Buffer(bridge_length, limit);
        int purpose = truck_weights.length;

        int time = 0;
        while (subscriber.getSize() < purpose) {
            subscriber.subscribe(buffer.poll());
            buffer.add(publisher);
            time += 1;
        }
        return time;
    }
}

class Subscriber {

    private final Queue<Integer> queue = new ArrayDeque<>();

    public int getSize() {
        return queue.size();
    }

    public void subscribe(int x) {
        if (x == 0) {
            return;
        }
        queue.add(x);
    }
}

class Publisher {

    private final Queue<Integer> queue;

    public Publisher(int[] values) {
        queue = Arrays.stream(values).boxed().collect(Collectors.toCollection(ArrayDeque::new));
    }

    public int publish() {
        if (queue.isEmpty()) {
            return 0;
        }
        return queue.poll();
    }

    public int getFront() {
        if (queue.isEmpty()) {
            return 0;
        }
        return queue.peek();
    }
}

class Buffer {

    private final int limit;
    private final Queue<Integer> queue;
    private int weight;

    public Buffer(int length, int limit) {
        queue = new ArrayDeque<>();
        for (int i = 0; i < length; ++i) {
            queue.add(0);
        }
        this.limit = limit;
    }

    public int poll() {
        int x = queue.poll();
        weight -= x;
        return x;
    }

    public void add(Publisher publisher) {
        if (publisher.getFront() + weight > limit) {
            queue.add(0);
            return;
        }
        int x = publisher.publish();
        queue.add(x);
        weight += x;
    }
}