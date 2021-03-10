package cn.misection.miscourse.presenter;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import cn.misection.miscourse.view.ExercisesViewManager;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ExercisesPresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 19:09:00
 */
public class ExercisesPresenter extends AbstractPresenter
{
    private Activity context;

    private volatile static ExercisesPresenter instance = null;

    private ExercisesPresenter(Activity context)
    {
        this.context = context;
        init();
    }

    public static ExercisesPresenter requireInstance(Activity context)
    {
        if (instance == null)
        {
            synchronized (ExercisesPresenter.class)
            {
                if (instance == null)
                {
                    instance = new ExercisesPresenter(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        this.view = ExercisesViewManager.requireInstance(context);
    }
}
