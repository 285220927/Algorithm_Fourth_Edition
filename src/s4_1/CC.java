/**
 * @FileName: CC.java
 * @Author: zzc
 * @Date: 2020年11月08日 13:27:09
 * @Version V1.0.0
 */

package s4_1;

public class CC {
    // 无向图连通分量
    private final Graph G;
    private final boolean[] visited;
    private final int[] id;
    private int count;

    public CC(Graph G) {
        if (G.isDirected())
            throw new IllegalArgumentException("CC only works in undirected graph");
        this.G = G;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v]) {
                count++;
                dfs(v);
            }
    }

    private void dfs(int v) {
        visited[v] = true;
        id[v] = count;
        for (int w : G.adjacent(v))
            if (!visited[w])
                dfs(w);
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Graph G = new Graph("src/s4_1/g.txt");
        CC cc = new CC(G);
        System.out.println(cc.count());
        System.out.println(cc.connected(0, 1));
        System.out.println(cc.connected(0, 5));
        for (int v = 0; v < G.V(); v++)
            System.out.println(cc.id(v));
    }
}
