package cn.misection.miscourse.presenter;

import android.content.Context;
import android.view.View;

import cn.misection.miscourse.view.MineViewManager;

public class MinePresenter implements IPresenter
{
    private Context context;

    private MineViewManager viewManager;

    private volatile static MinePresenter instance = null;

    public static MinePresenter requireInstance(Context context)
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

    private MinePresenter(Context context)
    {
        this.context = context;
        viewManager = MineViewManager.requireInstance(context);
    }

    @Override
    public void showView()
    {
        viewManager.show();
    }

    public View requireView()
    {
        return viewManager.getView();
    }

    public void putLoginParams(boolean loginFlag)
    {
        viewManager.putLoginParams(loginFlag);
    }
}
