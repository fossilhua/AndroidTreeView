package com.lsh.androidtreeview;

import org.w3c.dom.Node;

import java.util.List;

/**
 * Created by hua on 2016/10/20.
 */

public class TreeBean<T> {
    private int preNodeId;
    private int nodeId;
    private int level;
    private List<TreeBean<T>> nextNodes;
    private T data;
    private boolean isExpand;//是否展开 默认不展开

    @Override
    public String toString() {
        return "TreeBean{" +
                "preNodeId=" + preNodeId +
                ", nodeId=" + nodeId +
                ", level=" + level +
                ", nextNodes=" + nextNodes +
                ", data=" + data +
                ", isExpand=" + isExpand +
                '}';
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }


    public TreeBean(int preNodeId, int nodeId, T data) {
        this.preNodeId = preNodeId;
        this.nodeId = nodeId;
        this.data = data;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPreNodeId() {
        return preNodeId;
    }

    public void setPreNodeId(int preNodeId) {
        this.preNodeId = preNodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public List<TreeBean<T>> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<TreeBean<T>> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
