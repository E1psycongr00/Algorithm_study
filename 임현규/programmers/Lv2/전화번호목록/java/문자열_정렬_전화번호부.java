import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public boolean solution(String[] phone_book) {
        List<String> sorted = sortByString(phone_book);
        for (int i = 1; i < sorted.size(); ++i) {
            if (sorted.get(i).startsWith(sorted.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    private List<String> sortByString(String[] phone_book) {
        return Arrays.stream(phone_book)
            .sorted()
            .collect(Collectors.toList());
    }
}
