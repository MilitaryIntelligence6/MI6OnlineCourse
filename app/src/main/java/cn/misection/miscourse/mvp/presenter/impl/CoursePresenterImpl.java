package cn.misection.miscourse.mvp.presenter.impl;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

import cn.misection.miscourse.mvp.model.ICourseModel;
import cn.misection.miscourse.mvp.model.impl.CourseModelImpl;
import cn.misection.miscourse.mvp.presenter.ICoursePresenter;
import cn.misection.miscourse.mvp.view.ICourseView;
import cn.misection.miscourse.mvp.view.impl.CourseViewManager;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName CoursePresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 18:44:00
 */
public class CoursePresenterImpl implements ICoursePresenter
{
    private volatile static CoursePresenterImpl instance = null;

    private ICourseModel model;

    private ICourseView view;

    private final FragmentActivity context;

    private CoursePresenterImpl(FragmentActivity context)
    {
        this.context = context;
        init();
    }

    public static CoursePresenterImpl requireInstance(FragmentActivity context)
    {
        if (instance == null)
        {
            synchronized (CoursePresenterImpl.class)
            {
                if (instance == null)
                {
                    instance = new CoursePresenterImpl(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        model = new CourseModelImpl(this);
        view = new CourseViewManager(context,
                model.slideList(),
                model.courseListList()
        );
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

    @Override
    public FragmentActivity context()
    {
        return context;
    }
}
