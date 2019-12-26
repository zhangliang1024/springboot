package com.zhliang.springboot.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.leetcode.string
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/25 20:28
 * @version：V1.0
 */
public class CompareString {

    /**
     * 如何判断两个字符串是否由相同的字符组成:
     *  - https://blog.csdn.net/hd12370/article/details/82380039
     *  - https://blog.csdn.net/lanchunhui/article/details/87013785
     *  - https://blog.csdn.net/u014259820/article/details/78965993
     *
     */


    public static void main(String[] args) {

        StringBuffer s11 = new StringBuffer();
        StringBuffer s22 = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            s11.append("aybcybayaatt");
        }
        for (int i = 0; i < 10000; i++) {
            s22.append("aybcybayaatta");
        }
        String s1 = s11.toString();
        String s2 = s22.toString();
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();

        long l3 = System.currentTimeMillis();
        System.out.println(compare(a, b));
        long l4 = System.currentTimeMillis();
        System.out.println("集合用时：" + (l4 - l3));

        long l1 = System.currentTimeMillis();
        System.out.println(compare2(s1, s2));
        long l2 = System.currentTimeMillis();
        System.out.println("数组用时：" + (l2 - l1));

    }

    public static boolean compare(char[] a, char[] b) {
        int aLen = a.length;
        int bLen = b.length;
        if (aLen != bLen) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < aLen; i++) {
            if (map.containsKey(a[i])) {
                map.put(a[i], map.get(a[i]) + 1);
            } else {
                map.put(a[i], 1);
            }
        }
        for (int j = 0; j < bLen; j++) {
            if (map.containsKey(a[j]) && map.get(a[j]) == 1) {
                map.remove(a[j]);
            } else {
                map.put(a[j], map.get(a[j]) - 1);
            }
        }
        return map.isEmpty();

    }

    public static boolean compare2(String a, String b) {
        byte[] b1 = a.getBytes();
        byte[] b2 = b.getBytes();
        int[] bCount = new int[256];
        for (int i = 0; i < 256; i++) {
            bCount[i] = 0;
        }
        for (int i = 0; i < b1.length; i++)
            bCount[b1[i] - '0']++;
        for (int i = 0; i < b2.length; i++)
            bCount[b2[i] - '0']--;
        for (int i = 0; i < 256; i++) {
            if (bCount[i] != 0)
                return false;
        }
        return true;
    }


}
