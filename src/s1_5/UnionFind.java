/**
 * @FileName: UnionFind.java
 * @Author: zzc
 * @Date: 2020年09月21日 22:03:17
 * @Version V1.0.0
 */

package s1_5;

public interface UnionFind {

    int count();

    int find(int p);

    void union(int p, int q);

    boolean connected(int p, int q);
}
