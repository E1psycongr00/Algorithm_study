function solution(id_list, report, k) {
    let reports = [...new Set(report)].map(a=>{return a.split(' ')});
    let counts = new Map();
    for (const [reporter, bad] of reports){
        counts.set(bad,counts.get(bad)+1||1)
    }
    let good = new Map();
    for(const [reporter, bad] of reports){
        if(counts.get(bad)>=k){
            good.set(reporter, good.get(reporter)+1||1)
        }
    }
    let answer = id_list.map(a=>good.get(a)||0)
    return answer;
}