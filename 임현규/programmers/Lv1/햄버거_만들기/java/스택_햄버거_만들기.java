import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] ingredients) {
        HamburgerStack hamburgerStack = new HamburgerStack();
        for (int ingredient : ingredients) {
            hamburgerStack.addIngredient(ingredient);
        }
        return hamburgerStack.getHamburgerCount();
    }
}


class HamburgerStack {
    public enum Ingredient {
        BREAD,
        VEGETABLE,
        PATTY;

        public static Ingredient toIngredient(int number) {
            if (number == 1) {
                return BREAD;
            }
            if (number == 2) {
                return VEGETABLE;
            }
            if (number == 3) {
                return PATTY;
            }
            throw new IllegalArgumentException();
        }
    }

    private final List<Ingredient> stack = new ArrayList<>();

    private int hamburgerCount = 0;

    public void addIngredient(int ingredient) {
        stack.add(Ingredient.toIngredient(ingredient));
        updateHamburgerCount();
    }

    private void updateHamburgerCount() {
        int lastIndex = stack.size() - 1;
        if (stack.size() < 4) {
            return;
        }
        if (stack.get(lastIndex -3) == Ingredient.BREAD
        && stack.get(lastIndex - 2) == Ingredient.VEGETABLE
        && stack.get(lastIndex - 1) == Ingredient.PATTY
        && stack.get(lastIndex) == Ingredient.BREAD) {
            pop();
            pop();
            pop();
            pop();
            hamburgerCount++;
        }
    }

    private void pop() {
        int lastIndex = stack.size() - 1;
        stack.remove(lastIndex);
    }

    @Override
    public String toString() {
        return "HamburgerStack{" +
            "stack=" + stack +
            '}';
    }

    public int getHamburgerCount() {
        return hamburgerCount;
    }
}