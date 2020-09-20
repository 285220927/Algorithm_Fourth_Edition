/**
 * @FileName: ThreeSum.java
 * @Author: zzc
 * @Date: 2020年09月19日 10:34:21
 * @Version V1.0.0
 */

package s1_4;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ThreeSum {
    private static void sum(int[] arr) {
        // N^2
        // a + b = -c
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i);
            count += twoSum(arr, i + 1, arr.length - 1, -arr[i]);
        }
        System.out.println(count);
    }

    private static int twoSum(int[] arr, int start, int end, int target) {
        int count = 0;
        while (start < end) {
            if (arr[start] + arr[end] == target) { // 防止内存溢出可以改成减法
                count++;
                start++;
                end--;
            } else if (arr[start] + arr[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        // P133 1.4.15
        BufferedReader br = new BufferedReader(new FileReader("src/s1_4/1Mints.txt"));
        int[] arr = new int[1000000];
        int i = 0;
        while (br.read() != -1) {
            arr[i++] = NumberUtils.toInt(br.readLine().trim());
        }
//        sum(new int[] {-3, -2, 0, 1, 2, 5});

//1094675895
        sum(arr);
    }
}
