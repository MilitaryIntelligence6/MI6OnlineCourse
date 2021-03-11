package cn.misection.miscourse.presenter.impl;

import android.app.Activity;
import android.view.View;

import cn.misection.miscourse.model.IMineModel;
import cn.misection.miscourse.model.impl.MineModelImpl;
import cn.misection.miscourse.presenter.IMinePresenter;
import cn.misection.miscourse.view.impl.MineViewManager;

/**
 * @author Administrator
 */
public class MinePresenterImpl implements IMinePresenter
{
    private volatile static MinePresenterImpl instance = null;

    private IMineModel model;

    private MineViewManager view;

    private final Activity context;

    private MinePresenterImpl(Activity context)
    {
        this.context = context;
        init();
    }
    public static MinePresenterImpl requireInstance(Activity context)
    {
        if (instance == null)
        {
            synchronized (MinePresenterImpl.class)
            {
                if (instance == null)
                {
                    instance = new MinePresenterImpl(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        this.view = new MineViewManager(this);
        this.model = new MineModelImpl(this);
        showLoginState();
    }

    @Override
    public void showLoginState()
    {
        view.showLoginState(model.loginInfo());
    }

    @Override
    public boolean hasLogin()
    {
        return model.hasLogin();
    }

    @Override
    public void updateLoginState(boolean loginFlag)
    {
        model.updateLoginState(loginFlag);
    }

    @Override
    public Activity context()
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
