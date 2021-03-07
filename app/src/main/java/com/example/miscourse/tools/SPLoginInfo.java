package com.example.miscourse.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class SPLoginInfo {
    private static final String filename = "loginInfo";
    SharedPreferences sp;

    public SPLoginInfo(Context context) {
        sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    // 保存用户信息
    public void saveInfo(String username, String password) {
        String md5Pwd = MD5Utils.md5(password);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(username, md5Pwd);
        editor.commit();
    }

    //获取用户密码
    public String getPwd(String username) {
        return sp.getString(username, "");
    }

    // 保存登录状态
    public void saveLoginStatus(boolean status, String username) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUsername", username);
        editor.commit();
    }

    // 获取登录状态
    public boolean getLoginStatus() {
        return sp.getBoolean("isLogin", false);
    }

    // 获取登录用户名
    public String getLoginUsername() {
        return sp.getString("loginUsername", "");
    }
}
