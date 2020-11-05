/**
 * @FileName: Dijkstra.java
 * @Author: zzc
 * @Date: 2020年11月04日 21:16:30
 * @Version V1.0.0
 */

package s4_1;

import java.util.Arrays;

public class Dijkstra {
    // 迪杰斯特拉 无向有权图的最短路径 O(V^2)的时间复杂度
    // 迪杰斯特拉算法不能处理负权边
    private final int[] dis; // 从原点s到dis[v]的距离
    private final boolean[] visited; // 从原点s到visited[v]的最短路径已经确认

    public Dijkstra(WeightedGraph G, int s) {
        this.dis = new int[G.V()];
        this.visited = new boolean[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        while (true) {
            // 第一步 找到没有确定最短路径的节点
            int curDis = Integer.MAX_VALUE;
            int curV = -1;
            for (int v = 0; v < G.V(); v++) {
                if (!visited[v] && dis[v] < curDis) {
                    curDis = dis[v];
                    curV = v;
                }
            }
            if (curV == -1)
                break;

            // 第二步 从原点s到curV节点的最小路径已经确定
            visited[curV] = true;

            // 第三步 根据curV的最短路径的大小，更新其他节点的路径长度
            for (int w: G.adjacent(curV)) {
                if (visited[w])
                    continue;
                dis[w] = Math.min(dis[w], dis[curV] + G.getWeight(curV, w));
            }
        }
    }

    public boolean connected(int v) {
        return visited[v];
    }

    public int disTo(int v) {
        return dis[v];
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/s4_1/dijkstra_g.txt");
        Dijkstra dijkstra = new Dijkstra(weightedGraph, 0);
        for (int v = 0; v < weightedGraph.V(); v++)
            System.out.print(dijkstra.disTo(v) + " ");
    }
}
