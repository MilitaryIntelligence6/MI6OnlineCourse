package cn.misection.miscourse.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ScreenUtil
 * @Description TODO
 * @CreateTime 2021年03月10日 23:48:00
 */
public final class ScreenUtil {
    /**
     * 读取屏幕宽;
     *
     * @param context
     * @return
     */
    public static int screenWidth(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }
}
