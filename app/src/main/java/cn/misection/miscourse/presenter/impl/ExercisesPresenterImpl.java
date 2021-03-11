package cn.misection.miscourse.presenter.impl;

import android.app.Activity;
import android.view.View;

import cn.misection.miscourse.model.IExerciseModel;
import cn.misection.miscourse.model.impl.ExerciseModelImpl;
import cn.misection.miscourse.presenter.IExercisesPresenter;
import cn.misection.miscourse.view.IExerciseView;
import cn.misection.miscourse.view.impl.ExercisesViewManager;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ExercisesPresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 19:09:00
 */
public class ExercisesPresenterImpl implements IExercisesPresenter
{
    private volatile static ExercisesPresenterImpl instance = null;

    private final Activity context;

    private IExerciseModel model;

    private IExerciseView view;

    private ExercisesPresenterImpl(Activity context)
    {
        this.context = context;
        init();
    }

    public static ExercisesPresenterImpl requireInstance(Activity context)
    {
        if (instance == null)
        {
            synchronized (ExercisesPresenterImpl.class)
            {
                if (instance == null)
                {
                    instance = new ExercisesPresenterImpl(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        this.model = new ExerciseModelImpl(this);
        this.view = new ExercisesViewManager(context,
                this.model.exerciseList());
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
    public Activity context()
    {
        return context;
    }
}
