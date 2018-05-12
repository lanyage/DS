package Register;

import java.util.Arrays;

/**
 * Created by lanyage on 2018/5/12.
 */
public class Recursion {
    /**
     * <strong>减而治之</strong>
     * 使用递归的减而治之来实现数组的反转
     *
     * @param a 数组
     * @param l 左边界
     * @param r 右边界
     */
    public void reverseArray(int[] a, int l, int r) {
        if (l < r) {
            reverseArray(a, l + 1, r - 1);
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
        }
    }

    /**
     * <strong>分而治之</strong>
     * 使用递归的分而治之来实现数组的求和
     *
     * @param a 数组
     * @param l 左边界
     * @param r 右边界
     * @return 和
     */
    public int binarySum(int[] a, int l, int r) {
        if (l < r) {
            int mid = (l + r) >> 1;
            return binarySum(a, l, mid) + binarySum(a, mid + 1, r);
        } else {
            return a[l];
        }
    }

    /**
     * 通过递归的分而治之来找出数组中的最大的两个数
     *
     * @param a 数组
     * @param l 左边界
     * @param r 右边界
     * @return
     */
    public int[] maxTwo(int[] a, int l, int r) {
        if (r - l > 1) {
            int mid = (l + r) >> 1;
            int[] leftArr = maxTwo(a, l, mid);
            int[] rightArr = maxTwo(a, mid + 1, r);
            int x1;
            int x2;
            x1 = a[leftArr[0]] - a[rightArr[0]] > 0 ? leftArr[0] : rightArr[0];
            if (x1 == leftArr[0]) {
                if (leftArr[0] == leftArr[1]) {
                    x2 = rightArr[0];
                } else {
                    x2 = a[leftArr[1]] - rightArr[0] > 0 ? leftArr[1] : rightArr[0];
                }
            } else {
                if (rightArr[0] == rightArr[1]) {
                    x2 = leftArr[0];
                } else {
                    x2 = a[rightArr[1]] - leftArr[0] > 0 ? rightArr[1] : leftArr[0];
                }
            }
            return new int[]{x1, x2};
        } else {
            return a[l] > a[r] ? new int[]{l, r} : new int[]{r, l};
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 10, 9, 8, 7};
        Recursion recursion = new Recursion();
        System.out.println(Arrays.toString(recursion.maxTwo(a, 0, a.length - 1)));
    }
}
