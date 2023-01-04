package backjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    public String[] solution(int[][] queries) {
        List<String> result = new ArrayList<>();
        for (int[] query : queries) {
            List<Integer> path = MendelAlgorithm.findPath(query[0], query[1]);
            GenericCharacter genericCharacter = MendelAlgorithm.findGenericCharacter(path);
            result.add(genericCharacter.toString());
        }
        return result.toArray(String[]::new);
    }
}

class MendelAlgorithm {

    public static List<Integer> findPath(int generation, long index) {
        List<Integer> path = new ArrayList<>();
        long nextIndex = index;
        while (generation > 0) {
            nextIndex = addPathIndex(path, generation, nextIndex);
            generation--;
        }
        Collections.reverse(path);
        return path;
    }

    public static GenericCharacter findGenericCharacter(List<Integer> path) {
        GenericCharacter root = GenericCharacter.Rr;
        GenericCharacter currentCharacter = root;
        for (Integer pick : path) {
            currentCharacter = currentCharacter.getChild(pick);
        }
        return currentCharacter;
    }

    private static long addPathIndex(List<Integer> path, int generation, long index) {
        if (generation <= 1 && index <= 1) {
            return index;
        }
        long div = index / 4;
        int mod = (int) (index % 4);
        if (mod == 0) {
            path.add(4);
            return div;
        }
        path.add(mod);
        return div + 1;
    }
}

enum GenericCharacter {
    RR {
        @Override
        public GenericCharacter getChild(int index) {
            return RR;
        }
    },
    Rr {
        @Override
        public GenericCharacter getChild(int index) {
            if (index == 1) {
                return RR;
            }
            if (index == 4) {
                return rr;
            }
            return Rr;
        }
    },
    rr {
        @Override
        public GenericCharacter getChild(int index) {
            return rr;
        }
    };

    public abstract GenericCharacter getChild(int index);

    @Override
    public String toString() {
        return name();
    }
}




