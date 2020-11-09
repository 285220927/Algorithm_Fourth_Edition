/**
 * @FileName: Digraph.java
 * @Author: zzc
 * @Date: 2020年10月12日 21:30:53
 * @Version V1.0.0
 */

package s4_1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class Graph implements Cloneable {
    // 使用邻接表数据结构
    private int V; // 顶点数目 vertex
    private int E; // 边数目 edge
    private final boolean directed;
    private TreeSet<Integer>[] adjacencyList; // 邻接表 可以用HashSet 用set代表忽略平行边

    private int[] in_degrees; // 有向图每个顶点入度数量
    private int[] out_degrees; // 有向图每个顶点出度数量

    public Graph(String filepath, boolean directed) {
        this.directed = directed;
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            E = scanner.nextInt();
            adjacencyList = new TreeSet[V];
            in_degrees = new int[V];
            out_degrees = new int[V];
            for (int i = 0; i < adjacencyList.length; i++)
                adjacencyList[i] = new TreeSet<>();
            while (scanner.hasNext()) {
                addEdge(scanner.nextInt(), scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Graph(int V, boolean directed) {
        this.V = V;
        in_degrees = new int[V];
        out_degrees = new int[V];
        this.adjacencyList = new TreeSet[V];
        this.directed = true;
        for (int v = 0; v < V; v++) {
            this.adjacencyList[v] = new TreeSet<>();
        }
    }

    public Graph(int V) {
        this(V, true);
    }

    public Graph(String filepath) {
        this(filepath, false);
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        // 将顶点v和顶点w相连，形成一条边
        adjacencyList[v].add(w);
        if (directed) {
            // 修改入度和出度的数量
            out_degrees[v]++;
            in_degrees[w]++;
        } else
            adjacencyList[w].add(v);
    }

    public void removeEdge(int v, int w) {
        adjacencyList[v].remove(w);
        if (directed) {
            out_degrees[v]--;
            in_degrees[w]--;
        } else
            adjacencyList[w].remove(v);
    }

    public Iterable<Integer> adjacent(int v) {
        // 与v顶点相邻的顶点
        return adjacencyList[v];
    }

    public int degree(int v) {
        if (directed)
            throw new IllegalArgumentException("degree only works in undirected graph");
        return adjacencyList[v].size();
    }

    public int inDegree(int v) {
        if (!directed)
            throw new IllegalArgumentException("inDegree only works in directed graph");
        return in_degrees[v];
    }

    public int outDegree(int v) {
        if (!directed)
            throw new IllegalArgumentException("outDegree only works in directed graph");
        return out_degrees[v];
    }

    public boolean hasEdge(int v, int w) {
        // 返回两个顶点之间是否有边
        return adjacencyList[v].contains(w);
    }

    public boolean isDirected() {
        return directed;
    }

    public Graph reverse() {
        if (!directed)
            throw new IllegalArgumentException("reverse only works in directed graph");
        Graph G = new Graph(V);
        G.E = this.E;
        for (int v = 0; v < V; v++)
            for (int w : adjacent(v))
                G.addEdge(w, v);
        return G;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Graph cloneGraph = (Graph) super.clone();
        TreeSet<Integer>[] cloneAdj = new TreeSet[V];
        for (int i = 0; i < V; i++) {
            cloneAdj[i] = new TreeSet<>();
            for (int v : adjacencyList[i])
                cloneAdj[i].add(v);
        }
        return cloneGraph;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V = ").append(V()).append(" E = ").append(E()).append(" directed = ").append(directed).append("\n");
        for (int i = 0; i < V(); i++) {
            sb.append(i).append(": ").append(adjacencyList[i]).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 邻接表空间复杂度 O(V + E)
        Graph graph = new Graph("src/s4_1/direct_g.txt", true);
        System.out.println(graph);
    }
}
