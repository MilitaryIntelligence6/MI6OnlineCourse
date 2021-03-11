package cn.misection.miscourse.presenter;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ICoursePresenter
 * @Description TODO
 * @CreateTime 2021年03月11日 15:38:00
 */
public interface ICoursePresenter extends IPresenter
{
    /**
     * 获得 context;
     * @return context;
     */
    FragmentActivity context();
}
