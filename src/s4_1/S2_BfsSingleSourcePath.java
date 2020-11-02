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
        for (int w : graph.adjacent(e)) {
            if (visited[w] == -1) {
                visited[w] = e;
                queue.add(w);
            }
        }
        bfs();
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

    public boolean hasPath(int target) {
        return hasPath(target, new boolean[graph.V()], new LinkedList<Integer>() {{
            add(source);
        }});
    }

    private boolean hasPath(int target, boolean[] visited, Queue<Integer> queue) {
        if (queue.isEmpty())
            return false;
        Integer v = queue.poll();
        if (v == target)
            return true;
        visited[v] = true;
        for (int w : graph.adjacent(v)) {
            if (!visited[w]) {
                queue.add(w);
                if (hasPath(target, visited, queue))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // 广度优先遍历的得到的路径是无向无权图的最短路径
        Graph graph = new Graph("src/s4_1/g.txt");
        S2_BfsSingleSourcePath singleSourcePath1 = new S2_BfsSingleSourcePath(graph, 0);
        System.out.println(Arrays.toString(singleSourcePath1.visited));
        for (int i = 0; i < graph.V(); i++) {
            if (singleSourcePath1.hasPath(i)) {
                System.out.println("0到" + i + "的路径为: " + singleSourcePath1.path(i));
            } else {
                System.out.println("0到" + i + "之间没有路径");
            }
        }
        System.out.println("----------------------------------------------");
        S2_BfsSingleSourcePath singleSourcePath2 = new S2_BfsSingleSourcePath(graph, 6);
        System.out.println(Arrays.toString(singleSourcePath2.visited));
        for (int i = 0; i < graph.V(); i++) {
            if (singleSourcePath2.hasPath(i)) {
                System.out.println("6到" + i + "的路径为: " + singleSourcePath2.path(i));
            } else {
                System.out.println("6到" + i + "之间没有路径");
            }
        }
    }
}
