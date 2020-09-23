/**
 * @FileName: UFTest.java
 * @Author: zzc
 * @Date: 2020年09月21日 21:59:55
 * @Version V1.0.0
 */

package s1_5;

import org.apache.commons.lang3.math.NumberUtils;
import utils.Timer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UFTest {
    private static void uf(UnionFind uf, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        Timer timer = new Timer();
        while ((line = br.readLine()) != null) {
            String[] nums = line.split(" ");
            int p = NumberUtils.toInt(nums[0]);
            int q = NumberUtils.toInt(nums[1]);
            uf.union(p, q);
        }
        System.out.println(fileName + "文件耗时" + timer.getTime() + "秒");
        System.out.println(uf.count());
        br.close();
    }

    public static void main(String[] args) throws IOException {
//        uf(new UnionFind1(10), "src/s1_5/tinyUF.txt");
//        uf(new UnionFind1(625), "src/s1_5/mediumUF.txt");
////        uf(new UnionFind1(1000000), "src/s1_5/largeUF.txt");
//
//        System.out.println("=========================================");
//
        uf(new UnionFind2(10), "src/s1_5/tinyUF.txt");
        uf(new UnionFind2(625), "src/s1_5/mediumUF.txt");
        uf(new UnionFind2(1000000), "src/s1_5/largeUF.txt");
    }
}
