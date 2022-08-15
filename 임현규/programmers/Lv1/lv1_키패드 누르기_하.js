function solution(numbers, hand) {
    var handPosition = {
        left: "*",
        right: "#",
    };

    const answer = numbers.map(element => {
        if ([1, 4, 7].includes(element)) {
            handPosition["left"] = element;
            return "L";
        }
        if ([3, 6, 9].includes(element)) {
            handPosition.right = element;
            return "R";
        }

        if ([2, 5, 8, 0].includes(element)) {
            const val1 = calculateDistance(element, handPosition["left"]);
            const val2 = calculateDistance(element, handPosition["right"]);
            if (val1 > val2) {
                handPosition["right"] = element;
                return "R";
            }
            if (val1 < val2) {
                handPosition["left"] = element;
                return "L";
            }
            if (val1 == val2) {
                handPosition[hand] = element;
                return hand[0].toUpperCase();
            }
        }
    });
    return answer.join("");
}

function convert(pad) {
    const convertMap = {
        "*": 10,
        0: 11,
        "#": 12,
    };

    if (convertMap.hasOwnProperty(pad)) {
        return convertMap[pad];
    }
    return pad;
}

function getPlane(number) {
    const row = Math.floor((number - 1) / 3);
    const col = (number - 1) % 3;
    return [row, col];
}

function calculateDistance(a, b) {
    const [r1, c1] = getPlane(convert(a));
    const [r2, c2] = getPlane(convert(b));
    return Math.abs(r1 - r2) + Math.abs(c1 - c2);
}

console.log(solution([1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], "right"));
