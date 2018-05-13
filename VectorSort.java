package Register;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by lanyage on 2018/5/12.
 */

class VectorSort {
    /**
     * bubble排序的优化,一旦发现逆序元素就标记flag为false,flag控制循环是否还需要循环
     * $$$初步改进$$$
     *
     * @param a
     * @param r
     * @return
     */
    static int count = 0;

    public boolean bubbleSort(int[] a, int r) {
        boolean sort = true;
        for (int i = 0; i < r - 1; i++) {
            if (a[i] > a[i + 1]) {
                count++;
                sort = false;
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        return sort;
    }

    /**
     * 这个是冒泡排序的终极修改版本,相对于前者会更好
     * 每一次排序都记住交换到的最后一位
     *
     * @param a
     * @param r
     * @return
     */
    public int bubbleSortII(int[] a, int r) {
        int last = 0;
        for (int i = 0; i < r - 1; i++) {
            if (a[i] > a[i + 1]) {
                count++;
                last = i + 1;
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        return last;
    }

    /**
     * 归并排序 O(nlogn) 回朔的思想
     *
     * @param a 数组
     */
    public void mergeSort(int[] a, int l, int r) {
        if (r - l > 1) {
            int mid = (r + l) >> 1;
            mergeSort(a, l, mid);
            mergeSort(a, mid, r);
            merge(a, l, mid, r);
        }
    }

    /**
     * 回朔的思想
     */
    private void merge(int[] a, int l, int mid, int r) {
        int[] temp = new int[r - l];
        int i = 0;
        for (int j = l; j < mid; j++) {
            temp[i++] = a[j];
        }
        int re = r;
        int tlen = temp.length - 1;
        while (i > 0 && re > mid) {
            if (temp[i - 1] > a[re - 1]) {
                temp[tlen--] = temp[--i];
            } else {
                temp[tlen--] = a[--re];
            }
        }
        while (i > 0) {
            temp[tlen--] = temp[--i];
        }
        while (re > mid) {
            temp[tlen--] = a[--re];
        }
        i = 0;
        for (int j = l; j < r; j++) {
            a[j] = temp[i++];
        }
    }
    /**
     * 选择排序 能够确定比较的次数,每次把最大的元素放到最后面
     * @param a
     * @param r
     * @return
     */
    public int selectMax(int[] a, int r) {
        int max = 0;
        for (int i = 0; i < r; i++) {
            max = a[max] < a[i] ? i : max;
        }
        return max;
    }
    /**
    *插入排序的思想就是从后往前比较,如果比前面的元素要大或者前面的元素是哨兵指针,那么就直接插在节点后面
    */
}
