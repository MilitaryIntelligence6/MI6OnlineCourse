package cn.misection.miscourse.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

import cn.misection.miscourse.ui.activity.RegisterActivity;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ToastUtil
 * @Description TODO
 * @CreateTime 2021年03月16日 20:28:00
 */
public final class ToastUtil
{
    private ToastUtil() {}

    public static void show(Context parent, String message)
    {
        Toast.makeText(
                parent,
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    public static void show(Context parent, @StringRes int resId)
    {
        Toast.makeText(
                parent,
                resId,
                Toast.LENGTH_SHORT)
                .show();
    }
}
