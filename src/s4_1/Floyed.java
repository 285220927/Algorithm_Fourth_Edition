/**
 * @FileName: Floyed.java
 * @Author: zzc
 * @Date: 2020年11月06日 21:24:21
 * @Version V1.0.0
 */

package s4_1;

import java.util.Arrays;

public class Floyed {
    // 求出所有点对之间的最短路径，O(V^3)时间复杂度
    private final int[][] dis;
    private boolean hasNegativeCycle;

    public Floyed(WeightedGraph G) {
        dis = new int[G.V()][G.V()];
        for (int[] di : dis)
            Arrays.fill(di, Integer.MAX_VALUE);
        for (int v = 0; v < G.V(); v++) {
            dis[v][v] = 0;
            for (int w : G.adjacent(v))
                dis[v][w] = G.getWeight(v, w);
        }

        for (int t = 0; t < G.V(); t++)
            for (int v = 0; v < G.V(); v++)
                for (int w = 0; w < G.V(); w++)
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE && dis[v][t] + dis[t][w] < dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];

        for (int v = 0; v < G.V(); v++)
            if (dis[v][v] < 0)
                hasNegativeCycle = true;
    }

    public int dis(int v, int w) {
        return dis[v][w];
    }

    public boolean isNegativeCycle() {
        return hasNegativeCycle;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/s4_1/dijkstra_g.txt");
        Floyed floyed = new Floyed(weightedGraph);
        for (int i = 0; i < weightedGraph.V(); i++) {
            for (int j = 0; j < weightedGraph.V(); j++) {
                System.out.println(i + "到" + j + "的最短距离是: " + floyed.dis(i, j));
            }
        }
    }
}
