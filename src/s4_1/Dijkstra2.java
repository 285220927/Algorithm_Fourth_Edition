/**
 * @FileName: Dijkstra2.java
 * @Author: zzc
 * @Date: 2020年11月05日 19:38:39
 * @Version V1.0.0
 */

package s4_1;

import java.util.*;

public class Dijkstra2 {
    // 优先队列优化迪杰斯特拉算法 O(ElogE)的时间复杂度
    private final int source;
    private final int[] dis;
    private final boolean[] visited;
    private final int[] pre; // 存放最短路径经过的顶点

    private static class Node implements Comparable<Node> {
        private final int v, dis;

        private Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis - o.dis;
        }
    }

    public Dijkstra2(WeightedGraph G, int s) {
        this.source = s;
        dis = new int[G.V()];
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);
        pre[s] = s;
        dis[0] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, 0));

        while (!queue.isEmpty()) {
            int curV = queue.remove().v; // 选出一条dis值最小的边
            // 会有重复的v放入队列中，在后续从队列中可能会拿出已经判断过的v，应该跳过，只需要取出dis最小的v即可
            if (visited[curV])
                continue;
            visited[curV] = true;
            for (int w : G.adjacent(curV)) {
                if (!visited[w]) {
                    if (dis[w] > dis[curV] + G.getWeight(curV, w)) {
                        dis[w] = dis[curV] + G.getWeight(curV, w);
                        queue.add(new Node(w, dis[w]));
                        pre[w] = curV;
                    }
                }
            }
        }
    }

    public boolean connected(int v) {
        return visited[v];
    }

    public int disTo(int v) {
        return dis[v];
    }

    public Iterable<Integer> shortestPath(int v) {
        List<Integer> res = new ArrayList<>();
        if (!connected(v))
            return res;
        int cur = v;
        while (cur != source) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/s4_1/dijkstra_g.txt");
        Dijkstra2 dijkstra2 = new Dijkstra2(weightedGraph, 0);
        for (int v = 0; v < weightedGraph.V(); v++) {
            System.out.print(dijkstra2.disTo(v) + " ");
        }
        System.out.println();
        for (int v = 0; v < weightedGraph.V(); v++) {
            System.out.println(dijkstra2.shortestPath(v));
        }
    }
}
