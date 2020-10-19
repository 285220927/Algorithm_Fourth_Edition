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
public class Graph {
    // 无向无权图 使用邻接表数据结构
    private int V; // 顶点数目 vertex
    private int E; // 边数目 edge
    private TreeSet<Integer>[] adjacencyList; // 邻接表 可以用HashSet 用set代表忽略平行边

    public Graph(String filepath) {
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            E = scanner.nextInt();
            adjacencyList = new TreeSet[V];
            for (int i = 0; i < adjacencyList.length; i++)
                adjacencyList[i] = new TreeSet<>();
            while (scanner.hasNext()) {
                addEdge(scanner.nextInt(), scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        adjacencyList[v].add(w);
        adjacencyList[w].add(v);
    }

    public Iterable<Integer> adjacent(int v) {
        // 与v顶点相邻的顶点
        return adjacencyList[v];
    }

    public int degree(int v) {
        return adjacencyList[v].size();
    }

    public boolean hasEdge(int v, int w) {
        // 返回两个顶点之间是否有边
        return adjacencyList[v].contains(w);
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
        // 邻接表空间复杂度 O(V + E)
        Graph graph = new Graph("src/s4_1/g.txt");
        System.out.println(graph);
    }
}
