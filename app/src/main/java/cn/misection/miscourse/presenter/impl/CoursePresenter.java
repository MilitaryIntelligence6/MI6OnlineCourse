package cn.misection.miscourse.presenter.impl;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

import cn.misection.miscourse.model.ICourseModel;
import cn.misection.miscourse.model.impl.CourseModel;
import cn.misection.miscourse.presenter.ICoursePresenter;
import cn.misection.miscourse.view.impl.CourseViewManager;
import cn.misection.miscourse.view.IView;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName CoursePresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 18:44:00
 */
public class CoursePresenter implements ICoursePresenter
{
    private volatile static CoursePresenter instance = null;

    private ICourseModel model;

    private IView view;

    private final FragmentActivity context;

    private CoursePresenter(FragmentActivity context)
    {
        this.context = context;
        init();
    }

    public static CoursePresenter requireInstance(FragmentActivity context)
    {
        if (instance == null)
        {
            synchronized (CoursePresenter.class)
            {
                if (instance == null)
                {
                    instance = new CoursePresenter(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        this.model = new CourseModel(this);
        CourseModel courseModel = (CourseModel) this.model;
        this.view = new CourseViewManager(context,
                courseModel.getSlideBeanList(),
                courseModel.getBeanListList()
        );
    }

    public FragmentActivity getContext()
    {
        return context;
    }

    @Override
    public void showView()
    {
        view.show();
    }

    @Override
    public View requireView()
    {
        return view.view();
    }
}
