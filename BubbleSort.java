package Register;

/**
 * Created by lanyage on 2018/5/12.
 */

class BubbleSort {
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
}
