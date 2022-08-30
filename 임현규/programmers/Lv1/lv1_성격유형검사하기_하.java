import java.util.HashMap;

class Solution {

    public String solution(String[] survey, int[] choices) {
        Survey surveyObject = new Survey();
        return surveyObject.takeSurvey(survey, choices);
    }
}

class Survey {

    private final String[] typeIndex = new String[]{"RT", "CF", "JM", "AN"};


    public String takeSurvey(String[] characterType, int[] choices) {
        if (characterType.length != choices.length) {
            throw new RuntimeException("두 입력 길이가 같아야함");
        }
        int n = choices.length;
        HashMap<Character, Integer> scoreTable = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            CharacterScore characterScore = take(characterType[i], choices[i]);
            scoreTable.put(characterScore.getType(),
                scoreTable.getOrDefault(characterScore.getType(), 0) + characterScore.getScore());
        }
        char[] result = new char[4];
        for(int i = 0; i <4; ++i) {
            result[i] = compare(scoreTable, i);
        }
        return String.valueOf(result);
    }

    private char compare(HashMap<Character, Integer> scoreTable, int index) {
        char c1 = typeIndex[index].charAt(0);
        char c2 = typeIndex[index].charAt(1);

        int s1 = scoreTable.getOrDefault(c1, 0);
        int s2 = scoreTable.getOrDefault(c2, 0);

        if (s1 < s2) {
            return c2;
        } // s1 >= s2
        return c1;
    }

    private CharacterScore take(String SurveyType, int choice) {
        int score = choice - 4;
        char type = score > 0 ? SurveyType.charAt(1) : SurveyType.charAt(0);
        return new CharacterScore(type, Math.abs(score));
    }


    private static class CharacterScore {

        private final char type;
        private final int score;

        public CharacterScore(char type, int score) {
            this.type = type;
            this.score = score;
        }

        public char getType() {
            return type;
        }

        public int getScore() {
            return score;
        }
    }
}