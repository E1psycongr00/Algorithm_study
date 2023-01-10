import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(int[][] userList, int[] emoticonList) {
        List<List<Emoticon>> emoticonCases = makeDisCountEmoticonCase(emoticonList);
        List<User> users = makeUsers(userList);
        List<int[]> answers = new ArrayList<>();
        for (List<Emoticon> emoticons : emoticonCases) {
            int[] temp = new int[2];
            for (User user : users) {
                int[] buy = user.buy(emoticons);
                temp[0] += buy[0];
                temp[1] += buy[1];
            }
            answers.add(temp);
        }
        answers.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        return answers.get(0);
    }

    private List<User> makeUsers(int[][] users) {
        return Arrays.stream(users).map(ints -> new User(ints[0], ints[1]))
            .collect(Collectors.toList());
    }

    private List<List<Emoticon>> makeDisCountEmoticonCase(int[] emoticons) {
        List<List<DisCountPolicy>> generate = Product.generate(
            Arrays.stream(DisCountPolicy.values()).collect(Collectors.toList()),
            emoticons.length);
        List<List<Emoticon>> result = new ArrayList<>();
        for (List<DisCountPolicy> disCountPolicies : generate) {
            List<Emoticon> tempList = new ArrayList<>();
            for (int j = 0; j < emoticons.length; ++j) {
                tempList.add(new Emoticon(disCountPolicies.get(j), emoticons[j]));
            }
            result.add(tempList);
        }
        return result;
    }
}

class User {

    private final int purchaseRate;
    private final int limitPrice;

    public User(int purchaseRate, int limitPrice) {
        this.purchaseRate = purchaseRate;
        this.limitPrice = limitPrice;
    }

    public int[] buy(List<Emoticon> emoticons) {
        int[] result = new int[2];
        for (Emoticon emoticon : emoticons) {
            if (canBuy(emoticon)) {
                result[1] += emoticon.getDiscountPrice();
            }
        }
        if (isJoinSubscribePrice(result[1])) {
            result[1] = 0;
            result[0] = 1;
        }
        return result;
    }

    public boolean canBuy(Emoticon emoticon) {
        DisCountPolicy disCountPolicy = emoticon.getDisCountPolicy();
        return !disCountPolicy.isLess(this.purchaseRate);
    }

    public boolean isJoinSubscribePrice(int totalPrice) {
        return totalPrice >= limitPrice;
    }
}

class Emoticon {

    private final DisCountPolicy disCountPolicy;
    private final int price;

    public Emoticon(DisCountPolicy disCountPolicy, int price) {
        this.disCountPolicy = disCountPolicy;
        this.price = price;
    }

    public DisCountPolicy getDisCountPolicy() {
        return disCountPolicy;
    }

    public int getDiscountPrice() {
        return disCountPolicy.discount(price);
    }

    @Override
    public String toString() {
        return "Emoticon{" +
            "disCountPolicy=" + disCountPolicy +
            ", price=" + price +
            '}';
    }
}

enum DisCountPolicy {
    TEN(10),
    TWENTY(20),
    THIRTY(30),
    FORTY(40);

    private final int discountRate;

    DisCountPolicy(int discountRate) {
        this.discountRate = discountRate;
    }

    public boolean isLess(int rate) {
        return this.discountRate < rate;
    }

    public int discount(int price) {
        return price * (100 - this.discountRate) / 100;
    }
}

class Product {

    public static <T extends Comparable<? super T>> List<List<T>> generate(List<T> items,
        int length) {
        Collections.sort(items);
        List<List<T>> picked = new ArrayList<>();
        backTrack(picked, items, new ArrayList<>(), length);
        return picked;
    }

    private static <T extends Comparable<? super T>> void backTrack(List<List<T>> answer,
        List<T> items, List<T> tempList,
        int length) {
        if (tempList.size() == length) {
            answer.add(new ArrayList<>(tempList));
            return;
        }
        for (T item : items) {
            tempList.add(item);
            backTrack(answer, items, tempList, length);
            tempList.remove(tempList.size() - 1);
        }
    }
}

