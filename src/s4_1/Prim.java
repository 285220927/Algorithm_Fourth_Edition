/**
 * @FileName: Prim.java
 * @Author: zzc
 * @Date: 2020年11月03日 21:44:04
 * @Version V1.0.0
 */

package s4_1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {
    private static final List<WeightedEdge> mst = new ArrayList<>();

    public Prim(WeightedGraph G) {
        // 同Kruskal算法 忽略连通性判断
        Queue<WeightedEdge> queue = new PriorityQueue<>();
        // 横切边原理 横切边的两端在数组中必须是一端是true 另一端是false
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        for (int w: G.adjacent(0)) {
            queue.add(new WeightedEdge(0, w, G.getWeight(0, w)));
        }
        while (!queue.isEmpty()) {
            WeightedEdge minEdge = queue.poll();
            // 判断该边是否合法(还是不是横切边了)
            if (visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;
            mst.add(minEdge);
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;
            for (int w: G.adjacent(newV))
                if (!visited[w])
                    queue.add(new WeightedEdge(newV, w, G.getWeight(newV, w)));
        }
    }

    public Iterable<WeightedEdge> mst() {
        return mst;
    }

    public static void main(String[] args) {
        Prim prim = new Prim(new WeightedGraph("src/s4_1/weighted_g.txt"));
        System.out.println(prim.mst());
    }
}
