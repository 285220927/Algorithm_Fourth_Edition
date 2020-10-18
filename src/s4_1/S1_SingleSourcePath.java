/**
 * @FileName: S1_SingleSourcePath.java
 * @Author: zzc
 * @Date: 2020年10月18日 12:55:11
 * @Version V1.0.0
 */

package s4_1;

import java.util.*;

public class S1_SingleSourcePath {
    // 单源路径搜索
    private final Graph graph;
    private final int source;
    private final int[] visited; // int数组，存放该节点的上一个节点，初始为-1

    public S1_SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        visited = new int[graph.V()];
        Arrays.fill(visited, -1);
        dfs(source);
    }

    private void dfs(int v) {
        for (int w : graph.adjacent(v)) {
            if (visited[w] == -1) {
                visited[w] = v;
                dfs(w);
            }
        }
    }

    public Iterable<Integer> path(int v) {
        List<Integer> list = new ArrayList<>();
        if (visited[v] == -1)
            return list;
        list.add(v);
        while (v != source) {
            int next = visited[v];
            list.add(next);
            v = next;
        }
        Collections.reverse(list);
        return list;
    }

    public boolean hasPath(int v) {
        return hasPath(v, new boolean[graph.V()]);
    }

    private boolean hasPath(int v, boolean[] paths) {
        if (v == source)
            return true;
        for (int w : graph.adjacent(v)) {
            if (!paths[w]) {
                paths[w] = true;
                if (hasPath(w, paths))
                    return true;
            }
        }
        return false;
    }

//    public boolean hasCycle() {
//        // 图中是否有环
//        return hasCycle(0, new int[graph.V()]);
//    }
//
//    private boolean hasCycle(int v, int[] cycle) {
//
//    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/s4_1/g.txt");
        S1_SingleSourcePath singleSourcePath = new S1_SingleSourcePath(graph, 0);
        System.out.println(Arrays.toString(singleSourcePath.visited));
        for (int i = 0; i < graph.V(); i++) {
            if (singleSourcePath.hasPath(i))
                System.out.println("0和" + i + "之间存在路径: " + singleSourcePath.path(i));
            else
                System.out.println("0和" + i + "之间不存在路径");
        }
        System.out.println("----------------------------------------------");
        S1_SingleSourcePath singleSourcePath2 = new S1_SingleSourcePath(graph, 6);
        for (int i = 0; i < graph.V(); i++) {
            if (singleSourcePath2.hasPath(i))
                System.out.println("6和" + i + "之间存在路径: " + singleSourcePath2.path(i));
            else
                System.out.println("6和" + i + "之间不存在路径");
        }
    }
}
