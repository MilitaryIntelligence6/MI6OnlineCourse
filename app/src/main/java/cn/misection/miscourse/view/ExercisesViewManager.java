package cn.misection.miscourse.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.adapter.ExercisesAdapter;
import cn.misection.miscourse.bean.ExercisesBean;
import cn.misection.miscourse.constant.EnumExerciseProp;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 一个抽象类的view;
 *
 * @author Administrator
 */
public class ExercisesViewManager extends AbstractView
{
    private ListView listView;

    private ExercisesAdapter adapter;

    private List<ExercisesBean> exercisesBeanList;

    private Activity context;

    private volatile static ExercisesViewManager instance = null;

    public ExercisesViewManager(Activity context)
    {
        this.context = context;
        init();
    }

    public static ExercisesViewManager requireInstance(Activity context)
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
            instance.context = context;
        }
        return instance;
    }

    private void init()
    {
        initView();
    }

    private void initView()
    {
        view = View.inflate(context, R.layout.main_view_exercises, null);
        listView = view.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(context);
        initData();
        adapter.setData(exercisesBeanList);
        listView.setAdapter(adapter);
    }

    private void initData()
    {
        exercisesBeanList = new ArrayList<>();
        int[] backgroundArray = new int[]{
                R.drawable.exercises_bg_1,
                R.drawable.exercises_bg_2,
                R.drawable.exercises_bg_3,
                R.drawable.exercises_bg_4,
        };
        int exerciseCount = EnumExerciseProp.values().length;
        for (int i = 0; i < exerciseCount; i++)
        {
            ExercisesBean bean = new ExercisesBean();
            int id = i + 1;
            int color = i % backgroundArray.length;
            EnumExerciseProp prop = EnumExerciseProp.valueOf(i);
            bean.setId(id);
            bean.setTitle(String.format("第 %d 章 %s", i, prop.getChapterName()));
            bean.setContent(String.format("共计 %d 题", prop.getTopicCount()));
            bean.setBackground(backgroundArray[color]);
            exercisesBeanList.add(bean);
        }
    }
}
