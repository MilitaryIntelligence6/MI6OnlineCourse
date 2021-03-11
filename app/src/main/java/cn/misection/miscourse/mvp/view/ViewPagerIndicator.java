package cn.misection.miscourse.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import cn.misection.miscourse.R;

public class ViewPagerIndicator extends LinearLayout
{
    /**
     * 小圆点的个数;
     */
    private int count;

    /**
     * 当前小圆点的位置;
     */
    private int index;

    private Context context;

    public ViewPagerIndicator(Context context)
    {
        super(context);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        setGravity(Gravity.CENTER);  // 设置此布局居中
    }

    // 设置滑动到当前小圆点时其它圆点的位置
    public void setCurrentPosition(int currentIndex)
    {
        index = currentIndex;  // 当前小圆点
        removeAllViews();  // 移除界面上存在的 view
        int pex = 5;
        for (int i = 0; i < count; i++)
        {
            // 创建一个 ImageView 控件来放置小圆点
            ImageView imageView = new ImageView(context);
            if (index == i)
            {  // 滑动到的当前界面
                // 设置小圆点的图片为蓝色图片
                imageView.setImageResource(R.drawable.indicator_on);
            }
            else
            {
                // 设置小圆点的图片为灰色图片
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex, 0, pex, 0);
            addView(imageView);
        }
    }

    // 设置小圆点的数目
    public void setCount(int count)
    {
        this.count = count;
    }
}
