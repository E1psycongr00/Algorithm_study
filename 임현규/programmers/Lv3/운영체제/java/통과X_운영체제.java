import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {

    public long[] solution(int[][] programList) {
        List<Program> programs = makePrograms(programList);
        OperateSystem operateSystem = makeOperateSystem(programs);
        boolean isExecute = operateSystem.executeProgram();
        while (isExecute) {
            isExecute = operateSystem.executeProgram();
        }
        return operateSystem.getResult();
    }

    private List<Program> makePrograms(int[][] programList) {
        return Arrays.stream(programList).map(Program::from).collect(Collectors.toList());
    }

    private OperateSystem makeOperateSystem(List<Program> programs) {
        ProgramBuffer programBuffer = new ProgramBuffer(programs);
        ProgramWaitingBuffer programWaitingBuffer = new ProgramWaitingBuffer();
        return new OperateSystem(programBuffer, programWaitingBuffer);
    }
}


class Program {

    private final int score;
    private final long startTime;
    private final long executeTime;

    public Program(int score, long startTime, long executeTime) {
        this.score = score;
        this.startTime = startTime;
        this.executeTime = executeTime;
    }

    public static Program from(int[] result) {
        return new Program(result[0], result[1], result[2]);
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public int getScore() {
        return score;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Program{" +
            "score=" + score +
            ", startTime=" + startTime +
            ", executeTime=" + executeTime +
            '}';
    }
}

class ProgramBuffer {

    private final PriorityQueue<Program> buffer = new PriorityQueue<>(
        Comparator.comparing(Program::getStartTime));

    public ProgramBuffer(Collection<Program> programs) {
        buffer.addAll(programs);
    }

    public List<Program> popAllByUnderCurrentTime(long currentTime) {
        List<Program> programs = new ArrayList<>();
        while (!buffer.isEmpty() && buffer.peek().getStartTime() <= currentTime) {
            programs.add(buffer.poll());
        }
        return programs;
    }

    public Program top() {
        return buffer.peek();
    }

    public boolean isExist() {
        return !buffer.isEmpty();
    }
}

class ProgramWaitingBuffer {

    private final PriorityQueue<Program> buffer = new PriorityQueue<>(
        Comparator.comparing(Program::getScore).thenComparing(Program::getStartTime));

    public void addAll(List<Program> programs) {
        buffer.addAll(programs);
    }

    public Program pop() {
        return buffer.poll();
    }

    public boolean isExist() {
        return !buffer.isEmpty();
    }
}

class OperateSystem {

    private final long[] result = new long[11];
    private final ProgramBuffer programBuffer;
    private final ProgramWaitingBuffer programWaitingBuffer;


    public OperateSystem(ProgramBuffer programBuffer, ProgramWaitingBuffer programWaitingBuffer) {
        this.programBuffer = programBuffer;
        this.programWaitingBuffer = programWaitingBuffer;
    }

    public boolean executeProgram() {
        List<Program> programs = programBuffer.popAllByUnderCurrentTime(result[0]);
        programWaitingBuffer.addAll(programs);
        if (programs.isEmpty() && programBuffer.isExist()) {
            result[0] = programBuffer.top().getStartTime();
            programs = programBuffer.popAllByUnderCurrentTime(result[0]);
            programWaitingBuffer.addAll(programs);
        }
        if (programWaitingBuffer.isExist()) {
            Program program = programWaitingBuffer.pop();
            updateWaitTimeByScore(program);
            updateCurrentTime(program);
            return true;
        }
        return false;
    }

    private void updateWaitTimeByScore(Program program) {
        if (program.getStartTime() < result[0]) {
            result[program.getScore()] += result[0] - program.getStartTime();
        }
    }

    private void updateCurrentTime(Program program) {
        long startTime = program.getStartTime();
        long currentTime = result[0];
        if (currentTime < startTime) {
            result[0] = startTime;
        }
        result[0] += program.getExecuteTime();
    }


    public long[] getResult() {
        return result;
    }
}