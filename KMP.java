package test;

import java.io.*;
import java.util.Arrays;

/**
 * Created by lanyage on 2018/6/4.
 */
public class KMP {
    public static int KMP(String s, String p) {
        int[] next = getNext(p, new int[p.length()]);
        System.out.println(Arrays.toString(next));
        int i = 0;
        int j = 0;
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        System.out.println(i);
        System.out.println(j);
        if (j == p.length())
            return i - j;
        return -1;
    }

    public static int[] getNext(String p, int next[]) {
        /** 将模式串变成数组 */
        char[] parr = p.toCharArray();
        /** 获取数组的长度*/
        int pLen = p.length();
        /** 数组第一位为1*/
        next[0] = -1;

        int k = -1;
        int j = 0;
        /** 循环条件为j<pLen-1 */
        while (j < pLen - 1) {
            /** p[k]表示前缀，p[j]表示后缀 */
            if (k == -1 || parr[j] == parr[k]) {
                ++k;
                ++j;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String s = "BBC ABCDAB ABCDABCDABDE";
//        String p = "ABCDABD";
//        int res = KMP(s, p);
//        System.out.println(res);
//    }
}

