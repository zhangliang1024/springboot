package com.zhliang.springboot.redis.service;

import com.zhliang.springboot.redis.pojo.Menu;
import com.zhliang.springboot.redis.pojo.MenuNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/4 09:35
 * @version：V1.0
 */
@Slf4j
@Service
public class MenuService {


    @CacheEvict(cacheNames ={ "menu","menuTree"},allEntries=true)
    public void addMenu(Menu menu) {

    }

    @Cacheable(cacheNames="menu")
    public Menu getMenu(Long id) {
        log.info("call service getMenu " + id);
        // 模拟
        Menu menu = new Menu();
        menu.setCode("test");
        menu.setId(id);
        menu.setName("菜单" + id);
        menu.setParentId(1l);

        return menu;
    }

    @Cacheable("menuTree")
    public MenuNode getMenuTree() {

        log.info("call menu tree ");
        Menu root = new Menu();
        root.setCode("root");
        root.setId(1l);
        root.setName("系统管理");
        root.setParentId(null);

        Menu menu = new Menu();
        menu.setCode("menu");
        menu.setId(1l);
        menu.setName("菜单管理");
        menu.setParentId(1l);

        MenuNode tree = new MenuNode();
        tree.setMenu(root);
        tree.setParent(null);

        MenuNode menuTree = new MenuNode();
        menuTree.setMenu(menu);
        menuTree.setParent(tree);
        tree.getChildren().add(menuTree);

        return tree;

    }

}
