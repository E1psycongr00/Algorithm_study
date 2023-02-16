import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    public String solution(String m, String[] musicinfos) {
        String searchMelody = Music.convertMelody(m);
        List<Music> limit = Stream.of(musicinfos)
            .map(musicinfo -> {
                String[] split = musicinfo.split(",");
                return new Music(split[0], split[1], split[2], split[3]);
            })
            .filter(music -> music.containMelody(searchMelody))
            .sorted()
            .collect(Collectors.toList());
        return limit.isEmpty() ? "(None)" : limit.get(0).getTitle();
    }

    static class Music implements Comparable<Music> {

        private final int startTime;
        private final int endTime;
        private final String title;
        private final String flatMelody;

        public Music(String startTime, String endTime, String title, String melody) {
            this.startTime = Time.convertMinutes(startTime);
            this.endTime = Time.convertMinutes(endTime);
            this.title = title;
            this.flatMelody = flatMelody(this.endTime - this.startTime, melody);
        }

        public boolean containMelody(String melody) {
            return flatMelody.contains(melody);
        }

        @Override
        public int compareTo(Solution.Music o) {
            int durationCompare = Integer.compare(getDuration(), o.getDuration());
            if (durationCompare == 0) {
                return Integer.compare(startTime, o.startTime);
            }
            return -durationCompare;
        }

        public int getDuration() {
            return endTime - startTime;
        }

        public String getTitle() {
            return title;
        }

        public static String convertMelody(String stringMelody) {
            return Objects.requireNonNull(stringMelody)
                .replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");
        }

        public static String flatMelody(int duration, String melody) {
            String convertedMelody = convertMelody(melody);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < duration; ++i) {
                stringBuilder.append(convertedMelody.charAt(i % convertedMelody.length()));
            }
            return stringBuilder.toString();
        }
    }
}


class Time {

    public static int convertMinutes(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
