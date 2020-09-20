/**
 * @FileName: LeetCode_15.java
 * @Author: zzc
 * @Date: 2020年09月19日 11:57:51
 * @Version V1.0.0
 */

package s1_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_15_3sum {
    public List<List<Integer>> threeSum(int[] nums) {
        /**
         * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
         * 注意：答案中不可以包含重复的三元组。
         *
         * 示例：
         *
         * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
         *
         * 满足要求的三元组集合为：
         * [
         *   [-1, 0, 1],
         *   [-1, -1, 2]
         * ]
         */
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        // [-4, -1, -1, 0, 1, 2]
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 外循环去重
            twoSum(nums, i + 1, nums.length - 1, -nums[i], list);
        }
        return list;
    }

    private void twoSum(int[] nums, int start, int end, int target, List<List<Integer>> list) {
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                int finalStart = start;
                int finalEnd = end;
                list.add(new ArrayList<Integer>(){{
                    add(-target);
                    add(nums[finalStart]);
                    add(nums[finalEnd]);
                }});
                while (start < end && nums[start] == nums[start + 1]) {
                    // 内循环去重
                    start++;
                }
                start++;
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                end--;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums2 = new int[]{0, 0, 0, 0, 0, 0};
        System.out.println(new LeetCode_15_3sum().threeSum(nums1));
        System.out.println(new LeetCode_15_3sum().threeSum(nums2));
    }
}
