package cn.misection.miscourse.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferLoginInfo
{
    private static final String FILENAME = "loginInfo";

    private SharedPreferences preferences;

    public SharedPreferLoginInfo(Context context)
    {
        preferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    // 保存用户信息
    public void saveInfo(String username, String password)
    {
        String md5Pwd = MD5Utils.md5(password);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(username, md5Pwd);
        editor.commit();
    }

    //获取用户密码
    public String getPwd(String username)
    {
        return preferences.getString(username, "");
    }

    // 保存登录状态
    public void saveLoginStatus(boolean status, String username)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUsername", username);
        editor.commit();
    }

    // 获取登录状态
    public boolean hasLogin()
    {
        return preferences.getBoolean("isLogin", false);
    }

    // 获取登录用户名
    public String getLoginUsername()
    {
        return preferences.getString("loginUsername", "");
    }
}
