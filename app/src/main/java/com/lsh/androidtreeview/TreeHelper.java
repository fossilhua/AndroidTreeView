package com.lsh.androidtreeview;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua on 2016/10/21.
 */

public class TreeHelper<T> {
    List<TreeBean<T>> treeList;
    List<TreeBean<T>> treeBeanList = new ArrayList<>();
    List<TreeBean<T>> treeHeadBeanList = new ArrayList<>();
    List<TreeBean<T>> newList = new ArrayList<>();

    public TreeHelper(List<TreeBean<T>> treeList) {
        this.treeList = treeList;
    }

    public List<TreeBean<T>> getTreeHeadBeanList() {
        return treeHeadBeanList;
    }

    //删除 节点下的所有节点
    public List<TreeBean<T>> delete(TreeBean<T> treeBean, List<TreeBean<T>> treeList) {
        for (TreeBean<T> bean : treeBean.getNextNodes()) {
            if (bean.getNextNodes() != null) {
                //delete
                treeList = delete(bean, treeList);
            }
            treeList = deleteNode(bean, treeList);
        }

        return treeList;
    }

    //删除单个节点
    public List<TreeBean<T>> deleteNode(TreeBean<T> treeBean, List<TreeBean<T>> treeList) {
        for (TreeBean<T> bean : treeList) {
            if (bean.getNodeId() == treeBean.getNodeId()) {
                treeList.remove(bean);
                break;
            }

        }
        return treeList;
    }

    public List<TreeBean<T>> sortNewList() {
        //获取子节点集合
        Log.d(TreeConstant.TAG, TreeConstant.SPLIT_LINE + "获取子节点集合");

        for (TreeBean<T> bean : treeList) {
            if (bean.getPreNodeId() == -1) {
                treeHeadBeanList.add(bean);
            }
            for (TreeBean<T> tree : treeList) {
                if (bean.getNodeId() == tree.getPreNodeId()) {
                    List<TreeBean<T>> list = bean.getNextNodes();
                    if (list == null) {
                        list = new ArrayList<>();
                        list.add(tree);
                        bean.setNextNodes(list);
                    } else {
                        list.add(tree);
                        bean.setNextNodes(list);
                    }
                }
            }
            Log.d(TreeConstant.TAG, bean.toString());
            treeBeanList.add(bean);
        }
        Log.d(TreeConstant.TAG, treeBeanList.toString());
        //
        Log.d(TreeConstant.TAG, TreeConstant.SPLIT_LINE + "sort");
        sort(treeHeadBeanList, -1);
        return newList;
    }

    private void sort(TreeBean<T> treeBean) {
        sort(treeBean.getNextNodes(), treeBean.getLevel());
    }

    private void sort(List<TreeBean<T>> childList, int level) {
        level = level + 1;
        for (TreeBean<T> child : childList) {
            child.setLevel(level);
            newList.add(child);
            Log.d(TreeConstant.TAG, child.toString());
            if (child.getNextNodes() == null) {
                continue;
            }
            sort(child);
        }
    }

}
