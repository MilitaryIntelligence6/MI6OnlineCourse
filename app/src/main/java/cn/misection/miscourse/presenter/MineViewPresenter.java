package cn.misection.miscourse.presenter;

import android.content.Context;
import android.view.View;

import cn.misection.miscourse.view.MineViewManager;

public class MineViewPresenter implements IPresenter
{
    private Context context;

    private MineViewManager manager;

    private volatile static MineViewPresenter instance = null;

    public static MineViewPresenter requireInstance(Context context)
    {
        if (instance == null)
        {
            synchronized (MineViewPresenter.class)
            {
                if (instance == null)
                {
                    instance = new MineViewPresenter(context);
                }
            }
        }
        return instance;
    }

    private MineViewPresenter(Context context)
    {
        this.context = context;
        manager = MineViewManager.requireInstance(context);
    }

    public View requireView()
    {
        return manager.getView();
    }

    public void showView()
    {
        manager.showView();
    }

    public void putLoginParams(boolean loginFlag)
    {
        manager.putLoginParams(loginFlag);
    }
}
