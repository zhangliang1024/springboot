package com.zhliang.springboot.cache;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/8/23 15:51
 * @Description:
 * @Version: V1.0
 */
@Repository
public class CustomRepository {


    private static Map<Integer,Custom> customMap = new HashMap<>();


    static{
        Custom custom = new Custom();
        custom.setId(1);
        custom.setName("sansan");
        custom.setEmail("sansan126.com");
        customMap.put(1, custom);

        Custom custom1 = new Custom();
        custom1.setId(2);
        custom1.setName("sansan2");
        custom1.setEmail("sansan2126.com");
        customMap.put(2, custom1);

        Custom custom2 = new Custom();
        custom2.setId(3);
        custom2.setName("sansan3");
        custom2.setEmail("sansan3126.com");
        customMap.put(3, custom2);

    }

    public Custom findById(Integer id){
        return customMap.get(id);
    }

    public void delete(Custom custom){
        Integer id = custom.getId();
        customMap.remove(id);
    }
}
