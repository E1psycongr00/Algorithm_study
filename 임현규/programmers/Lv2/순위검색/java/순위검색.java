import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[] solution(String[] infos, String[] queries) {
        Database database = new Database();
        for (String info : infos) {
            database.put(info);
        }
        List<Integer> result = new ArrayList<>();
        for (String query : queries) {
            int count = database.countByQuery(query);
            result.add(count);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}


class Database {

    private static final String ANY = "-";
    private static final String[][] dict = new String[][]{
        {"cpp", "java", "python"},
        {"backend", "frontend"},
        {"junior", "senior"},
        {"chicken", "pizza"}
    };
    private final Map<String, List<Integer>> members = new HashMap<>();

    public void put(String s) {
        String[] split = s.split(" ");
        String key = split[0] + "," + split[1] + "," + split[2] + "," + split[3];
        int value = Integer.parseInt(split[4]);
        members.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        members.get(key).sort(Comparator.naturalOrder());
    }

    public int countByQuery(String query) {
        String replaced = query.replaceAll(" and ", " ");
        String[] split = replaced.split(" ");
        List<String> keys = makeKeys(split);
        int value = Integer.parseInt(split[4]);
        int count = 0;
        for (String key : keys) {
            List<Integer> scores = members.get(key);
            if (scores == null) {
                continue;
            }
            count = getCount(value, count, scores);
        }
        return count;
    }

    private int getCount(int value, int count, List<Integer> scores) {
        int lo = 0;
        int hi = scores.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (scores.get(mid) >= value) {
                hi = mid;
                continue;
            }
            lo = mid + 1;
        }
        return count + (scores.size() - hi);
    }

    private List<String> makeKeys(String[] split) {
        List<String> keys = new ArrayList<>();
        int step = 0;
        generate(keys, split, new ArrayList<>(), step);
        return keys;
    }

    private void generate(List<String> keys, String[] split, List<String> temp, int step) {
        if (step == 4) {
            keys.add(String.join(",", temp));
            return;
        }
        if (!split[step].equals(ANY)) {
            temp.add(split[step]);
            generate(keys, split, temp, step + 1);
            temp.remove(temp.size() - 1);
            return;
        }
        for (int i = 0; i < dict[step].length; ++i) {
            temp.add(dict[step][i]);
            generate(keys, split, temp, step + 1);
            temp.remove(temp.size() - 1);
        }
    }

    @Override
    public String toString() {
        return "Database{" +
            "members=" + members +
            '}';
    }
}
