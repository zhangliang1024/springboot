package com.zhliang.springboot.utils.test;

import java.util.ArrayList;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.utils.test
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/26 20:23
 * @version：V1.0
 */
public class ArrayListGcTest {


    /**
     * 查看 GC 输出
     * -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Integer> list0 = new ArrayList<>();

        long start0 = System.currentTimeMillis();
        for (int i = 0; i <10000000; i++) {
            list0.add(i);
        }

        System.out.println(System.currentTimeMillis() - start0);

        ArrayList<Integer> list1 = new ArrayList<>();

        long start1 = System.currentTimeMillis();
        for (int i = 10000000; i <20000000; i++) {
            list0.add(i);
        }

        System.out.println(System.currentTimeMillis() - start1);
    }
}
