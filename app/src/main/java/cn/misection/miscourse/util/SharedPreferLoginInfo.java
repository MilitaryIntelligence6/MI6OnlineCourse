package cn.misection.miscourse.util;

import android.content.Context;
import android.content.SharedPreferences;

import cn.misection.miscourse.constant.global.EnumCommonString;
import cn.misection.miscourse.constant.ui.EnumDefaultValue;
import cn.misection.miscourse.constant.ui.EnumSharedPrefKey;

public class SharedPreferLoginInfo
{
    private static final String FILENAME = "loginInfo";

    private SharedPreferences preferences;

    public SharedPreferLoginInfo(Context context)
    {
        preferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存用户信息;
     * @param username
     * @param password
     */
    public void saveInfo(String username, String password)
    {
        String md5Pwd = MdFiveUtil.md5(password);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(username, md5Pwd);
        editor.commit();
    }

    /**
     * 获取用户密码;
     * @param username
     * @return
     */
    public String getPwd(String username)
    {
        return preferences.getString(username, EnumCommonString.EMPTY.value());
    }

    /**
     * 保存登录状态;
     * @param status
     * @param username
     */
    public void saveLoginStatus(boolean status, String username)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(
                EnumSharedPrefKey.IS_LOGIN.literal(),
                status);
        editor.putString(
                EnumSharedPrefKey.LOGIN_USERNAME.literal(),
                username);
        editor.commit();
    }

    /**
     * 获取登录状态;
     * @return
     */
    public boolean hasLogin()
    {
        return preferences.getBoolean(
                EnumSharedPrefKey.IS_LOGIN.literal(),
                EnumDefaultValue.INSTANCE.boolVal());
    }

    /**
     * 获取登录用户名;
     * @return
     */
    public String getLoginUsername()
    {
        return preferences.getString("loginUsername", "");
    }
}
