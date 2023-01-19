import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[] solution(String[] infos, String[] queries) {
        Database database = new Database();
        for (String info : infos) {
            database.put(info);
        }
        System.out.println(database);
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
    }

    public int countByQuery(String query) {
        String replaced = query.replaceAll(" and ", " ");
        String[] split = replaced.split(" ");
        List<String> keys = makeKeys(split);
        int value = Integer.parseInt(split[4]);
        int count = 0;
        for (String key : keys) {
            if (!members.containsKey(key)) {
                continue;
            }
            List<Integer> scores = members.get(key);
            count = getCount(value, count, scores);
        }
        return count;
    }

    private int getCount(int value, int count, List<Integer> scores) {
        for (int score : scores) {
            if (score >= value) {
                count++;
            }
        }
        return count;
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
