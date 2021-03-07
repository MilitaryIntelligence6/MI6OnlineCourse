package com.example.miscourse.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.miscourse.R;
import com.example.miscourse.adapter.ExercisesAdapter;
import com.example.miscourse.bean.ExercisesBean;

import java.util.ArrayList;
import java.util.List;

public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> eb1;
    private Activity context;
    private LayoutInflater inflater;
    private View currentView;

    public ExercisesView(Activity context){
        this.context = context;
        // 为之后将 Layout 转化为 view 时用
        inflater = LayoutInflater.from(context);
    }

    private void createView(){
        initView();
    }

    private void initView() {
        currentView = inflater.inflate(R.layout.main_view_exercises, null);
        lv_list = currentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(context);
        initData();
        adapter.setData(eb1);
        lv_list.setAdapter(adapter);
    }

    private void initData() {
        eb1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExercisesBean bean = new ExercisesBean();
            bean.id = (i + 1);
            switch (i) {
                case 0:
                    bean.title = "第 1 章 Android 基础入门";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title = "第 2 章 Android UI 开发";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title = "第 3 章 Activity";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title = "第 4 章 数据存储";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title = "第 5 章 SQLite 数据库";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title = "第 6 章 广播接收者";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title = "第 7 章 服务";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title = "第 8 章 内容提供者";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title = "第 9 章 网络编程";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title = "第 10 章 高级编程";
                    bean.content = "共计 5 题";
                    bean.background = (R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            eb1.add(bean);
        }
    }

    // 获取当前在导航栏上方显示对应的 View
    public View getView(){
        if (currentView == null) {
            createView();
        }
        return currentView;
    }

    // 显示当前导航栏上方所对应的 view 界面
    public void showView(){
        if (currentView == null) {
            createView();
        }
        currentView.setVisibility(View.VISIBLE);
    }
}
