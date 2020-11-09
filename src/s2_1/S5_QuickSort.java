/**
 * @FileName: S5_QuickSort.java
 * @Author: zzc
 * @Date: 2020年10月01日 10:31:45
 * @Version V1.0.0
 */

package s2_1;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class S5_QuickSort {
    // 快速排序P182
    public static void quickSort1(Comparable[] arr) {
        quickSort1(arr, 0, arr.length - 1);
    }

    private static void quickSort1(Comparable[] arr, int left, int right) {
        if (left >= right)
            return;
        int i = partition(arr, left, right);
        quickSort1(arr, left, i - 1);
        quickSort1(arr, i + 1, right);
    }

    private static int partition(Comparable[] arr, int left, int right) {
        // 切分
        Comparable mid = arr[left];
        while (left < right) {
            while (left < right && arr[right].compareTo(mid) >= 0)
                right--;
            if (left < right)
                arr[left] = arr[right];
            while (left < right && arr[left].compareTo(mid) <= 0)
                left++;
            if (left < right)
                arr[right] = arr[left];
        }
        arr[left] = mid;
        return left;
    }

    public static void quickSort2(Comparable[] arr) {
        // 三路快排
        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort2(Comparable[] arr, int left, int right) {
        if (left >= right)
            return;

        // 标定点
        Comparable mid = arr[left];
        int lt = left; // arr[left + 1: lt] < mid;
        int gt = right + 1; // arr[gt: right] > mid;
        int i = left + 1; // arr[lt + 1: i] == v;
        while (i < gt) {
            if (arr[i].compareTo(mid) < 0) {
                ArrayUtils.swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i].compareTo(mid) > 0)
                ArrayUtils.swap(arr, i, --gt);
              else
                i++;
        }
        ArrayUtils.swap(arr, left, lt);
        quickSort2(arr, left, lt - 1);
        quickSort2(arr, gt, right);
    }

    public static void main(String[] args) {
        /**
         * 快速排序
         * 如果切分不平衡，会使程序非常低效，如已经有序的数组，第一次切分的结果索引为0，第二次为1，第三次为2... 可以在排序前打乱数组或者使用三路快排
         */
        Integer[] arr = new Integer[]{0, 4, 6, 8, 0, 9, 7, 5, 3, 1, 0};
        quickSort1(arr);
        System.out.println(Arrays.toString(arr));
    }
}
