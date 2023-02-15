import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Solution {

    public String[] solution(String[] files) {
        List<FileName> convertFiles = Arrays.stream(files)
            .map(FileName::new)
            .sorted()
            .collect(Collectors.toList());
        return convertFiles.stream()
            .map(FileName::getFullName)
            .toArray(String[]::new);
    }
}

class FileName implements Comparable<FileName> {

    private static final Pattern FILE_NAME = Pattern.compile("^(\\D+)(\\d+)(.*)$");
    private final String fullName;
    private String head;
    private String number;
    private int realNumber;
    private String tail;

    public FileName(String fullName) {
        this.fullName = fullName;
        init(fullName);
    }

    private void init(String fullName) {
        Matcher matcher = FILE_NAME.matcher(fullName);
        boolean matches = matcher.matches();
        if (!matches) {
            throw new IllegalArgumentException("cannot found fileName");
        }
        this.head = matcher.group(1);
        this.number = matcher.group(2);
        this.realNumber = Integer.parseInt(matcher.group(2));
        this.tail = matcher.group(3);
    }

    @Override
    public String toString() {
        return
            "head='" + head + '\'' +
                ", number=" + number +
                ", tail='" + tail + '\'';
    }

    @Override
    public int compareTo(FileName o) {
        int headCompare = this.head.compareToIgnoreCase(o.head);
        int numberCompare = Integer.compare(realNumber, o.realNumber);
        if (headCompare == 0) {
            return numberCompare;
        }
        return headCompare;
    }

    public String getFullName() {
        return fullName;
    }
}