package cn.misection.miscourse.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import cn.misection.miscourse.model.IMineModel;
import cn.misection.miscourse.model.impl.MineModel;
import cn.misection.miscourse.presenter.IMinePresenter;
import cn.misection.miscourse.view.MineViewManager;

/**
 * @author Administrator
 */
public class MinePresenter implements IMinePresenter
{
    private volatile static MinePresenter instance = null;

    private IMineModel model;

    private MineViewManager view;

    private final Activity context;

    private MinePresenter(Activity context)
    {
        this.context = context;
        init();
    }
    public static MinePresenter requireInstance(Activity context)
    {
        if (instance == null)
        {
            synchronized (MinePresenter.class)
            {
                if (instance == null)
                {
                    instance = new MinePresenter(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        this.view = new MineViewManager(this);
        this.model = new MineModel(this);
        putLoginParams();
    }

    public void putLoginParams()
    {
        view.showLoginState(model.loginInfo());
    }

    public Activity getContext()
    {
        return context;
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
