/**
 * @FileName: Digraph.java
 * @Author: zzc
 * @Date: 2020年10月12日 21:30:53
 * @Version V1.0.0
 */

package s4_1;

import utils.DoubleLinkedList;

@SuppressWarnings("unchecked")
public class Digraph {
    // 有向图 使用邻接表数据结构
    private final int V; // 顶点数目
    private int E; // 边数目
    private final DoubleLinkedList<Integer>[] adjacencyList; // 邻接表

    public Digraph(int v) {
        V = v;
        E = 0;
        adjacencyList = (DoubleLinkedList<Integer>[]) new DoubleLinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacencyList[i] = new DoubleLinkedList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        // 将顶点v和顶点w相连，形成一条边
        adjacencyList[v].append(w);
        adjacencyList[w].append(v);
        E++;
    }

    public Iterable<Integer> adjacent(int v) {
        // 与v顶点相邻的顶点
        return adjacencyList[v];
    }
}
