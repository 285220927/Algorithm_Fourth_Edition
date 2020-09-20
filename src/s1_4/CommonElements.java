/**
 * @FileName: CommonElements.java
 * @Author: zzc
 * @Date: 2020年09月20日 09:28:06
 * @Version V1.0.0
 */

package s1_4;

public class CommonElements {
    // P132 1.4.12
    private static void printCommonElements(int[] arr1, int[] arr2) {
        int idx1 =  0;
        int idx2 =  0;
        while (idx1 < arr1.length && idx2 < arr2.length){
            if (arr1[idx1] == arr2[idx2]) {
                System.out.print(arr1[idx1] + " ");
                idx1++;
                idx2++;
            } else if (arr1[idx1] > arr2[idx2]) {
                idx2++;
            } else {
                idx1++;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printCommonElements(new int[] {3, 5, 6, 7, 10}, new int[] {2, 4, 5, 6, 8, 9, 10});
        printCommonElements(new int[] {1}, new int[] {1});
    }
}
