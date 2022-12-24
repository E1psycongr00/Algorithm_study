import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Table table = new Table(data, col, row_begin, row_end);
        return table.getHashCode();
    }
}

//    1. 테이블을 col asc, 첫뻔재 desc로 정렬한다.
//    2. 행을 기준으로 튜플 값의 모든 합을 mod로 더함.
//    3. 해쉬값 구하기
//    note) 행, 열은 1부터 시작
class Table {

    private final List<List<Integer>> data = new ArrayList<>();
    private final int hashCode;

    public Table(int[][] data, int col, int row_begin, int row_end) {
        initData(data);
        sort(col);
        makeHashCode(row_begin, row_end);
        this.hashCode = makeHashCode(row_begin, row_end);
    }

    public int getHashCode() {
        return hashCode;
    }

    private void initData(int[][] data) {
        for (int[] datum : data) {
            this.data.add(Arrays.stream(datum).boxed().collect(Collectors.toList()));
        }
    }

    private void sort(int col) {
        int colIndex = col - 1;
        data.sort((o1, o2) -> {
            if (Objects.equals(o1.get(colIndex), o2.get(colIndex))) {
                return o2.get(0) - o1.get(0);
            }
            return o1.get(colIndex) - o2.get(colIndex);
        });

    }

    private int makeHashCode(int rowBegin, int rowEnd) {
        int hashCode = 0;
        for (int i = rowBegin; i <= rowEnd; ++i) {
            int sum = sumTuples(i);
            hashCode ^= sum;
        }
        return hashCode;
    }

    private int sumTuples(int row) {
        int rowIndex = row - 1;
        return this.data.get(rowIndex).stream()
            .mapToInt(value -> value % row)
            .sum();
    }
}