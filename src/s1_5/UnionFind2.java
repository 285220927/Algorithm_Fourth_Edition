/**
 * @FileName: UnionFind2.java
 * @Author: zzc
 * @Date: 2020年09月22日 19:28:37
 * @Version V1.0.0
 */

package s1_5;

public class UnionFind2 implements UnionFind {
    private int count;
    private int[] id;
    private int[] sz; // 记录树的节点数，合并时将小树合并到大树上，防止树的深度过高

    public UnionFind2(int count) {
        this.count = count;
        id = new int[count];
        sz = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int find(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]]; // 路径压缩
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId)
            return;
        if (sz[pId] < sz[qId]) {
            // 小树挂在大数上
            id[pId] = qId;
            sz[qId] += sz[pId];
        } else {
            id[qId] = pId;
            sz[pId] += sz[qId];
        }
        this.count--;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
