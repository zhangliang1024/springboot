package com.zhliang.springboot.leetcode.string;

import java.util.Arrays;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.leetcode.string
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/25 11:24
 * @version：V1.0
 */
public class StrCompare {


    /**
     * 1. 判断 2个输入的字符串是否一致
     * 2. 判断 2个输入的字符串【格式】是否一致
     */
    public static void main(String[] args) {
        String str1 = "gdeacbf";
        String str2 = "gdeacbf";
        //compare(str1, str2);
        str1 = "gdeacbf";
        str2 = "hmgdeacbf";
        //str1 = "ABBA京杭杭京";
        //str2 = "AABB京杭杭京";
        compare(str1, str2);
        //boolean b = canPerm(str1, str2);
        System.out.println(isStringEqual(str1,str2));
    }

    /**
     * 排序法
     * @param s1
     * @param s2
     */
    public static void compare(String s1, String s2) {
        char[] b1 = s1.toCharArray();
        char[] b2 = s2.toCharArray();
        //byte[] b1 = s1.getBytes();
        //byte[] b2 = s2.getBytes();
        //Arrays.sort(b1);
        //Arrays.sort(b2);
        s1 = new String(b1);
        s2 = new String(b2);
        if (s1.equals(s2)) {
            System.out.println("equal");
        } else {
            System.out.println(" not equal");
        }
    }

    public static boolean canPerm(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[256];
        for (char c : s.toCharArray()) {
            counter[c]++;
        }
        // 两字符串长度一致的前提下，
        for (char c : t.toCharArray()) {
            if (--counter[c] < 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean isStringEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        //将两个字符串分别转化为字节数组bytes1、bytes2
        byte[] bytes1 = s1.getBytes();
        byte[] bytes2 = s2.getBytes();
        int[] charCount = new int[256];
        for (int i = 0; i < bytes1.length; i++) {
            //对于字符串1出现的字符，数组加1，字符串2出现的字符，数组减1
            charCount[bytes1[i]]++;
            charCount[bytes2[i]]--;
        }
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
