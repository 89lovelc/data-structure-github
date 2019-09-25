package com.lovelc;

/**
 * KMP 看毛片？
 */
public class KMP {


    /**
     * 得到next 数组
     *
     * @param string
     * @return
     */
    public static int[] getNext(String string) {
        int[] next = new int[string.length()];

        //j 进行遍历string
        int j = 0;
        //k 进行记录匹配个数
        int k = -1;
        next[0] = -1;

        char[] chars = string.toCharArray();

        while (j < chars.length - 1) {

            if (k == -1 || chars[j] == chars[k]) {
                j++;
                k++;
                next[j] = k;
            } else {
                // 不能进行匹配 就进行回退到 子串进行匹配
                k = next[k];
            }
        }
        return next;
    }


    /**
     * 进行匹配
     * @param s 被匹配串
     * @param find 匹配串
     * @return -1 匹配失败  其他为 匹配开始的index
     */
    public static int KMPIndex(String s, String find) {
        int[] next = getNext(find);
        int i = 0;
        int j = 0;

        while (i < s.length() && j < find.length()) {
            if ( j == -1 || s.charAt(i) == find.charAt(j) ) {
                i++;
                j++;
            } else {
                // 不能进行匹配 子串右滑
                j = next[j];
            }

        }

        // 匹配成功
        if (j == find.length()) {
            return i - find.length();
        }

        return -1;

    }


    public static void main(String[] args) {

        System.out.println(KMPIndex("adcaddaadcade","adcade"));

        System.out.println(KMPIndex("djkalsdjkld","ssdd"));
    }
}
