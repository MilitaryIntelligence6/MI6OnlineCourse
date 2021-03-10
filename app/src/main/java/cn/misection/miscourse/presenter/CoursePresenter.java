package cn.misection.miscourse.presenter;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

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
    private FragmentActivity context;

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
        this.view = CourseViewManager.requireInstance(context);
    }

    public View requireView()
    {
        return this.view.view();
    }
}
