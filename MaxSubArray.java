package Register;

import java.util.Arrays;

/**
 * Created by lanyage on 2018/5/18.
 */

/**
 * 通过递归分置来求解数组的最大子数组
 * 思想:
 * 子向量要么存在于左边,要么存在于右边,要么存在于从mid开始向两边扩散的区间
 * 因此可以把问题分而治之
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int[] res = maxSub(nums, 0, nums.length);
        int sum = 0;
        for(int i = res[0]; i < res[1]; i++) {
            sum += nums[i];
        }
        return sum;
    }

    private int[] maxSub(int[] a, int l, int r) {
        if (r - l == 1) {
            return new int[]{l, r};
        }

        int mid = (l + r) / 2;
        int[] left = maxSub(a, l, mid);
        int[] right = maxSub(a, mid, r);

        int leftMaxIndex = mid - 1;
        int sum = 0;
        int leftMax = 0;
        for (int i = mid - 1; i >= l; i--) {
            sum += a[i];
            if (sum > leftMax) {
                leftMax = sum;
                leftMaxIndex = i;
            }
        }
        int rightMaxIndex = mid;
        sum = 0;
        int rightMax = 0;
        for (int i = mid; i < r; i++) {
            sum += a[i];
            if (sum > rightMax) {
                rightMax = sum;
                rightMaxIndex = i;
            }
        }
        int leftSum = 0;
        for (int i = left[0]; i < left[1]; i++) {
            leftSum += a[i];
        }
        int rightSum = 0;
        for (int i = right[0]; i < right[1]; i++) {
            rightSum += a[i];
        }
        int crossSum = 0;
        for (int i = leftMaxIndex; i < rightMaxIndex + 1; i++) {
            crossSum += a[i];
        }
        if (leftSum >= rightSum && leftSum >= crossSum) {
            return new int[]{left[0], left[1]};
        } else if (rightSum >= leftSum && rightSum >= crossSum) {
            return new int[]{right[0], right[1]};
        } else {
            return new int[]{leftMaxIndex, rightMaxIndex + 1};
        }
    }

    /**
     * 使用动态规划的方式来寻找向量中的最大和的子向量区间
     *
     * @param a
     * @return
     */
    public int findMaxSubArray(int[] a) {
        int local = a[0];
        int max = a[0];
        int len = a.length;
        for (int i = 1; i < len; i++) {
            local = local + a[i] > a[i] ? local + a[i] : a[i];
            max = Math.max(local, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, -2, 3, -4, -5, -6, 7, 11};
        MaxSubArray demo = new MaxSubArray();

        System.out.println("------------------");
        long start = System.currentTimeMillis();
        int[] res = demo.findMaxSubArray(a, 0, a.length);
        System.out.println(Arrays.toString(res));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("------------------");
        start = System.currentTimeMillis();
        System.out.println(demo.findMaxSubArray(a));
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("------------------");
    }
}
