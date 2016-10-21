package com.lsh.androidtreeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsh.lshrecyclerviewadapter.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TreeAdapter mAdapter;
    TreeHelper<String> treeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new MItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();
        recyclerView.setAdapter(mAdapter = new TreeAdapter(this, showList));
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                TreeBean<String> treeBean = showList.get(pos);
                Log.d(TreeConstant.TAG, treeBean.toString());
                if (treeBean.getNextNodes() == null) {
                    //进入详情页
                } else {
                    if (treeBean.isExpand()) {
                        //收起
                        treeBean.setExpand(false);
                        showList.set(pos, treeBean);
                        showList = treeHelper.delete(treeBean, showList);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        treeBean.setExpand(true);
                        showList.set(pos, treeBean);
                        List<TreeBean<String>> child = treeBean.getNextNodes();
                        int i = 0;
                        for (TreeBean<String> treeBean1 : child) {
                            for (TreeBean<String> treeBean2 : newList) {
                                Log.d(TreeConstant.TAG, "=======showList:::::=" + new Gson().toJson(showList, new TypeToken<List<TreeBean<String>>>() {
                                }.getType()));
                                if (treeBean1.getNodeId() == treeBean2.getNodeId()) {
                                    i++;
                                    Log.d(TreeConstant.TAG, "========treeBean2::::" + new Gson().toJson(treeBean2, new TypeToken<TreeBean<String>>() {
                                    }.getType()));
                                    showList.add(pos + i, treeBean2);
                                    break;
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }
                Log.d(TreeConstant.TAG, "========" + new Gson().toJson(showList, new TypeToken<List<TreeBean<String>>>() {
                }.getType()));
            }
        });
    }

    List<DataBean> list = new ArrayList<>();
    List<TreeBean<String>> treeList = new ArrayList<>();
    List<TreeBean<String>> newList;
    List<TreeBean<String>> showList = new ArrayList<>();

    private void init() {
        for (int i = 0; i < 20; i++) {
            DataBean dataBean = new DataBean();
            dataBean.setTitle("Title" + i);
            dataBean.setDes("Des" + i);
            list.add(dataBean);
        }
        //
        treeList.add(new TreeBean<>(TreeConstant.HEAD_INDEX, 1, "顶级节点1"));
        treeList.add(new TreeBean<>(TreeConstant.HEAD_INDEX, 99, "顶级节点2"));
        treeList.add(new TreeBean<>(TreeConstant.HEAD_INDEX, 999, "顶级节点3"));
        treeList.add(new TreeBean<>(99, 22, "1级节点3"));
        treeList.add(new TreeBean<>(1, 2, "1级节点1"));
        treeList.add(new TreeBean<>(1, 3, "1级节点2"));
        treeList.add(new TreeBean<>(2, 4, "2级节点1"));
        treeList.add(new TreeBean<>(2, 5, "2级节点2"));
        //获取子节点集合
        treeHelper = new TreeHelper<>(treeList);
        newList = treeHelper.sortNewList();
        Log.d(TreeConstant.TAG, "========newList::::::::" + new Gson().toJson(newList, new TypeToken<List<TreeBean<String>>>() {
        }.getType()));
        showList = treeHelper.getTreeHeadBeanList();
    }


}
