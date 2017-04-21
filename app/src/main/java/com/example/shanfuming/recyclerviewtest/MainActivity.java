package com.example.shanfuming.recyclerviewtest;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanfuming on 2017/4/20.
 */

public class MainActivity extends ActionBarActivity {

    private ArrayList<String> mData = new ArrayList<>();

    @BindView(R.id.recycleView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mrl_refreshLayout)
    MaterialRefreshLayout materialRefreshLayout;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    protected void initData() {

        for (int i = 0; i < 70; i++) {
            mData.add("展示-" + i);
        }

        myAdapter = new MyAdapter(MainActivity.this, mData);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //这个是默认设置的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));

//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        /**
         * 添加分隔线
         */
        //1.LinearLayoutManager使用
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //2.GirdLayoutManager 和 StaggeredGridLayoutManager 使用
//        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        //设置Adapter
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick1(int position) {
                Toast.makeText(MainActivity.this, "点击了第" + position + "位置", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this, "长按了第" + position + "位置", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                myAdapter.addData(1);
                break;
            case R.id.action_remove:
                myAdapter.removeData(1);
                break;
        }
        return true;
    }


}
