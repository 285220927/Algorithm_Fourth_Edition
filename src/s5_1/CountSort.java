/**
 * @FileName: CountSort.java
 * @Author: zzc
 * @Date: 2020年11月16日 21:22:55
 * @Version V1.0.0
 */

package s5_1;

import java.util.Arrays;

public class CountSort {
    // 键索引计数法
    public static void main(String[] args) {
        String[] s = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "10HV845", "4JZY524", "1ICK750", "3CI0720",
                "10HV845", "10HV845", "2RLA629", "2RLA629", "3ATW723"};
        sort(s, 7);
    }

    private static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            // 计算出现频率
            int[] count = new int[R + 1];
            for (String s : a)
                count[s.charAt(d) + 1]++;
            // 将频率转换为索引
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            System.out.println(Arrays.toString(count));
            // 将元素分类(得到各个数字的起始索引)
            for (String s : a) {
                System.out.print(count[s.charAt(d)] + " ");
                aux[count[s.charAt(d)]++] = s;
            }
            System.out.println();
            System.out.println(Arrays.toString(aux));
            System.out.println();
            // 回写
            System.arraycopy(aux, 0, a, 0, N);
        }
    }
}
