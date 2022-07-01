package com.zhliang.springboot.log4j2.plugin;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.log4j2.plugin
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/3/14 15:56
 * @version：V1.0
 */
public class MapUtils {


    public static boolean isEmpty(Map keyRegMap) {
        return CollectionUtils.isEmpty(keyRegMap);
    }

    public static void main(String[] args) {

        // 交集
        List<String> listA_01 = new ArrayList<String>() {{
            add("A");
            add("B");
        }};
        List<String> listB_01 = new ArrayList<String>() {{
            add("B");
            add("C");
        }};
        listA_01.retainAll(listB_01);
        System.out.println(listA_01); // 结果:[B]
        System.out.println(listB_01); // 结果:[B, C]

        // 差集
        List<String> listA_02 = new ArrayList<String>() {{
            add("A");
            add("B");
        }};
        List<String> listB_02 = new ArrayList<String>() {{
            add("B");
            add("C");
        }};
        listA_02.removeAll(listB_02);
        System.out.println(listA_02); // 结果:[A]
        System.out.println(listB_02); // 结果:[B, C]

        // 并集
        List<String> listA_03 = new ArrayList<String>() {{
            add("A");
            add("B");
        }};
        List<String> listB_03 = new ArrayList<String>() {{
            add("B");
            add("C");
        }};
        listA_03.removeAll(listB_03);
        listA_03.addAll(listB_03);
        System.out.println(listA_03); // 结果:[A, B, C]
        System.out.println(listB_03); // 结果:[B, C]
    }
}
