/**
 * @FileName: PriorityQueue.java
 * @Author: zzc
 * @Date: 2020年10月02日 10:56:52
 * @Version V1.0.0
 */

package s2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SameParameterValue")
public class PriorityQueue<E extends Comparable<E>> {
    private final List<E> data;

    public PriorityQueue() {
        data = new ArrayList<>();
    }

    public PriorityQueue(E[] data) {
        this.data = new ArrayList<>(Arrays.asList(data));
        for (E e : this.data) {
            insert(e);
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E max() {
        if (isEmpty())
            throw new IllegalArgumentException();
        return data.get(0);
    }

    private void swim(int index) {
        // 上浮
        int parent = (index - 1) / 2;
        while (index > 0) {
            if (parent >= 0 && data.get(index).compareTo(data.get(parent)) > 0) {
                swap(index, parent);
                index /= 2;
            } else
                return;
        }
    }

    private void swap(int p, int q) {
        E temp = data.get(p);
        data.set(p, data.get(q));
        data.set(q, temp);
    }

    private void sink(int index) {
        // 下沉
        while (index * 2 + 1 < data.size()) { // 还有子节点就继续查找
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            // i为2个子节点中较大的那一个，把他浮上来
            int i = leftChild;
            // i + 1 < size() 是因为可能没有右子树
            if (i + 1 < size() && data.get(leftChild).compareTo(data.get(rightChild)) < 0) {
                i = rightChild;
            }
            if (data.get(index).compareTo(data.get(i)) >= 0)
                break;
            swap(i, index);
            index = i;
        }
    }

    public void insert(E e) {
        data.add(e);
        swim(size() - 1);
    }

    public E delMax() {
        E ret = max();
        // 第一个元素和最后一个元素交换位置，然后对交换后的第一个元素进行下沉操作
        data.set(0, data.get(size() - 1));
        data.remove(data.size() - 1);
        sink(0);
        return ret;
    }

    public static void main(String[] args) {
        // 二叉堆: 每个节点都大于它的子节点
        /**       索引
         *         0
         *       1   2
         *     3  4 5  6
         *
         *        数据
         *        100
         *      80   90
         *    30 50 10 70
         */

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.insert(5);
        queue.insert(0);
        queue.insert(15);
        queue.insert(10);
        while (!queue.isEmpty())
            System.out.println(queue.delMax());
    }
}
