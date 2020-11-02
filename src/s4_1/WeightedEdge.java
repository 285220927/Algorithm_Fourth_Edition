/**
 * @FileName: Edge.java
 * @Author: zzc
 * @Date: 2020年11月02日 20:27:54
 * @Version V1.0.0
 */

package s4_1;

public class WeightedEdge implements Comparable<WeightedEdge> {
    private final int v;
    private final int w;
    private final int weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("(%d-%d: %d)", v, w, weight);
    }
    @Override
    public int compareTo(WeightedEdge o) {
        return weight - o.weight;
    }
}
