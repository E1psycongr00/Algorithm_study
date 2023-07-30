import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    public String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(File::new)
                .sorted()
                .map(File::getFileName)
                .toArray(String[]::new);
    }

    private static class File implements Comparable<File> {

        private static Pattern fileNamePattern = Pattern.compile("^([a-zA-Z.\\s-]+)(\\d{1,5})(.*)$");
        private final String head;
        private final String number;
        private final String tail;

        public File(String fileName) {
            Matcher matcher = fileNamePattern.matcher(fileName);
            if (matcher.find()) {
                this.head = matcher.group(1);
                this.number = matcher.group(2);
                this.tail = matcher.group(3);
            } else {
                throw new IllegalArgumentException("파일명이 올바르지 않습니다.");
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            File file = (File) o;
            return Objects.equals(head, file.head) && Objects.equals(number,
                    file.number) && Objects.equals(tail, file.tail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(head, number, tail);
        }

        @Override
        public int compareTo(Solution.File o) {
            int headCompare = this.head.compareToIgnoreCase(o.head);
            if (headCompare == 0) {
                return Integer.compare(Integer.parseInt(this.number), Integer.parseInt(o.number));
            }
            return headCompare;
        }

        public String getFileName() {
            return this.head + this.number + this.tail;
        }
    }
}