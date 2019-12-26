package com.zhliang.springboot.redis.controller;

import com.zhliang.springboot.redis.pojo.Menu;
import com.zhliang.springboot.redis.pojo.MenuNode;
import com.zhliang.springboot.redis.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.controller
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/4 09:36
 * @version：V1.0
 */
@RestController
public class AdminCrontroller {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/addmenu.json")
    public @ResponseBody String add() throws Exception{
        menuService.addMenu(null);
        //模拟改变缓存
        return "{\"success\":true}";
    }

    @RequestMapping("/getmenu.json")
    public @ResponseBody Menu getMenu(Long menuId) throws Exception{
        return menuService.getMenu(menuId);
    }

    @RequestMapping("/tree.json")
    public @ResponseBody
    MenuNode tree() throws Exception{
        return menuService.getMenuTree();
    }


}
