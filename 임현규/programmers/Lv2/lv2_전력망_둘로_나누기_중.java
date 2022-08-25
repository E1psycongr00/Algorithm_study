package org.example.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        this.parent = new int[n+1];
        for (int i = 0; i <=n; ++i) {
            this.parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void merge(int u, int v) {
        int findU = find(u);
        int findV = find(v);
        parent[findV] = findU;
    }

    public int getDifferenceByComponent() {
        for (int i = 0; i < parent.length; i++) {
            find(i);
        }
        Map<Integer, Long> counter = Arrays.stream(parent).boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        assert(counter.size() == 3);
        List<Long> values = new ArrayList<>(counter.values());
        return (int)(Math.abs(values.get(1) - values.get(2)));
    }

    @Override
    public String toString() {
        return "UnionFind{" +
            "parent=" + Arrays.toString(parent) +
            '}';
    }
}



class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 1000000000;
        for(int i = 0; i <wires.length; ++i) {
            UnionFind unionFind = new UnionFind(n);
            for (int j = 0; j < wires.length; ++j) {
                if (i == j) continue;
                unionFind.merge(wires[j][0], wires[j][1]);
            }
            answer = Math.min(answer, unionFind.getDifferenceByComponent());

        }
        return answer;
    }
}