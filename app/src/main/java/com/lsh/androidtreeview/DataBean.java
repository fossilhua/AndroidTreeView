package com.lsh.androidtreeview;

/**
 * Created by hua on 2016/10/20.
 */

public class DataBean {
    private String title;
    private String des;

    public DataBean() {
    }

    public DataBean(String title, String des) {
        this.title = title;
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
