import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 처리 대기 중인 작업을 추적하는 데 사용하는 기간별로 정렬된 작업의 우선 순위 대기열
 */
class Solution {

    /**
     * 
     * @param jobs 정수의 2D 배열
     * @return 주어진 모든 작업을 완료하는 데 걸리는 평균 시간
     */
    public int solution(int[][] jobs) {
        int answer = 0;
        int end = 0;
        int count = 0;
        int index = 0;
        List<Job> jobList = makeJobs(jobs);
        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparing(Job::getDuration));

        while (count < jobs.length) {
            while (index < jobs.length && isProcessing(end, index, jobList)) {
                pq.add(jobList.get(index));
                index++;
            }
            // processing 상태가 아니고 pq 에도 없는 경우 pq에 넣어서 처리하기 위해 end 값 초기화
            // ex) end = 3, Job = (4, 4)
            if (pq.isEmpty()) {
                end = jobList.get(index).getCall();
            } else {    // 실제 처리 로직
                Job job = pq.poll();
                end = job.getDuration() + end;
                answer += end - job.getCall();
                count++;
            }
        }
        return answer / jobList.size();
    }

    /**
     * 정수 배열의 배열을 취하고 호출 시간별로 정렬된 작업 목록을 반환
     * 
     * @param jobs 
     * @return 호출 시간별로 정렬된 작업 목록
     */
    private List<Job> makeJobs(int[][] jobs) {
        return Arrays.stream(jobs)
            .map(job -> new Job(job[0], job[1]))
            .sorted(Comparator.comparing(Job::getCall))
            .collect(Collectors.toList());
    }

    /**
     * > 이 함수는 주어진 인덱스에 있는 작업이 주어진 종료 시간에 처리되고 있는지 확인
     * 
     * @param end 마지막 작업의 종료 시간
     * @param index jobList에 있는 작업의 인덱스
     * @param jobList 처리할 작업 목록
     * @return 주어진 시간에 처리할 수 있는 작업의 수
     */
    private boolean isProcessing(int end, int index, List<Job> jobList) {
        return jobList.get(index).getCall() <= end;
    }
}

class Job {

    private final int call;
    private final int duration;

    public Job(int call, int duration) {
        this.call = call;
        this.duration = duration;
    }

    public int getCall() {
        return call;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Job{" +
            "call=" + call +
            ", duration=" + duration +
            '}';
    }
}