import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {

    private static final String COMMA = ",";

    public String solution(String m, String[] musicData) {
        List<Music> musics = convertMusics(musicData);
        String melody = MelodyConverter.convert(m);
        List<Music> matchResult = match(melody, musics);
        matchResult.sort(
            Comparator.comparing(Music::getDurationMinutes, Comparator.reverseOrder()));
        return matchResult.isEmpty() ? "(None)" : matchResult.get(0).getTitle();
    }

    private List<Music> match(String melody, List<Music> musics) {
        List<Music> matches = new ArrayList<>();
        for (Music music : musics) {
            if (music.isMatchMelody(melody)) {
                matches.add(music);
            }
        }
        return matches;
    }

    private List<Music> convertMusics(String[] musicData) {
        List<Music> musics = new ArrayList<>();
        for (String datum : musicData) {
            String[] split = datum.split(COMMA);
            Music music = new Music(LocalTime.parse(split[0]),
                LocalTime.parse(split[1]),
                split[2],
                MelodyConverter.convert(split[3]));
            musics.add(music);
        }
        return musics;
    }
}


class Music {

    private final LocalTime startAt;
    private final LocalTime endAt;
    private final String title;
    private final String melody;

    public Music(LocalTime startAt, LocalTime endAt, String title, String melody) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.title = title;
        this.melody = melody;
    }

    public boolean isMatchMelody(String melody) {
        return getRealMelody().contains(melody);
    }

    public String getRealMelody() {
        Duration duration = Duration.between(startAt, endAt);
        int minutes = (int) duration.toMinutes();
        StringBuilder realMelody = new StringBuilder();
        for (int i = 0; i < minutes; ++i) {
            int idx = i % melody.length();
            realMelody.append(melody.charAt(idx));
        }
        return realMelody.toString();
    }

    public int getDurationMinutes() {
        return (int) Duration.between(startAt, endAt).toMinutes();
    }

    public String getTitle() {
        return title;
    }
}


class MelodyConverter {

    public static String convert(String melody) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < melody.length(); ++i) {
            if (i < melody.length() - 1 && melody.charAt(i + 1) == '#') {
                stringBuilder.append(Character.toLowerCase(melody.charAt(i)));
                i += 1;
                continue;
            }
            stringBuilder.append(melody.charAt(i));
        }
        return stringBuilder.toString();
    }
}