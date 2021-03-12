package cn.misection.miscourse.mvp.model.impl;

import cn.misection.miscourse.R;
import cn.misection.miscourse.mvp.model.IMineModel;
import cn.misection.miscourse.mvp.presenter.impl.MinePresenterImpl;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineModel
 * @Description TODO
 * @CreateTime 2021年03月11日 00:45:00
 */
public class MineModelImpl implements IMineModel
{
    private final MinePresenterImpl presenter;

    private SharedPreferLoginInfo sharePrefLoginInfo;

    public MineModelImpl(MinePresenterImpl presenter)
    {
        this.presenter = presenter;
        init();
    }

    private void init()
    {
        sharePrefLoginInfo = new SharedPreferLoginInfo(presenter.context());
    }

    @Override
    public boolean hasLogin()
    {
        return sharePrefLoginInfo.hasLogin();
    }

    @Override
    public String loginInfo()
    {
        // 改成资源文件中;
        return hasLogin() ? sharePrefLoginInfo.getLoginUsername()
                : presenter.context().getString(R.string.click_to_login);
    }

    @Override
    public void updateLoginState(boolean loginFlag)
    {
        // FIXME; 但是好像没影响;
    }
}
