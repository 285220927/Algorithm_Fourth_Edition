/**
 * @FileName: S2_BfsSingleSourcePath.java
 * @Author: zzc
 * @Date: 2020年10月22日 19:46:02
 * @Version V1.0.0
 */

package s4_1;

import java.util.*;

public class S2_BfsSingleSourcePath {
    // 广度优先单源路径搜索
    private final Graph graph;
    private final int source;
    private final int[] visited;
    private final Queue<Integer> queue = new LinkedList<>();

    public S2_BfsSingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        visited = new int[graph.V()];
        Arrays.fill(visited, -1);
        visited[source] = source;
        queue.add(source);
        bfs();
    }

    private void bfs() {
        if (queue.isEmpty())
            return;
        int e = queue.remove();
        for (int w: graph.adjacent(e)) {
            if (visited[w] == -1) {
                visited[w] = e;
                queue.add(w);
                bfs();
            }
        }
    }

    public Iterable<Integer> path(int target) {
        List<Integer> pathList = new ArrayList<>();
        if (visited[target] == -1)
            return pathList;
        pathList.add(target);
        while (target != source) {
            int next = visited[target];
            pathList.add(next);
            target = next;
        }
        Collections.reverse(pathList);
        return pathList;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/s4_1/g.txt");
        S2_BfsSingleSourcePath singleSourcePath1 = new S2_BfsSingleSourcePath(graph, 0);
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("0到" + i + "的路径为: " + singleSourcePath1.path(i));
        }

        S2_BfsSingleSourcePath singleSourcePath2 = new S2_BfsSingleSourcePath(graph, 6);
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("6到" + i + "的路径为: " + singleSourcePath2.path(i));
        }
    }
}
