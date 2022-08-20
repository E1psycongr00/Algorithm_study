import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Solution {

    private final JustThatSong justThatSong = new JustThatSongImpl();

    public String solution(String m, String[] musicinfos) {
        List<SongInfo> songInfoList = Arrays.stream(musicinfos).map(SongInfo::of)
            .collect(Collectors.toList());
        return justThatSong.findSongTitle(m, songInfoList);
    }
}

interface JustThatSong {

    String findSongTitle(String sample, List<SongInfo> songInfos);
}

interface SongInterpreter {

    String interpretMelody(SongInfo songInfo);
    int interpretMinuteDifference(SongInfo songInfo);
    String convertMelody(String melody);
}

interface SongMatcher {

    boolean match(String sample, String melody);
}

class JustThatSongImpl implements JustThatSong {

    private final SongInterpreter songInterpreter = new SongInterpreterImpl();
    private final SongMatcher songMatcher = new SongMatcherImpl();

    private static final int INVALID_LENGTH = -10000000;
    
    @Override
    public String findSongTitle(String sample, List<SongInfo> songInfos) {
        String convertedSample = songInterpreter.convertMelody(sample);
        return songInfos.stream().map(songInfo -> {
                String melody = songInterpreter.interpretMelody(songInfo);
                if (songMatcher.match(convertedSample, melody)) {
                    return new MatchResult(songInfo.getTitle(),
                        songInterpreter.interpretMinuteDifference(songInfo));
                } else {
                    return new MatchResult("(None)", INVALID_LENGTH);
                }
            }).max(Comparator.comparing(MatchResult::getLength))
            .map(MatchResult::getTitle)
            .get();
    }

    private static class MatchResult {

        private String title;
        private int length;

        public MatchResult(String title, int length) {
            this.title = title;
            this.length = length;
        }

        public String getTitle() {
            return title;
        }

        public int getLength() {
            return length;
        }
    }
}

class SongInterpreterImpl implements SongInterpreter {

    @Override
    public String interpretMelody(SongInfo songInfo) {
        int diffMinute = interpretMinuteDifference(songInfo);
        String convertedMelody = convertMelody(songInfo.getMelody());
        return interpretMelody(convertedMelody, diffMinute);
    }

    @Override
    public int interpretMinuteDifference(SongInfo songInfo) {
        LocalTime localTime = LocalTime.parse(songInfo.getStartTime());
        LocalTime endTime = LocalTime.parse(songInfo.getEndTime());

        Duration duration = Duration.between(localTime, endTime);
        int diffMinute = (int) duration.toMinutes();

        return diffMinute;
    }

    @Override
    public String convertMelody(String melody) {
        StringBuilder sb = new StringBuilder();
        int n = melody.length();

        int i = 0;
        while (i < n) {
            if (i + 1 < n && melody.charAt(i+1) == '#') {
                sb.append(Character.toLowerCase(melody.charAt(i)));
                i++;
            } else {
                sb.append(melody.charAt(i));
            }
            i += 1;
        }
        return sb.toString();
    }

    private String interpretMelody(String melody, long diffMinute) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < diffMinute; ++i) {
            int index = i % melody.length();
            sb.append(melody.charAt(index));
        }
        return sb.toString();
    }
}

class SongMatcherImpl implements SongMatcher {


    @Override
    public boolean match(String sample, String melody) {
        return melody.contains(sample);
    }


}

// vo
class SongInfo {

    private final String startTime;
    private final String endTime;
    private final String title;
    private final String melody;

    public SongInfo(String startTime, String endTime, String title, String melody) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.melody = melody;
    }

    public static SongInfo of(String info) {
        String[] split = info.split(",");
//        if (split.length > 4) {
//            throw new Exception("데이터 갯수 초과");
//        }
        return new SongInfo(split[0], split[1], split[2], split[3]);
    }


    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getMelody() {
        return melody;
    }
}