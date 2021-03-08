package com.example.miscourse.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.miscourse.R;
import com.example.miscourse.adapter.AdBannerAdapter;
import com.example.miscourse.adapter.CourseAdapter;
import com.example.miscourse.bean.CourseBean;
import com.example.miscourse.tools.AnalysisUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseView
{
    private ListView listView;
    private CourseAdapter adapter;
    private FragmentActivity context;
    private List<List<CourseBean>> cb1;
    private View currentView;
    private LayoutInflater inflater;
    private ViewPager adPager;  // 广告
    private View adBannerLay;  // 广告条容器
    private AdBannerAdapter ada;  // 适配器
    public static final int MSG_AD_SLID = 002;  // 广告自动滑动
    private ViewPagerIndicator vpi;  // 小圆点
    private MHandler handler;  // 事件捕获
    private List<CourseBean> cad1;

    private CourseView(FragmentActivity context)
    {
        initContextAndInflater(context);
    }

    private volatile static CourseView instance = null;

    public static CourseView requireInstance(FragmentActivity context)
    {
        if (instance == null)
        {
            synchronized (CourseView.class)
            {
                if (instance == null)
                {
                    instance = new CourseView(context);
                }
            }
        }
        // 单一职责, 但是代码有点丑;
        if (!instance.context.equals(context))
        {
            instance.initContextAndInflater(context);
        }
        return instance;
    }

    private void initContextAndInflater(FragmentActivity context)
    {
        this.context = context;
        // 为之后将 Layout 转化为 view 时用
        putInflater(context);
    }

    private void putInflater(FragmentActivity context)
    {
        this.inflater = LayoutInflater.from(context);
    }

    private void createView()
    {
        this.handler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }

    // 事件捕获
    private class MHandler extends Handler
    {
        @Override
        public void dispatchMessage(@NonNull Message msg)
        {
            super.dispatchMessage(msg);
            switch (msg.what)
            {
                case MSG_AD_SLID:
                    if (ada.getCount() > 0)
                    {
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }

    // 广告自动滑动
    private class AdAutoSlidThread extends Thread
    {
        @Override
        public void run()
        {
            super.run();
            while (true)
            {
                try
                {
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (handler != null)
                {
                    handler.sendEmptyMessage(MSG_AD_SLID);
                }
            }
        }
    }

    // 初始化控件
    private void initView()
    {
        currentView = inflater.inflate(R.layout.main_view_course, null);
        listView = currentView.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(context);
        adapter.setData(cb1);
        listView.setAdapter(adapter);
        adPager = currentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        ada = new AdBannerAdapter(context.getSupportFragmentManager(), handler);
        adPager.setAdapter(ada);  // 给 ViewPager 设置适配器
        adPager.setOnTouchListener(ada);
        vpi = currentView.findViewById(R.id.vpi_advert_indicator);
        vpi.setCount(ada.getSize());  // 设置小圆点的个数
        adBannerLay = currentView.findViewById(R.id.rl_addBanner);
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                if (ada.getSize() > 0)
                {
                    // 由于 index 数据在滑动时是累加的
                    // 因此用 index % ada.getSize() 来标记滑动到的当前位置
                    vpi.setCurrentPosition(position % ada.getSize());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        resetSize();
        if (cad1 != null)
        {
            if (cad1.size() > 0)
            {
                vpi.setCount(cad1.size());
                vpi.setCurrentPosition(0);
            }
            ada.setDatas(cad1);
        }
    }

    // 计算控件大小
    private void resetSize()
    {
        int sw = getScreenWidth(context);
        int adLheight = sw / 2;
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }

    // 读取屏幕宽
    public static int getScreenWidth(Activity context)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    // 初始化广告中的数据
    private void initAdData()
    {
        cad1 = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            CourseBean bean = new CourseBean();
            bean.setId(i + 1);
            switch (i)
            {
                case 0:
                    bean.setIcon("banner_1");
                    break;
                case 1:
                    bean.setIcon("banner_2");
                    break;
                case 2:
                    bean.setIcon("banner_3");
                    break;
                default:
                    break;
            }
            cad1.add(bean);
        }
    }

    // 获取课程信息
    private void getCourseData()
    {
        try
        {
            InputStream is = context.getResources().getAssets().open("chaptertitle.xml");
            cb1 = AnalysisUtils.getCourseInfos(is);
        }
        catch (IOException | XmlPullParserException e)
        {
            e.printStackTrace();
        }
    }

    // 获取当前在导航栏上方显示对应的 View
    public View getView()
    {
        initViewInstance();
        return currentView;
    }

    // 显示当前导航栏上方所对应的 view 界面
    public void showView()
    {
        initViewInstance();
        currentView.setVisibility(View.VISIBLE);
    }

    private void initViewInstance()
    {
        if (currentView == null)
        {
            synchronized (CourseView.class)
            {
                if (currentView == null)
                {
                    createView();
                }
            }
        }
    }
}
