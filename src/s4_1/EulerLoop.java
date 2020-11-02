/**
 * @FileName: EulerLoop.java
 * @Author: zzc
 * @Date: 2020年11月01日 10:38:03
 * @Version V1.0.0
 */

package s4_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EulerLoop {
    // 欧拉回路 每个边都会被经过有且仅有1次
    // 欧拉回路的路径中每个顶点的度必须是偶数(一进一出)
    private final Graph graph;

    public EulerLoop(Graph graph) {
        this.graph = graph;
    }

    private boolean hasEulerLoop() {
        for (int i = 0; i < graph.V(); i++)
            if (graph.degree(i) % 2 == 1)
                return false;
        return true;
    }

    public Iterable<Integer> eulerPath() {
        Graph g;
        try {
            g = (Graph) graph.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
        List<Integer> res = new ArrayList<>();
        if (!hasEulerLoop())
            return res;
        Stack<Integer> stack = new Stack<>();
        int curV = 0;
        stack.push(curV);
        while (!stack.isEmpty()) {
            if (g.degree(curV) != 0) {
                // 还有别的路可以走
                stack.push(curV);
                int nextV = g.adjacent(curV).iterator().next();
                g.removeEdge(curV, nextV);
                curV = nextV;
            } else {
                res.add(curV);
                curV = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        EulerLoop eulerLoop = new EulerLoop(new Graph("src/s4_1/eulerLoop.txt"));
        System.out.println(eulerLoop.eulerPath());
    }
}
