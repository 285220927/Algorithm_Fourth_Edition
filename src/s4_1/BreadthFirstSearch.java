/**
 * @FileName: BreadthFirstSearch.java
 * @Author: zzc
 * @Date: 2020年10月21日 21:44:00
 * @Version V1.0.0
 */

package s4_1;


import java.util.*;

public class BreadthFirstSearch {
    // 广度优先遍历
    private final Graph graph;
    private final boolean[] visited;
    private final Queue<Integer> queue = new LinkedList<>();
    // 广度优先遍历只有一个顺序
    private final List<Integer> pre = new ArrayList<>();

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        // 有可能存在多个连通分量
        for (int i = 0; i < graph.V(); i++) {
            if (!visited[i]) {
                queue.add(i);
                bfs();
            }
        }
    }

    private void bfs() {
        if (queue.isEmpty())
            return;
        int e = queue.remove();
        visited[e] = true;
        pre.add(e);
        for (int w : graph.adjacent(e)) {
            if (!visited[w]) {
                visited[w] = true;
                queue.add(w);
            }
        }
        bfs();
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public static void main(String[] args) {
        // 广度优先遍历O(V + E)的时间复杂度
        // dfs和bfs相同，区别在于在非递归实现时，dfs需要使用栈，bfs需要使用队列
        BreadthFirstSearch bfs = new BreadthFirstSearch(new Graph("src/s4_1/direct_g.txt"));
        System.out.println(bfs.pre());
    }
}
