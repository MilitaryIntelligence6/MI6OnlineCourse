package cn.misection.miscourse.presenter;

import android.view.View;

import cn.misection.miscourse.view.IView;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName AbstractPresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 19:10:00
 */
public abstract class AbstractPresenter implements IPresenter
{
    protected IView view;

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
