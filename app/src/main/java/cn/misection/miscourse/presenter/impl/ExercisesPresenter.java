package cn.misection.miscourse.presenter.impl;

import android.app.Activity;

import cn.misection.miscourse.model.impl.ExerciseModel;
import cn.misection.miscourse.presenter.AbstractPresenter;
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
    private final Activity context;

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
        this.model = new ExerciseModel(this);
        this.view = new ExercisesViewManager(context,
                this.model.requireData());
    }
}
