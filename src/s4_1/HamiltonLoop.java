/**
 * @FileName: HamiltonLoop.java
 * @Author: zzc
 * @Date: 2020年10月31日 13:12:47
 * @Version V1.0.0
 */

package s4_1;

import java.util.*;

public class HamiltonLoop {
    // 哈密尔顿回路
    private final Graph graph;
    private final boolean[] visited;
    private final int[] pre;
    private int end = -1;

    public HamiltonLoop(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        dfs(0, 0);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (int w: graph.adjacent(v)) {
            if (!visited[w]) {
                if (dfs(w, v))
                    return true;
            } else if (w == 0 && allVisited()) {
                end = v;
                return true;
            }
        }
        visited[v] = false;
        return false;
    }

    private boolean allVisited() {
        for (boolean v : visited) {
            if (!v)
                return false;
        }
        return true;
    }

    public Iterable<Integer> getPath() {
        List<Integer> res = new ArrayList<>();
        if (end == -1)
            return res;
        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        HamiltonLoop hamiltonLoop = new HamiltonLoop(new Graph("src/s4_1/hamilton_g.txt"));
        System.out.println(hamiltonLoop.getPath());
    }
}
