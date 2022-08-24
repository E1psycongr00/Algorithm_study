import java.util.ArrayDeque;
import java.util.Deque;

class LRUCacheSimulator {
    private static final int EXECUTE_TIME_WHEN_CACHE_HIT = 1;
    private static final int EXECUTE_TIME_WHEN_CACHE_MISS = 5;

    private final int cacheSize;
    private final Deque<String> memory = new ArrayDeque<>();

    public LRUCacheSimulator(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public int simulate(String[] data) {
        int time = 0;
        for (String value : data) {
            time += useData(value.toLowerCase());
        }
        return time;
    }

    private int useData(String value) {
        if (memory.contains(value)) {
            memory.remove(value);
            memory.addFirst(value);
            return EXECUTE_TIME_WHEN_CACHE_HIT;
        }
        
        memory.addFirst(value);
        if (isExceed()) {
            memory.pollLast();
        }
        return EXECUTE_TIME_WHEN_CACHE_MISS;
    }



    private boolean isExceed() {
        return memory.size() > cacheSize;
    }

}



class Solution {

    public int solution(int cacheSize, String[] cities) {
        LRUCacheSimulator lruCacheSimulator = new LRUCacheSimulator(cacheSize);
        return lruCacheSimulator.simulate(cities);
    }
}

