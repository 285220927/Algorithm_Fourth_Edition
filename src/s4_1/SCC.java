/**
 * @FileName: SCC.java
 * @Author: zzc
 * @Date: 2020年11月10日 21:05:01
 * @Version V1.0.0
 */

package s4_1;

public class SCC {
    // 有向图强连通分量
    private final Graph G;
    private final boolean[] visited;
    private final int[] id;
    private int count;

    public SCC(Graph G) {
        if (!G.isDirected())
            throw new IllegalArgumentException("scc only works in directed graph");
        this.G = G;
        this.id = new int[G.V()];
        this.visited = new boolean[G.V()];
        Graph reverseG = G.reverse();
        // 原图的后序遍历不等于反转图的后序遍历的逆序!
        for (int v : new DirectedGraphDfs(reverseG).reversePost()) {
            if (!visited[v]) {
                count++;
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        id[v] = count;
        visited[v] = true;
        for (int w : G.adjacent(v)) {
            if (!visited[w])
                dfs(w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/s4_1/scc_g.txt", true);
        SCC scc = new SCC(graph);
        System.out.println("图的强连通分量大小: " + scc.count());
        for (int v = 0; v < graph.V(); v++) {
            System.out.println("顶点" + v + "的强连通分量id为: " + scc.id(v));
            for (int w = 0; w < graph.V(); w++) {
                System.out.println(v + "和" + w + "在一个强连通分量吗? " + scc.stronglyConnected(v, w));
            }
        }
    }
}
