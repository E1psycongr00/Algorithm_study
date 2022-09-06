function solution(N, stages) {
    const failureRate = [];
    for (let i = 1; i <= N; ++i) {
        failureRate.push(
            stages.filter(stage => i == stage).length /
                stages.filter(stage => i <= stage).length
        );
    }
    return [...Array(N+1).keys()].splice(1).sort((a, b)=> failureRate[b-1] - failureRate[a-1]);
}

console.log(solution(5, [2, 1, 2, 6, 2, 4, 3, 3]));
