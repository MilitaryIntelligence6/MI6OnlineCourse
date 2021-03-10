package cn.misection.miscourse.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineView
 * @Description 失败的, view不能强转;
 * @CreateTime 2021年03月10日 00:19:00
 */
public class MineView extends View
{
    private volatile static View instance = null;

    private static LayoutInflater inflater;

    public static View requireInstance(Context context)
    {
        if (instance == null)
        {
            synchronized (MineView.class)
            {
                if (instance == null)
                {
                    inflater = LayoutInflater.from(context);
                    instance = inflater.inflate(R.layout.main_view_mine, null);
                }
            }
        }
        return instance;
    }

    public MineView(Context context)
    {
        super(context);
    }
}
