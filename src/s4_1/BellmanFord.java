/**
 * @FileName: BellmanFord.java
 * @Author: zzc
 * @Date: 2020年11月06日 20:18:22
 * @Version V1.0.0
 */

package s4_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BellmanFord {
    // 可以有负权边的最短路径 O(V*E) 的时间复杂度
    private final int source;
    private final int[] dis;
    private boolean hasNegativeCycle = false;
    private final int[] pre;

    public BellmanFord(WeightedGraph G, int s) {
        this.source = s;
        this.dis = new int[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);
        dis[s] = 0;

        // 最多遍历v - 1次
        for (int pass = 1; pass < G.V(); pass++) {
            // 遍历所有的边
            for (int v = 0; v < G.V(); v++)
                for (int w : G.adjacent(v))
                    if (dis[v] != Integer.MAX_VALUE && dis[w] > dis[v] + G.getWeight(v, w)) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                        pre[w] = v;
                    }
        }
        // 还要再循环一次，如果dis[w]发生了变化，那么说明出现了负权环
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adjacent(v))
                if (dis[v] != Integer.MAX_VALUE && dis[w] > dis[v] + G.getWeight(v, w))
                    hasNegativeCycle = true;
    }

    public boolean isNegativeCycle() {
        return hasNegativeCycle;
    }

    public int disTo(int v) {
        if (isNegativeCycle())
            throw new RuntimeException("detected negative cycle");
        return dis[v];
    }

    public Iterable<Integer> path(int v) {
        List<Integer> res = new ArrayList<>();
        if (dis[v] == Integer.MAX_VALUE)
            return res;
        while (v != source) {
            res.add(v);
            v = pre[v];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/s4_1/dijkstra_g.txt");
        BellmanFord bellmanFord = new BellmanFord(weightedGraph, 0);
        for (int v = 0; v < weightedGraph.V(); v++) {
            System.out.println(bellmanFord.path(v));
        }
    }
}
