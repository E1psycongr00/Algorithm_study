import java.util.ArrayList;
import java.util.List;

class Solution {

    public int solution(int n, String[] data) {
        List<String> list = new ArrayList<>(List.of("A", "C", "F", "J", "M", "N", "R", "T"));
        List<List<String>> perms = permutations(list);
        long count = perms.stream().filter(strings -> {
            for (String command : data) {
                if (!interpretCommand(command, strings)) {
                    return false;
                }
            }
            return true;
        }).count();
        return (int) count;
    }

    private boolean interpretCommand(String info, List<String> list) {
        String a = String.valueOf(info.charAt(0));
        String b = String.valueOf(info.charAt(2));
        char op = info.charAt(3);
        int targetInterval = info.charAt(4) - '0';
        int aIndex = list.indexOf(a);
        int bIndex = list.indexOf(b);
        int interval = Math.abs(bIndex - aIndex) - 1;
        if (op == '>') {
            return interval > targetInterval;
        }
        if (op == '<') {
            return interval < targetInterval;
        }
        if (op == '=') {
            return interval == targetInterval;
        }
        throw new RuntimeException();
    }

    private List<List<String>> permutations(List<String> list) {
        List<List<String>> answer = new ArrayList<>();
        backTrack(answer, new ArrayList<>(), list);
        return answer;
    }

    private void backTrack(List<List<String>> answer, ArrayList<String> tempList,
            List<String> list) {
        if (tempList.size() == list.size()) {
            answer.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (tempList.contains(list.get(i))) {
                continue;
            }
            tempList.add(list.get(i));
            backTrack(answer, tempList, list);
            tempList.remove(tempList.size() - 1);
        }
    }

}
