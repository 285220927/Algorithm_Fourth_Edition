/**
 * @FileName: S4_MergeSort.java
 * @Author: zzc
 * @Date: 2020年09月28日 20:52:55
 * @Version V1.0.0
 */

package s2_1;

import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class S4_MergeSort {
    // 归并排序P170
    private static Comparable[] aux; // 辅助的数组

    public static void mergeSort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Comparable[] arr, int left, int right) {
        if (left >= right)
            return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid); // 将左半边排序
        mergeSort(arr, mid + 1, right); // 将右半边排序
        merge(arr, mid, left, right); // 归并
    }

    private static void merge(Comparable[] arr, int mid, int left, int right) {
        // 将arr复制到aux中
        if (right + 1 - left >= 0)
            System.arraycopy(arr, left, aux, left, right + 1 - left);

        int j = mid + 1;
        for (int i = left; i <= right; i++) {
            if (left > mid)
                arr[i] = aux[j++];
            else if (j > right)
                arr[i] = aux[left++];
            else if (aux[j].compareTo(aux[left]) < 0)
                arr[i] = aux[j++];
            else
                arr[i] = aux[left++];
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{2, 4, 6, 8, 0, 9, 7, 5, 3, 1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
