/**
 * @FileName: TwoSum.java
 * @Author: zzc
 * @Date: 2020年09月19日 09:46:41
 * @Version V1.0.0
 */

package s1_4;

import org.apache.commons.lang3.math.NumberUtils;
import utils.BinarySearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TwoSum {
    // P119 1.4.5.1
    private static void sum1(int[] arr) {
        // NlogN
        Arrays.sort(arr);
        int count = 0;
        for (int i : arr) {
            if (BinarySearch.search(arr, -i) != -1) {
                count++;
            }
        }
        // todo 优化
        count = count / 2;
        System.out.println(count);
    }

    private static void sum2(int[] arr) {
        // N
        Arrays.sort(arr);
        int front = 0;
        int tail = arr.length - 1;
        int count = 0;
        while (front <= tail){
            int sum = arr[front] + arr[tail];
            if (sum == 0) {
                count++;
                front++;
                tail--;
            } else if (sum < 0) {
                front++;
            } else {
                tail--;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/s1_4/1Mints.txt"));
        int[] arr = new int[1000000];
        int i = 0;
        while (br.read() != -1) {
            arr[i++] = NumberUtils.toInt(br.readLine().trim());
        }
        sum1(arr);
        sum2(arr);
    }
}
