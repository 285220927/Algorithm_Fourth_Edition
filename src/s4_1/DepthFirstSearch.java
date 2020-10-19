/**
 * @FileName: DepthFirstSearch.java
 * @Author: zzc
 * @Date: 2020年10月14日 20:02:34
 * @Version V1.0.0
 */

package s4_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearch {
    // 深度优先搜索 P338
    private final int[] visited; // 用int数组代替boolean数组，数组中存放每个顶点的连通分量id，初始值为-1
    private final Graph graph;
    private final Set<Integer> pre = new HashSet<>(); // 先序遍历结果
    private final Set<Integer> post = new HashSet<>(); // 后序遍历结果
    private int connectComponent = 0; // 连通分量

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        Arrays.fill(visited, -1);
        for (int i = 0; i < graph.V(); i++) // 在外层加一个循环，防止非连通图的部分节点无法被访问到
            if (visited[i] == -1) {
                dfs(i);
                connectComponent++;
            }
    }

    private void dfs(int v) {
        pre.add(v); // 图的深度优先先序遍历
        visited[v] = connectComponent;
        for (int w: graph.adjacent(v))
            if (visited[w] == -1)
                dfs(w);
        post.add(v); // 图的深度优先后序遍历
    }

    public boolean isConnected(int s,int w) {
        // s与w是否连通
        return visited[s] == visited[w];
    }

    public int connectComponent() {
        return connectComponent;
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> order() {
        return post;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/s4_1/g.txt");
        DepthFirstSearch dfs = new DepthFirstSearch(graph);
        // O(V + E)的时间复杂度
        System.out.println("先序遍历: " + dfs.pre());
        System.out.println("后序遍历: " + dfs.order());
        System.out.println("连通分量: " + dfs.connectComponent());
        System.out.println("visited: " + Arrays.toString(dfs.visited));
        System.out.println("0和1是否连通: " + dfs.isConnected(0, 1));
        System.out.println("0和5是否连通: " + dfs.isConnected(0, 5));
    }
}
