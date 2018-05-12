package Register;

/**
 * Created by lanyage on 2018/5/12.
 */
public class Fibonacci {
    /**
     * 通过动态规划来实现斐波那契数列
     *
     * @param n 阶数
     * @return
     */
    public long fib(int n) {
        long first = 1; //用来维护第一个值
        long second = 1; //用来维护第二个值
        System.out.println(first + "-" + second);
        while (n-- > 2) {
            second += first;
            first = second - first;
            System.out.println(first + "-" + second);
        }
        return second;
    }
}
