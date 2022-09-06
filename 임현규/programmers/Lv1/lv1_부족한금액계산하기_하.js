function solution(price, money, count) {
    const totalPrice = price * count * (count + 1 ) / 2;
    return money - totalPrice > 0 ? 0 : -(money - totalPrice);


}

console.log(solution(3, 20, 4))
