const type = {
    0: ["R", "T"],
    1: ["C", "F"],
    2: ["J", "M"],
    3: ["A", "N"],
};

function getScore(surveyType, choice) {
    const score = choice - 4;
    const type = score > 0 ? surveyType.charAt(1) : surveyType.charAt(0);
    return [type, Math.abs(score)];
}

function solution(survey, choices) {
    const table = new Map();
    for (let i = 0; i < choices.length; ++i) {
        const [t, score] = getScore(survey[i], choices[i]);
        table.set(t, (table.get(t) || 0) + score);
    }
    var result = [];
    for (let i = 0; i < 4; ++i) {
        const [c1, c2] = type[i];
        const s1 = table.get(c1) || 0;
        const s2 = table.get(c2) || 0;
        if (s1 < s2) {
            result.push(c2);
        } else {
            result.push(c1);
        }
    }
    return result.join("");
}
