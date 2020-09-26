/**
 * @FileName: SelectSort.java
 * @Author: zzc
 * @Date: 2020年09月25日 21:20:55
 * @Version V1.0.0
 */

package s2_1;

import java.util.Arrays;

@SuppressWarnings({"unchecked", "WeakerAccess"})
public class S1_SelectSort {
    // P155
    public static void selectSort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            Comparable min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (min.compareTo(arr[j]) > 0) {
                    min = arr[j];
                    Comparable temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 选择排序的特点:
         * 1.如果一个数组已经有序，选择排序依然需要对整个数组进行遍历后找出一个最小的元素
         * 2.数据移动次数是最少的
         */
        Integer[] arr = new Integer[] {2, 4, 6, 8, 0, 9, 7, 5, 3, 1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
