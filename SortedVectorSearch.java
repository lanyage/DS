package Register;

import java.util.Arrays;

/**
 * Created by lanyage on 2018/5/12.
 */
public class Vector {
    /**
     * 给有序列表去重
     *
     * @param a
     */
    public void deduplicates(int a[]) {
        int len = a.length;
        if (len < 2)
            return;
        int i = 0;
        for (int j = 1; j < len; j++) {
            if (a[j] != a[i]) {
                a[++i] = a[j];
            }
        }
        for (i++; i < len; i++)
            a[i] = 0;
    }

    /**
     * 二分查找
     * 如果查不到元素就返回最大的且不大于target的元素的下标+1,
     * 如果查到的最小的元素都比target要小那么就返回-1,
     * 否则返回最右边的且等于target元素的下标。
     *
     * @param a 数组
     */
    public int binarySearch(int[] a, int l, int r, int target) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (a[mid] == target) {
                return mid;
            }
            if (a[mid] > target) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l == 0 ? -1 : l;
    }

    /**
     * 斐波那契查询  但是没有严格的兑现search()接口的语意约定
     *
     * @param a      数组
     * @param l      左边界
     * @param r      右边界
     * @param target 目标数
     * @return
     */
    public int fibonacciSearch(int[] a, int l, int r, int target) {
        int k = 0;
        int len = a.length;
        while (len > fib(k) - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(a, fib(k) - 1);
        for (int i = r; i < temp.length; i++) {
            temp[i] = a[r - 1];
        }
        for (int i = len; i < fib(k) - 1; ++i)
            temp[i] = a[len - 1];
        while (l < r) {
            int mid = l + fib(k - 1) - 1;
            if (temp[mid] < target) {
                l = mid + 1;
                k = k - 2;
            } else if (temp[mid] > target) {
                r = mid;
                k = k - 1;
            } else {
                return mid < len ? mid : len - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找的优化,只有两个分支,左右选择成本是一样的,但是一定要走完所有的流程,不能在命中的一瞬间就返回结果,但是更加稳定
     * @param a
     * @param l
     * @param r
     * @param target
     * @return
     */
    public int binarySearchPlus(int[] a, int l, int r, int target) {

        while (1 < r - l) {
            int mid = (r + l) >> 1;
            if (a[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return a[l] == target ? l : -1;
    }

    /**
     * 符合约定语义 这个是二分查找的终极版本
     * @param a
     * @param l
     * @param r
     * @param target
     * @return
     */
    public int binarySearchPlusII(int[] a, int l, int r, int target) {
        while (l < r) {
            int mid = (r + l) >> 1;
            if (a[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return --l;
    }
    private int fib(int n) {
        int first = 1; //用来维护第一个值
        int second = 1; //用来维护第二个值
        while (n-- > 1) {
            second += first;
            first = second - first;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 2, 3, 4, 7, 7, 7, 8, 9, 10};
        Vector v = new Vector();
        System.out.println(v.binarySearchPlusII(a, 0, a.length, 10));
    }
}
