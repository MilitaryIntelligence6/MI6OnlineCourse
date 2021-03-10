package cn.misection.miscourse.presenter;

import android.content.Context;

import cn.misection.miscourse.view.MineViewManager;

public class MinePresenter extends AbstractPresenter
{
    private Context context;

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
        this.view = new MineViewManager(context);
    }

    public void putLoginParams(boolean loginFlag)
    {
        ((MineViewManager) view).putLoginParams(loginFlag);
    }
}
