/**
 * @FileName: TopoSort.java
 * @Author: zzc
 * @Date: 2020年11月07日 15:30:26
 * @Version V1.0.0
 */

package s4_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSort {
    private final List<Integer> res = new ArrayList<>();

    // 有向图的拓扑排序
    public TopoSort(Graph G) {
        if (!G.isDirected())
            throw new IllegalArgumentException("topo sort only works in directed graph");

        int[] in_degrees = new int[G.V()];
        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            in_degrees[v] = G.inDegree(v);
            if (G.inDegree(v) == 0)
                queue.add(v);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            for (int next : G.adjacent(cur)) {
                if (--in_degrees[next] == 0)
                    queue.add(next);
            }
        }

        // 环检测
        if (res.size() != G.V())
            // 存在环
            res.clear();
    }

    public Iterable<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        TopoSort topoSort = new TopoSort(new Graph("src/s4_1/topo_g.txt", true));
        System.out.println(topoSort.result());
    }
}
