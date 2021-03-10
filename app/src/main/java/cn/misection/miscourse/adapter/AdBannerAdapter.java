package cn.misection.miscourse.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import cn.misection.miscourse.bean.CourseBean;
import cn.misection.miscourse.fragment.AdBannerFragment;
import cn.misection.miscourse.view.CourseViewManager;

import java.util.ArrayList;
import java.util.List;

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener
{
    private Handler handler;
    private List<CourseBean> courseBeanList;

    public AdBannerAdapter(@NonNull FragmentManager fragmentManager)
    {
        super(fragmentManager);
        courseBeanList = new ArrayList<>();
    }

    public AdBannerAdapter(@NonNull FragmentManager fragmentManager, Handler handler)
    {
        super(fragmentManager);
        this.handler = handler;
        courseBeanList = new ArrayList<>();
    }

    /**
     * 设置数据更新界面;
     *
     * @param courseBeanList courses;
     */
    public void setDatas(List<CourseBean> courseBeanList)
    {
        this.courseBeanList = courseBeanList;
        notifyDataSetChanged();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        handler.removeMessages(CourseViewManager.MSG_AD_SLID);
        return false;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Bundle args = new Bundle();
        if (courseBeanList.size() > 0)
        {
            args.putString("ad", courseBeanList.get(position % courseBeanList.size()).getIcon());
        }
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    /**
     * 返回数据集的真实容量大小;
     * @return size;
     */
    public int getSize()
    {
        return courseBeanList == null ? 0 : courseBeanList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object)
    {
        // 防止刷新结果显示列表时出现缓存数据，重载这个函数，使之默认返回 POSITION_NONE
        return POSITION_NONE;
    }
}
