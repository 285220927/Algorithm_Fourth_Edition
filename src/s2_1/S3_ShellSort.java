/**
 * @FileName: S3_ShellSort.java
 * @Author: zzc
 * @Date: 2020年09月26日 11:57:11
 * @Version V1.0.0
 */

package s2_1;

import java.util.Arrays;

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class S3_ShellSort {
    public static void shellSort(Comparable[] arr) {
        int N = arr.length;
        // step 步长，每循环一次，步长就为上一次的一半
        for (int step = N / 2; step > 0; step /= 2) {
            // 共step个组，对每一组进行插入排序
            for (int i = step; i < N; i++) {
                insertSort(arr, i, step);
            }
        }
    }

    /**
     * 对原数组的指定下标进行插入排序
     * @param arr      原数组
     * @param i        索引
     * @param step     步长
     */
    private static void insertSort(Comparable[] arr, int i, int step) {
        Comparable temp = arr[i];
        int j;
        for (j = i - step; j >= 0 && temp.compareTo(arr[j]) < 0; j -= step) {
            arr[j + step] = arr[j];
        }
        arr[j + step] = temp;
    }

    public static void main(String[] args) {
        /**
         * 希尔排序是插入排序的延申
         */
        Integer[] arr = new Integer[]{2, 4, 6, 8, 0, 9, 7, 5, 3, 1};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
