package cn.misection.miscourse.model;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName IMineModel
 * @Description TODO
 * @CreateTime 2021年03月11日 15:12:00
 */
public interface IMineModel
{
    /**
     * 登录信息;
     * @return 是否登录;
     */
    boolean hasLogin();

    /**
     * 登录名;
     * @return 名;
     */
    String loginInfo();

    /**
     * 更新;
     * @param loginFlag 是否login;
     */
    void updateLoginState(boolean loginFlag);
}
