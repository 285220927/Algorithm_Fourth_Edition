/**
 * @FileName: S2_InsertSort.java
 * @Author: zzc
 * @Date: 2020年09月25日 21:37:16
 * @Version V1.0.0
 */

package s2_1;

import java.util.Arrays;

@SuppressWarnings({"unchecked", "WeakerAccess"})
public class S2_InsertSort {
    // P157
    public static void insertSort1(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void insertSort2(Comparable[] a) {
        // P168 2.1.25 向右移动 减少交换次数
        for (int i = 1; i < a.length; i++) {
            Comparable temp = a[i];
            int j;
            for (j = i - 1; j >= 0 && temp.compareTo(a[j]) < 0; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }


    public static void main(String[] args) {
        /**
         * 插入排序适用于接近已经有序的数组或者是元素的位置和它的正确位置相差较近的时候
         * 对于一个已经有序的数组，插入排序的复杂度是线性级别的
         */
        Integer[] arr = new Integer[]{2, 4, 6, 8, 0, 9, 7, 5, 3, 1};
        insertSort1(arr);
//        insertSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
