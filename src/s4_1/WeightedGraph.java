/**
 * @FileName: WeightedGraph.java
 * @Author: zzc
 * @Date: 2020年11月01日 16:32:18
 * @Version V1.0.0
 */

package s4_1;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class WeightedGraph {
    private int V; // 顶点数目 vertex
    private int E; // 边数目 edge
    private final boolean directed;
    private TreeMap<Integer, Integer>[] adjacencyList; // 邻接表 可以用HashSet 用set代表忽略平行边

    public WeightedGraph(String filepath, boolean directed) {
        this.directed = directed;
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            E = scanner.nextInt();
            adjacencyList = new TreeMap[V];
            for (int i = 0; i < adjacencyList.length; i++)
                adjacencyList[i] = new TreeMap<>();
            while (scanner.hasNext()) {
                addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WeightedGraph(String filepath) {
        this(filepath, false);
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w, int weight) {
        // 将顶点v和顶点w相连，形成一条边
        adjacencyList[v].put(w, weight);
        if (!directed)
            adjacencyList[w].put(v, weight);
    }

    public void removeEdge(int v, int w) {
        adjacencyList[v].remove(w);
        if (!directed)
            adjacencyList[w].remove(v);
    }

    public Iterable<Integer> adjacent(int v) {
        // 与v顶点相邻的顶点
        return adjacencyList[v].keySet();
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w))
            return adjacencyList[v].get(w);
        throw new IllegalArgumentException();
    }

    public int degree(int v) {
        if (directed)
            throw new IllegalArgumentException("degree only works in undirected graph");
        return adjacencyList[v].size();
    }

    public boolean hasEdge(int v, int w) {
        // 返回两个顶点之间是否有边
        return adjacencyList[v].containsKey(w);
    }

    public boolean isDirected() {
        return directed;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Graph cloneGraph = (Graph) super.clone();
        TreeMap<Integer, Integer>[] cloneAdj = new TreeMap[V];
        for (int i = 0; i < V; i++) {
            cloneAdj[i] = new TreeMap<>();
            for (Map.Entry<Integer, Integer> entry : adjacencyList[i].entrySet())
                cloneAdj[i].put(entry.getKey(), entry.getValue());
        }
        return cloneGraph;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V = ").append(V()).append(" E = ").append(E()).append("\n");
        for (int i = 0; i < V(); i++) {
            sb.append(i).append(": ").append(adjacencyList[i]).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src/s4_1/dijkstra_g.txt", true);
        System.out.println(graph);
    }
}
