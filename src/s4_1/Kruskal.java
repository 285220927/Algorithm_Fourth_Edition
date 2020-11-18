/**
 * @FileName: Kruskal.java
 * @Author: zzc
 * @Date: 2020年11月02日 20:26:02
 * @Version V1.0.0
 */

package s4_1;

import s1_5.UnionFind2;

import java.util.*;

public class Kruskal {
    // 无向有权图的最小生成树
    // Kruskal算法首先按权重对边进行排序，从权重最小的边开始，如果加入该边之0后图中没有出现环，那么该边一定是最小生成树中的一边
    // O(ElogE)的时间复杂度，其中排序占的时间最多为O(ElogE)，判断是否是连通图为O(V + E) 是线性级别，最后使用并查集判断两点是否连通，是接近O(1)的复杂度
    // 最小生成树不能处理有向图，那是一个更加困难的有向图处理问题：最小树形图
    private final List<WeightedEdge> mst = new ArrayList<>(); // 最小生成树的边

    public Kruskal(WeightedGraph G) {
        // 这里忽略连通分量的判断，要求图的最小生成树，整个图必须要是连通的
        List<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++)
            for (int w: G.adjacent(v))
                if (w > v)
                    // 一条边会被表示两次 w的临边中有v v的临边中有w
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));

        // 按照权重进行排序
        Collections.sort(edges);
        UnionFind2 uf = new UnionFind2(G.V());
        for (WeightedEdge edge : edges) {
            // 使用并查集来判断v和w是否是连通的
            // 如果v和w已经连通了，那么v-w边连接之后一定会形成一个环，不符合最小生成树的性质
            if (!uf.connected(edge.getV(), edge.getW())) {
                mst.add(edge);
                uf.union(edge.getV(), edge.getW());
            }
        }
    }

    public Iterable<WeightedEdge> mst() {
        return mst;
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal(new WeightedGraph("src/s4_1/weighted_g.txt"));
        System.out.println(kruskal.mst());
    }
}
