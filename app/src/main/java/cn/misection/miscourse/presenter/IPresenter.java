package cn.misection.miscourse.presenter;

import android.view.View;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName IPresenter
 * @Description TODO
 * @CreateTime 2021年03月10日 13:32:00
 */
public interface IPresenter
{
    void showView();

    View requireView();
}
