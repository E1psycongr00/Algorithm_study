class Solution {

    public int solution(int a, int b, int n) {
        Mart mart = new Mart(a, b);
        return mart.exchangeBottle(n);
    }
}


class Mart {

    private final int getCokeSize;
    private final int returnCokeSize;

    public Mart(int getCokeSize, int returnCokeSize) {
        this.getCokeSize = getCokeSize;
        this.returnCokeSize = returnCokeSize;
    }

    public int exchangeBottle(int amount) {
        int returnCoke = 0;
        while (canExchangeBottle(amount)) {
            returnCoke += _exchangeBottle(amount);
            amount = _exchangeBottle(amount) + amount % getCokeSize;
        }
        return returnCoke;
    }

    private int _exchangeBottle(int amount) {
        int size = amount / getCokeSize;
        return size * returnCokeSize;
    }

    private boolean canExchangeBottle(int amount) {
        return amount >= getCokeSize;
    }
}