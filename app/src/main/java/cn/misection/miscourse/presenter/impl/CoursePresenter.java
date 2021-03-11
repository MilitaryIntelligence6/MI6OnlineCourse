package cn.misection.miscourse.presenter.impl;

import androidx.fragment.app.FragmentActivity;

import cn.misection.miscourse.model.impl.CourseModel;
import cn.misection.miscourse.presenter.AbstractPresenter;
import cn.misection.miscourse.view.CourseViewManager;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName CoursePresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 18:44:00
 */
public class CoursePresenter extends AbstractPresenter
{
    private final FragmentActivity context;

    private volatile static CoursePresenter instance = null;

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
                courseModel.requireData(),
                courseModel.requireBeanListList()
        );
    }

    public FragmentActivity getContext()
    {
        return context;
    }
}
