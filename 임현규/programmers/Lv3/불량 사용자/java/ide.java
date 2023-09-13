import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public int solution(String[] userId, String[] bannedId) {
        List<List<String>> permutations = Permutations.permute(Arrays.asList(userId),
            bannedId.length);
        Set<List<String>> filtered = new HashSet<>();
        for (List<String> permutation : permutations) {
            if (isMatched(permutation, bannedId)) {
                permutation.sort(Comparator.naturalOrder());
                filtered.add(permutation);
            }
        }
        return filtered.size();
    }

    private boolean isMatched(List<String> permutation, String[] bannedId) {
        for (int i = 0; i < permutation.size(); i++) {
            String userId = permutation.get(i);
            String bannedIdRegex = bannedId[i].replace("*", ".");
            if (!userId.matches(bannedIdRegex)) {
                return false;
            }
        }
        return true;
    }


    private static class Permutations {

        public static <T> List<List<T>> permute(List<T> list, int num) {
            List<List<T>> result = new ArrayList<>();
            permute(result, list, new ArrayList<>(), num);
            return result;
        }

        private static <T> void permute(List<List<T>> result, List<T> list, List<T> chosen,
            int num) {
            if (chosen.size() == num) {
                result.add(new ArrayList<>(chosen));
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                T element = list.get(i);
                if (chosen.contains(element)) {
                    continue;
                }
                chosen.add(element);
                permute(result, list, chosen, num);
                chosen.remove(chosen.size() - 1);
            }
        }
    }
}