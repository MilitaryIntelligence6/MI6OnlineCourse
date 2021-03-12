package cn.misection.miscourse.mvp.view.impl;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.ui.adapter.ExercisesAdapter;
import cn.misection.miscourse.entity.ExerciseBean;
import cn.misection.miscourse.mvp.view.IExerciseView;

import java.util.List;

/**
 * TODO 一个抽象类的view;
 *
 * @author Administrator
 */
public class ExercisesViewManager implements IExerciseView
{
    private View view;

    private ListView listView;

    private final List<ExerciseBean> beanList;

    private final Activity context;

    public ExercisesViewManager(Activity context,
                                List<ExerciseBean> beanList)
    {
        this.context = context;
        this.beanList = beanList;
        init();
    }

    private void init()
    {
        initView();
        initShowData();
    }

    private void initView()
    {
        view = View.inflate(context, R.layout.main_view_exercises, null);
        listView = view.findViewById(R.id.lv_list);
    }

    private void initShowData()
    {
        ExercisesAdapter adapter = new ExercisesAdapter(context);
        adapter.setData(beanList);
        listView.setAdapter(adapter);
    }

    @Override
    public void show()
    {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public View view()
    {
        return view;
    }
}
