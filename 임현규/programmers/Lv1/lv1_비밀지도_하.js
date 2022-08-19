const solution = (n, arr1, arr2) => {
    answer = [];
    for (let i = 0; i < arr1.length; ++i) {
        const val = (arr1[i] | arr2[i]);
        const binary = val.toString(2).padStart(n, '0');
        const result = binary.replace(/0/g, " ").replace(/1/g, "#");
        answer.push(result);
    }
    return answer;
}