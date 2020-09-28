/**
 * @FileName: SortTest.java
 * @Author: zzc
 * @Date: 2020年09月26日 10:17:16
 * @Version V1.0.0
 */

package s2_1;

import org.apache.commons.lang3.ArrayUtils;
import utils.Timer;

import java.util.Arrays;
import java.util.Random;

@SuppressWarnings({"unchecked", "DuplicatedCode", "rawtypes"})
public class SortTest {
    public static void main(String[] args) {
        int nums = 10000;
        Double[] d;

        System.out.println("===============随机数组测试===============");
        Double[] d1 = getRandomArray(nums);

        d = d1.clone();
        Timer t = new Timer();
        Arrays.sort(d);
        System.out.println("随机数组 " + nums + "次Arrays.sort耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d1.clone();
        t.reset();
        S1_SelectSort.selectSort(d);
        System.out.println("随机数组 " + nums + "次选择排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d1.clone();
        t.reset();
        S2_InsertSort.insertSort2(d);
        System.out.println("随机数组 " + nums + "次插入排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d1.clone();
        t.reset();
        S3_ShellSort.shellSort(d);
        System.out.println("随机数组 " + nums + "次希尔排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d1.clone();
        t.reset();
        S4_MergeSort.mergeSort(d);
        System.out.println("随机数组 " + nums + "次归并排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        System.out.println("===============顺序数组测试===============");
        Double[] d2 = getOrderArray(nums);

        d = d2.clone();
        t.reset();
        Arrays.sort(d);
        System.out.println("顺序数组 " + nums + "次Arrays.sort耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d2.clone();
        t.reset();
        S1_SelectSort.selectSort(d);
        System.out.println("顺序数组 " + nums + "次选择排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d2.clone();
        t.reset();
        S2_InsertSort.insertSort2(d);
        System.out.println("顺序数组 " + nums + "次插入排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d2.clone();
        t.reset();
        S3_ShellSort.shellSort(d);
        System.out.println("顺序数组 " + nums + "次希尔排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d2.clone();
        t.reset();
        S4_MergeSort.mergeSort(d);
        System.out.println("顺序数组 " + nums + "次归并排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        System.out.println("===============逆序数组测试===============");
        Double[] d3 = getReverseArray(nums);

        d = d3.clone();
        t.reset();
        Arrays.sort(d);
        System.out.println("逆序数组 " + nums + "次Arrays.sort耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d3.clone();
        t.reset();
        S1_SelectSort.selectSort(d);
        System.out.println("逆序数组 " + nums + "次选择排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d3.clone();
        t.reset();
        S2_InsertSort.insertSort2(d);
        System.out.println("逆序数组 " + nums + "次插入排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d3.clone();
        t.reset();
        S3_ShellSort.shellSort(d);
        System.out.println("逆序数组 " + nums + "次希尔排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d3.clone();
        t.reset();
        S4_MergeSort.mergeSort(d);
        System.out.println("逆序数组 " + nums + "次归并排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        System.out.println("===============相同数组测试===============");
        Double[] d4 = getReverseArray(nums);

        d = d4.clone();
        t.reset();
        Arrays.sort(d);
        System.out.println("相同数组 " + nums + "次Arrays.sort耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d4.clone();
        t.reset();
        S1_SelectSort.selectSort(d);
        System.out.println("相同数组 " + nums + "次选择排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d4.clone();
        t.reset();
        S2_InsertSort.insertSort2(d);
        System.out.println("相同数组 " + nums + "次插入排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d4.clone();
        t.reset();
        S3_ShellSort.shellSort(d);
        System.out.println("相同数组 " + nums + "次希尔排序耗时" + t.getTime() + "秒");
        ensureSorted(d);

        d = d4.clone();
        t.reset();
        S4_MergeSort.mergeSort(d);
        System.out.println("相同数组 " + nums + "次归并排序耗时" + t.getTime() + "秒");
        ensureSorted(d);
    }

    private static Double[] getRandomArray(int nums) {
        Double[] d = new Double[nums];
        for (int i = 0; i < nums; i++)
            d[i] = new Random().nextDouble();
        return d;
    }

    private static Double[] getOrderArray(int nums) {
        Double[] d = new Double[nums];
        double random = new Random().nextDouble();
        for (int i = 0; i < nums; i++)
            d[i] = i * random;
        return d;
    }

    private static Double[] getReverseArray(int nums) {
        Double[] d = getOrderArray(nums);
        ArrayUtils.reverse(d);
        return d;
    }

    private static Double[] getSameArray(int nums) {
        Double[] d = new Double[nums];
        for (int i = 0; i < nums; i++)
            d[i] = 1.0;
        return d;
    }

    private static void ensureSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0)
                throw new RuntimeException("Array is not ordered!");
        }
    }
}
