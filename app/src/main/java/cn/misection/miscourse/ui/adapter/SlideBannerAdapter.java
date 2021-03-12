package cn.misection.miscourse.ui.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import cn.misection.miscourse.entity.CourseBean;
import cn.misection.miscourse.ui.fragment.SlideBannerFragment;
import cn.misection.miscourse.mvp.view.impl.CourseViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class SlideBannerAdapter extends FragmentStatePagerAdapter
        implements View.OnTouchListener
{
    private Handler handler;
    private List<CourseBean> courseList;

    public SlideBannerAdapter(@NonNull FragmentManager fragmentManager)
    {
        super(fragmentManager);
        courseList = new ArrayList<>();
    }

    public SlideBannerAdapter(@NonNull FragmentManager fragmentManager, Handler handler)
    {
        super(fragmentManager);
        this.handler = handler;
        courseList = new ArrayList<>();
    }

    /**
     * 设置数据更新界面;
     * @param courseList courses;
     */
    public void setDatas(List<CourseBean> courseList)
    {
        this.courseList = courseList;
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
        if (courseList.size() > 0)
        {
            args.putString("ad", courseList.get(position % courseList.size()).getIcon());
        }
        return SlideBannerFragment.newInstance(args);
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
        return courseList == null ? 0 : courseList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object)
    {
        // 防止刷新结果显示列表时出现缓存数据，重载这个函数，使之默认返回 POSITION_NONE
        return POSITION_NONE;
    }
}
