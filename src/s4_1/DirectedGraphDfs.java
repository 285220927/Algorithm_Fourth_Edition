/**
 * @FileName: DirectedGraphDfs.java
 * @Author: zzc
 * @Date: 2020年11月10日 21:07:59
 * @Version V1.0.0
 */

package s4_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DirectedGraphDfs {
    private final Graph G;
    private final boolean[] visited;
    private final List<Integer> pre = new ArrayList<>();
    private final List<Integer> post = new ArrayList<>();
    private final List<Integer> reversePost = new ArrayList<>();

    public DirectedGraphDfs(Graph G) {
        if (!G.isDirected())
            throw new IllegalArgumentException("directed graph dfs only works in directed graph");
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v);
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        for (int w : G.adjacent(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
        reversePost.add(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        Collections.reverse(reversePost);
        return reversePost;
    }
}
