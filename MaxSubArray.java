package Register;

import java.util.Arrays;

/**
 * Created by lanyage on 2018/5/18.
 */

/**
 * 通过递归分置来求解数组的最大子数组
 * 思想:
 * 子向量要么存在于左边,要么存在于右边,要么存在于左边接和右边界之间
 * 因此可以把问题分而治之
 */
public class MaxSubArray {
    public int[] findMaxSubArray(int[] a, int l, int r) {
        if (r - l == 1)
            return new int[]{l, r};
        int mid = (l + r) >> 1;
        int[] leftMaxSubArray = findMaxSubArray(a, l, mid);//左边区间的最大子区间
        int[] rightMaxSubArray = findMaxSubArray(a, mid, r);//右边区间的最大子区间

        /** 根据左边界和右边界和mid来确认合并的区间是左还是右*/
        return findMaxCrossArray(a, leftMaxSubArray[0], leftMaxSubArray[1], mid, rightMaxSubArray[0], rightMaxSubArray[1]);
    }

    private int[] findMaxCrossArray(int[] a, int l, int r1, int mid, int l2, int r) {
        int sum = 0;
        int leftMax = Integer.MIN_VALUE;
        int leftMaxIndex = -1;
        for (int i = r1 - 1; i >= l; i--) {
            sum += a[i];
            if (sum > leftMax) {
                leftMax = sum;
                leftMaxIndex = i;
            }
        }
        sum = 0;
        int rightMax = Integer.MIN_VALUE;
        int rightMaxIndex = -1;
        for (int i = l2; i < r; i++) {
            sum += a[i];
            if (sum > rightMax) {
                rightMax = sum;
                rightMaxIndex = i;
            }
        }
        sum = 0;
        for (int i = leftMaxIndex; i < rightMaxIndex + 1; i++) {
            sum += a[i];
        }
        if (leftMax > sum && leftMax > rightMax) {
            return new int[]{l, r1};
        } else if (rightMax > sum && rightMax > leftMax) {
            return new int[]{l2, r};
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
