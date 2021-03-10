package cn.misection.miscourse.view;

import android.view.View;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName AbstractView
 * @Description TODO
 * @CreateTime 2021年03月10日 19:40:00
 */
public abstract class AbstractView implements IView
{
    protected View view;

    @Override
    public void show()
    {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public View view()
    {
        return view;
    }
}
