package com.xinfu.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */

public class MyFviewYesBean {
    public List<DataBean> data;

    public ArrayList<String> getTypes() {
        TreeSet<String> set = new TreeSet<>();
        for (DataBean obj : data) {
            set.add(obj.T_name);
        }
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }
}
