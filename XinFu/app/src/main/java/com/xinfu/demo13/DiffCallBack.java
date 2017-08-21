package com.xinfu.demo13;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/08/21
 *     desc   :
 * </pre>
 */
public class DiffCallBack extends DiffUtil.Callback {
    private List<BeanInfo> newLists, oldLists;

    public DiffCallBack(List<BeanInfo> newLists, List<BeanInfo> oldLists) {
        this.newLists = newLists;
        this.oldLists = oldLists;
    }

    @Override
    public int getOldListSize() {
        return oldLists != null ? oldLists.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newLists != null ? newLists.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newLists.get(newItemPosition).id.equals(oldLists.get(oldItemPosition).id);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newLists.get(newItemPosition).name.equals(oldLists.get(oldItemPosition).name);
    }
}
