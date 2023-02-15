import java.util.LinkedList;

class Solution {

    public int solution(int cacheSize, String[] cities) {
        LRUCache lruCache = new LRUCache(cacheSize);
        int time = 0;
        for (String city : cities) {
            time += lruCache.push(city.toLowerCase());
        }
        return time;
    }
}

class LRUCache {

    private static final int CACHE_HIT = 1;
    private static final int CACHE_MISS = 5;

    private final LinkedList<String> hash;
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        hash = new LinkedList<>();
    }

    public int push(String value) {
        if (calculateCacheTime(value) == CACHE_HIT) {
            hash.removeFirstOccurrence(value);
            hash.addLast(value);
            return CACHE_HIT;
        }
        hash.addLast(value);
        removeEldestNode();
        return CACHE_MISS;
    }

    private void removeEldestNode() {
        if (cacheSize < hash.size()) {
            hash.removeFirst();
        }
    }

    private int calculateCacheTime(String value) {
        if (hash.contains(value)) {
            return CACHE_HIT;
        }
        return CACHE_MISS;
    }
}