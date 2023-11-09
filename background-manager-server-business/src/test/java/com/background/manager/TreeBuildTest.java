package com.background.manager;

import cn.hutool.core.lang.tree.Tree;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.model.BackgroundMenu;
import com.background.manager.util.TreeBuildUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TreeBuildTest {

    @Test
    public void test1(){
        List<BackgroundMenu>  backgroundMenuLists=new ArrayList<>();
        BackgroundMenu backgroundMenu=new BackgroundMenu();
        backgroundMenu.setId(new Long(1));
        backgroundMenu.setMenuName("父类菜单");
        backgroundMenu.setParentId(new Long(0));
        backgroundMenu.setIcon("1");

        BackgroundMenu backgroundMenu1=new BackgroundMenu();
        backgroundMenu1.setId(new Long(2));
        backgroundMenu1.setMenuName("子类菜单1");
        backgroundMenu1.setParentId(new Long(1));
        backgroundMenu1.setIcon("2");

        BackgroundMenu backgroundMenu2=new BackgroundMenu();
        backgroundMenu2.setId(new Long(3));
        backgroundMenu2.setMenuName("子类菜单2");
        backgroundMenu2.setParentId(new Long(1));
        backgroundMenu2.setIcon("3");

        BackgroundMenu backgroundMenu3=new BackgroundMenu();
        backgroundMenu3.setId(new Long(4));
        backgroundMenu3.setMenuName("子类菜单3");
        backgroundMenu3.setParentId(new Long(1));
        backgroundMenu3.setIcon("4");

        backgroundMenuLists.add(backgroundMenu);
        backgroundMenuLists.add(backgroundMenu1);
        backgroundMenuLists.add(backgroundMenu2);
        backgroundMenuLists.add(backgroundMenu3);

        System.out.println(backgroundMenu3);
            List<Tree<Long>> build = TreeBuildUtils.build(backgroundMenuLists, (list, tree) -> {
                tree.setId(list.getId())
                        .setName(list.getMenuName())
                        .setParentId(list.getParentId())
                        .putExtra("icon", list.getIcon());
                tree.putExtra("perm",list.getPerms());
            });
        System.out.println(JSONObject.toJSON(build));
    }

    @Test
    public void test2(){
        String s = Long.toString(0);
        System.out.println(s);
    }


}
