import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(String[] info, String[] querys) {
        Set<Member> members = makeMembers(info);
        List<Query> queries = makeQueries(querys);
        List<Long> result = new ArrayList<>();
        for (Query query : queries) {
            result.add(query.request(members));
        }
        return result.stream().mapToInt(Long::intValue).toArray();
    }

    private Set<Member> makeMembers(String[] info) {
        return Arrays.stream(info)
            .map(Member::fromString)
            .collect(Collectors.toSet());
    }

    private List<Query> makeQueries(String[] querys) {
        return Arrays.stream(querys)
            .map(Query::fromString)
            .collect(Collectors.toList());
    }

}


enum Lang {
    JAVA("java"),
    PYTHON("python"),
    CPP("cpp"),
    ANY("-");

    private final String name;

    Lang(String name) {
        this.name = name;
    }

    public static Lang fromString(String name) {
        return Arrays.stream(Lang.values())
            .filter(lang -> name.equals(lang.name))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}

enum Major {
    BACKEND("backend"),
    FRONTEND("frontend"),
    ANY("-");

    private final String name;

    Major(String name) {
        this.name = name;
    }

    public static Major fromString(String name) {
        return Arrays.stream(Major.values())
            .filter(major -> name.equals(major.name))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}

enum Career {
    JUNIOR("junior"),
    SENIOR("senior"),
    ANY("-");

    private final String name;

    Career(String name) {
        this.name = name;
    }

    public static Career fromString(String name) {
        return Arrays.stream(Career.values())
            .filter(career -> name.equals(career.name))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}

enum Food {
    PIZZA("pizza"),
    CHICKEN("chicken"),
    ANY("-");

    private final String name;

    Food(String name) {
        this.name = name;
    }

    public static Food fromString(String name) {
        return Arrays.stream(Food.values())
            .filter(food -> name.equals(food.name))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}

class Query {

    private final Lang lang;
    private final Major major;
    private final Career career;
    private final Food food;
    private final int score;

    public Query(Lang lang, Major major, Career career, Food food, int score) {
        this.lang = lang;
        this.major = major;
        this.career = career;
        this.food = food;
        this.score = score;
    }

    public static Query fromString(String s) {
        String replaced = s.replaceAll(" and ", " ");
        String[] split = replaced.split(" ");
        return new Query(Lang.fromString(split[0]),
            Major.fromString(split[1]),
            Career.fromString(split[2]),
            Food.fromString(split[3]),
            Integer.parseInt(split[4]));
    }

    public long request(Set<Member> members) {
        return members.stream()
            .filter(member -> {
                if (lang.equals(Lang.ANY)) {
                    return true;
                }
                return member.getLang().equals(lang);
            })
            .filter(member -> {
                if (major.equals(Major.ANY)) {
                    return true;
                }
                return member.getMajor().equals(major);
            })
            .filter(member -> {
                if (career.equals(Career.ANY)) {
                    return true;
                }
                return member.getCareer().equals(career);
            })
            .filter(member -> {
                if (food.equals(Food.ANY)) {
                    return true;
                }
                return member.getFood().equals(food);
            })
            .filter(member -> member.getScore() >= score)
            .count();
    }
}

class Member {

    private final Lang lang;
    private final Major major;
    private final Career career;
    private final Food food;
    private final int score;

    public Member(Lang lang, Major major, Career career, Food food, int score) {
        this.lang = lang;
        this.major = major;
        this.career = career;
        this.food = food;
        this.score = score;
    }

    public static Member fromString(String s) {
        String[] split = s.split(" ");
        return new Member(Lang.fromString(split[0]),
            Major.fromString(split[1]),
            Career.fromString(split[2]),
            Food.fromString(split[3]),
            Integer.parseInt(split[4]));
    }

    public Lang getLang() {
        return lang;
    }

    public Major getMajor() {
        return major;
    }

    public Career getCareer() {
        return career;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        return "Member{" +
            "lang=" + lang +
            ", major=" + major +
            ", career=" + career +
            ", food=" + food +
            ", score=" + score +
            '}';
    }
}