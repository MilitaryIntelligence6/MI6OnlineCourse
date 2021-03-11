package cn.misection.miscourse.presenter;

import android.app.Activity;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName IMinePresenter
 * @Description TODO
 * @CreateTime 2021年03月11日 15:14:00
 */
public interface IMinePresenter extends IPresenter
{
    /**
     * 是否登录;
     * @return
     */
    boolean hasLogin();

    /**
     * 更新login状态;
     * @param loginFlag 是否登录;
     */
    void updateLoginState(boolean loginFlag);

    /**
     * 获得context;
     * @return context;
     */
    Activity context();

    /**
     * 展示是否登录;
     */
    void showLoginState();
}
