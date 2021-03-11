package cn.misection.miscourse.model.impl;

import java.util.List;

import cn.misection.miscourse.model.ICourseModel;
import cn.misection.miscourse.model.IMineModel;
import cn.misection.miscourse.presenter.impl.MinePresenter;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineModel
 * @Description TODO
 * @CreateTime 2021年03月11日 00:45:00
 */
public class MineModel implements IMineModel
{
    private final MinePresenter presenter;

    private SharedPreferLoginInfo sharePrefLoginInfo;

    public MineModel(MinePresenter presenter)
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
                : "点击登陆";
    }

    @Override
    public void updateLoginState(boolean loginFlag)
    {
        // FIXME; 但是好像没影响;
    }
}
