package cn.misection.miscourse.mvp.view;

import android.view.View;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName IMineView
 * @Description TODO
 * @CreateTime 2021年03月10日 13:23:00
 */
public interface IView
{
    /**
     * 展示窗口;
     */
    void show();

    /**
     * 获得 view;
     * @return view;
     */
    View view();
}
