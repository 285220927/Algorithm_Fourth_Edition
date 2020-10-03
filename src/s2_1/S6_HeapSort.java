/**
 * @FileName: S7_HeapSort.java
 * @Author: zzc
 * @Date: 2020年10月03日 11:29:25
 * @Version V1.0.0
 */

package s2_1;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked", "SameParameterValue"})
public class S6_HeapSort {
    private static Comparable[] data;
    private static int size = 0;

    // 堆排序
    // https://www.cnblogs.com/jingmoxukong/p/4303826.html
    public static void heapSort(Comparable[] arr) {
        data = new Comparable[arr.length];
        // 初始化构造堆
        for (Comparable c : arr) {
            insert(c);
        }

        // 将最大值和最后一个叶子节点交换，得到最大值，然后将交换后的堆顶进行下沉操作
        while (size > 0) {
            if (size == 1) {
                // 最后一个元素
                arr[0] = data[--size];
                break;
            }
            ArrayUtils.swap(data, 0, --size);
            arr[size] = data[size];
            data[size] = null;
            sink(0);
        }
    }

    private static void insert(Comparable e) {
        data[size] = e;
        swim(size);
        size++;
    }

    private static void swim(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[index].compareTo(data[parent]) > 0) {
                ArrayUtils.swap(data, index, parent);
                index = parent;
            } else
                break;
        }
    }

    private static void sink(int index) {
        while (index * 2 + 1 <= size - 1) {
            int left = index * 2 + 1; // 左子节点
            int right = index * 2 + 2; // 右子节点
            int i = left;
            if (right <= size - 1 && data[left].compareTo(data[right]) < 0)
                i = right;
            if (data[index].compareTo(data[i]) >= 0)
                break;
            ArrayUtils.swap(data, i, index);
            index = i;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
        heapSort(arr);
        /**
         *        9
         *      8   6
         *    7  2  3  5
         *  1  4 0
         */
        System.out.println(Arrays.toString(arr));
    }
}

