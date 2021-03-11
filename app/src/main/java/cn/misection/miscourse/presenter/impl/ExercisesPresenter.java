package cn.misection.miscourse.presenter.impl;

import android.app.Activity;
import android.view.View;

import cn.misection.miscourse.model.IExerciseModel;
import cn.misection.miscourse.model.impl.ExerciseModel;
import cn.misection.miscourse.presenter.IExercisesPresenter;
import cn.misection.miscourse.view.impl.ExercisesViewManager;
import cn.misection.miscourse.view.IView;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ExercisesPresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 19:09:00
 */
public class ExercisesPresenter implements IExercisesPresenter
{
    private volatile static ExercisesPresenter instance = null;

    private final Activity context;

    private IExerciseModel model;

    private IView view;

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
