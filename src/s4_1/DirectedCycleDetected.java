/**
 * @FileName: DirectedCycleDetected.java
 * @Author: zzc
 * @Date: 2020年11月07日 12:55:44
 * @Version V1.0.0
 */

package s4_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DirectedCycleDetected {
    // 有向图的环检测
    private final Graph G;
    private final boolean[] visited;
    private final boolean[] onPath; // 是否在当前路径上
    private final int[] path;
    private final List<Integer> cyclePath = new ArrayList<>();
    private boolean hasCycle;

    public DirectedCycleDetected(Graph G) {
        if (!G.isDirected())
            throw new IllegalArgumentException("DirectedCycleDetected only work in directed graph");
        this.G = G;
        this.path = new int[G.V()];
        Arrays.fill(this.path, -1);
        this.path[0] = 0;
        this.visited = new boolean[G.V()];
        this.onPath = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
    }

    private boolean dfs(int v) {
        visited[v] = true;
        onPath[v] = true;
        for (int w : G.adjacent(v)) {
            if (!visited[w]) {
                path[w] = v;
                if (dfs(w))
                    return true;
            } else if (onPath[w]) {
                for (int x = v; x != w; x = path[x])
                    cyclePath.add(x);
                cyclePath.add(w);
                Collections.reverse(cyclePath);
                return true;
            }
        }
        onPath[v] = false;
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public Iterable<Integer> getCyclePath() {
        return cyclePath;
    }

    public static void main(String[] args) {
        DirectedCycleDetected directedCycleDetected = new DirectedCycleDetected(new Graph("src/s4_1/direct_g.txt", true));
        System.out.println(directedCycleDetected.hasCycle());
        System.out.println(directedCycleDetected.getCyclePath());
    }
}
