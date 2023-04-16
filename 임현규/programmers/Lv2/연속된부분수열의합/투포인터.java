import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class Solution {

    private final FindIndexStrategy findIndexStrategy = new FindIndexTwoPointerStrategy();

    public int[] solution(int[] sequence, int k) {
        List<Interval> intervals = findIndexStrategy.find(sequence, k);
        intervals.sort(
            Comparator.comparingInt(Interval::getLength).thenComparingInt(Interval::getLeft));
        return intervals.get(0).toIntArray();
    }

}


interface FindIndexStrategy {

    List<Interval> find(int[] sequence, int k);
}

class Interval {

    private final int left;
    private final int right;

    public Interval(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getLength() {
        return right - left + 1;
    }

    public int[] toIntArray() {
        return new int[]{left, right};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Interval interval = (Interval) o;
        return left == interval.left && right == interval.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "Interval{" +
            "left=" + left +
            ", right=" + right +
            '}';
    }

    public static Interval of(int left, int right) {
        return new Interval(left, right);
    }
}


class FindIndexTwoPointerStrategy implements FindIndexStrategy {

    @Override
    public List<Interval> find(int[] sequence, int k) {
        int sum = 0;
        int right = 0;
        List<Interval> result = new ArrayList<>();
        for (int left = 0; left < sequence.length; left++) {
            int[] expandsResult = expandRight(sequence, k, sum, right);
            sum = expandsResult[0];
            right = expandsResult[1];
            if (sum == k) {
                result.add(Interval.of(left, right));
            }
            sum -= sequence[left];
        }
        return result;
    }

    private int[] expandRight(int[] sequence, int k, int sum, int right) {
        while (sum < k && right < sequence.length) {
            sum += sequence[right];
            right++;
        }
        return new int[]{sum, right - 1};
    }
}