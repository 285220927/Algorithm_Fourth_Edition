/**
 * @FileName: UnionFind.java
 * @Author: zzc
 * @Date: 2020年09月20日 16:13:24
 * @Version V1.0.0
 */

package s1_5;

public class UnionFind1 implements UnionFind {
    // P139
    // 并查集
    private int[] id;
    private int count; // 连通分量的数量

    public UnionFind1(int nums) {
        count = nums;
        id = new int[nums];
        for (int i = 0; i < nums; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];
        if (pId == qId) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) id[i] = qId;
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
}
