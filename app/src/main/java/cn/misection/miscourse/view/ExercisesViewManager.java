package cn.misection.miscourse.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.adapter.ExercisesAdapter;
import cn.misection.miscourse.bean.ExercisesBean;

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

    private List<ExercisesBean> beanList;

    private Activity context;

    public ExercisesViewManager(Activity context,
                                List<ExercisesBean> beanList)
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
        adapter = new ExercisesAdapter(context);
        adapter.setData(beanList);
        listView.setAdapter(adapter);
    }
}
