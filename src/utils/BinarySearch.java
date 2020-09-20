/**
 * @FileName: BinarySearch.java
 * @Author: zzc
 * @Date: 2020年09月19日 09:59:35
 * @Version V1.0.0
 */

package utils;

import java.util.Arrays;

public class BinarySearch {
    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) return -1;

        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int searchMinIndex(int[] array, int target) {
        if (array == null || array.length == 0) return -1;

        Arrays.sort(array);
        int index = -1;
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                index = mid;
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    public static int count(int[] arr, int target) {
        // 最大索引 - 最小索引 + 1
        if (arr == null || arr.length == 0) return 0;

        Arrays.sort(arr);
        int minIndex = -1;
        int maxIndex = -1;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                right = mid - 1;
                minIndex = mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        left = 0;
        right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                left = mid + 1;
                maxIndex = mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (minIndex != -1 && maxIndex != -1) {
            return maxIndex - minIndex + 1;
        }
        return 0;
    }

}
