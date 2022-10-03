import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "blind";
        String[] pages = new String[]{
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        };
        String word2 = "Muzi";
        String[] pages2 = new String[]{
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        };
        System.out.println(solution.solution(word2, pages2));
        System.out.println(solution);

    }

    private static void matchResolverTest(String word, String page) {
        String url = MatchResolver.findUrl(page);
        List<String> links = MatchResolver.findExternalLink(page);
        int cnt = MatchResolver.countMatchedWord(word, page);
        System.out.println(url);
        System.out.println(links);
        System.out.println(cnt);
    }
}

class Solution {

    private final List<String> urls = new ArrayList<>();
    private final List<Integer> commonScore = new ArrayList<>();
    private final List<List<String>> externalLinks = new ArrayList<>();
    private final HashMap<Integer, List<Integer>> graph = new HashMap<>();

    private final List<Double> linkScores = new ArrayList<>();

    public int solution(String word, String[] pages) {
        int n = pages.length;
        updateInfo(n, word, pages);
        updateGraph(n);
        updateLinkScore(n);
        return findMaxValueIndex(n);
    }

    private int findMaxValueIndex(int n) {
        double maxValue = -1;
        int result = -1;
        for (int i = 0; i < n; ++i) {
            double score = commonScore.get(i) + linkScores.get(i);
            if (maxValue < score) {
                maxValue = score;
                result = i;
            }
        }
        return result;
    }

    private void updateInfo(int n, String word, String[] pages) {
        for (int i = 0; i < n; ++i) {
            urls.add(MatchResolver.findUrl(pages[i]));
            commonScore.add(MatchResolver.countMatchedWord(word, pages[i]));
            externalLinks.add(MatchResolver.findExternalLink(pages[i]));
        }
    }

    private void updateGraph(int n) {
        for (int i = 0; i < n; ++i) {
            for (String externalLink : externalLinks.get(i)) {
                int idx = urls.indexOf(externalLink);
                int NOT_FOUND = -1;
                if (idx == NOT_FOUND) {
                    continue;
                }
                graph.computeIfAbsent(idx, key -> new ArrayList<>()).add(i);
            }
        }
    }

    private void updateLinkScore(int n) {
        for (int i = 0; i < n; ++i) {
            double linkScore = 0;
            if (graph.containsKey(i)) {
                linkScore = graph.get(i).stream()
                    .mapToDouble(
                        idx -> (double) commonScore.get(idx) / externalLinks.get(idx).size())
                    .sum();
            }

            linkScores.add(linkScore);
        }
    }

    @Override
    public String toString() {
        return "Solution{\n" +
            " urls=" + urls +
            ",\n commonScore=" + commonScore +
            ",\n externalLinks=" + externalLinks +
            ",\n graph=" + graph +
            ",\n linkScores=" + linkScores +
            '}';
    }
}

class MatchResolver {

    private static final String URL_REGEX = "(?i)<meta property=\"og:url\" content=\"(?<url>\\S+)\"/>";
    private static final String EXTERNAL_LINK_REGEX = "(?i)<a href=\"(?<link>\\S+)\">";

    public static String findUrl(String page) {
        Matcher matcher = Pattern.compile(URL_REGEX).matcher(page);
        if (matcher.find()) {
            return matcher.group("url");
        }
        return null;
    }

    public static List<String> findExternalLink(String page) {
        Matcher matcher = Pattern.compile(EXTERNAL_LINK_REGEX).matcher(page);
        List<String> links = new ArrayList<>();
        while (matcher.find()) {
            links.add(matcher.group("link"));
        }
        return links;
    }

    public static int countMatchedWord(String word, String page) {
        String replaced = page.replaceAll("\\d+", " ");
        Matcher matcher = Pattern.compile("(?i)\\b" + word + "\\b").matcher(replaced);
        int cnt = 0;
        while (matcher.find()) {
            cnt += 1;
        }
        return cnt;
    }
}