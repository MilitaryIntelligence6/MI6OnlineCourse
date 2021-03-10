package cn.misection.miscourse.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.FragmentActivity;

import cn.misection.miscourse.R;
import cn.misection.miscourse.adapter.ExercisesAdapter;
import cn.misection.miscourse.bean.ExercisesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 一个抽象类的view;
 */
public class ExercisesViewManager
{
    private ListView listView;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> exercisesBeanList;
    private Activity context;
    private LayoutInflater inflater;
    private View currentView;

    private volatile static ExercisesViewManager instance = null;

    public ExercisesViewManager(Activity context)
    {
        initContextAndInflater(context);
    }

    public static ExercisesViewManager requireInstance(FragmentActivity context)
    {
        if (instance == null)
        {
            synchronized (ExercisesViewManager.class)
            {
                if (instance == null)
                {
                    instance = new ExercisesViewManager(context);
                }
            }
        }
        // 单一职责, 但是代码有点丑;
        if (!instance.context.equals(context))
        {
            instance.initContextAndInflater(context);
        }
        return instance;
    }

    private void initContextAndInflater(Activity context)
    {
        this.context = context;
        // 为之后将 Layout 转化为 view 时用
        inflater = LayoutInflater.from(context);
    }

    private void createView()
    {
        initView();
    }

    private void initView()
    {
        currentView = inflater.inflate(R.layout.main_view_exercises, null);
        listView = currentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(context);
        initData();
        adapter.setData(exercisesBeanList);
        listView.setAdapter(adapter);
    }

    private void initData()
    {
        exercisesBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            ExercisesBean bean = new ExercisesBean();
            bean.setId((i + 1));
            switch (i)
            {
                case 0:
                    bean.setTitle("第 1 章 Android 基础入门");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_1));
                    break;
                case 1:
                    bean.setTitle("第 2 章 Android UI 开发");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_2));
                    break;
                case 2:
                    bean.setTitle("第 3 章 Activity");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_3));
                    break;
                case 3:
                    bean.setTitle("第 4 章 数据存储");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_4));
                    break;
                case 4:
                    bean.setTitle("第 5 章 SQLite 数据库");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_1));
                    break;
                case 5:
                    bean.setTitle("第 6 章 广播接收者");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_2));
                    break;
                case 6:
                    bean.setTitle("第 7 章 服务");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_3));
                    break;
                case 7:
                    bean.setTitle("第 8 章 内容提供者");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_4));
                    break;
                case 8:
                    bean.setTitle("第 9 章 网络编程");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_1));
                    break;
                case 9:
                    bean.setTitle("第 10 章 高级编程");
                    bean.setContent("共计 5 题");
                    bean.setBackground((R.drawable.exercises_bg_2));
                    break;
                default:
                    break;
            }
            exercisesBeanList.add(bean);
        }
    }

    // 获取当前在导航栏上方显示对应的 View
    public View getView()
    {
        initViewInstance();
        return currentView;
    }

    // 显示当前导航栏上方所对应的 view 界面
    public void showView()
    {
        initViewInstance();
        currentView.setVisibility(View.VISIBLE);
    }

    private void initViewInstance()
    {
        if (currentView == null)
        {
            synchronized (ExercisesViewManager.class)
            {
                if (currentView == null)
                {
                    createView();
                }
            }
        }
    }
}
