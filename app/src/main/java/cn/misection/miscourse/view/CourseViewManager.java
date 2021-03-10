package cn.misection.miscourse.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import cn.misection.miscourse.R;
import cn.misection.miscourse.adapter.AdBannerAdapter;
import cn.misection.miscourse.adapter.CourseAdapter;
import cn.misection.miscourse.bean.CourseBean;
import cn.misection.miscourse.util.AnalysisUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseViewManager extends AbstractView
{
    private ListView listView;

    private CourseAdapter adapter;

    private FragmentActivity context;

    private List<List<CourseBean>> courseBeanListList;

    /**
     * 广告
     */
    private ViewPager adPager;

    /**
     * 广告条容器
     */
    private View adBannerLay;

    /**
     * 适配器
     */
    private AdBannerAdapter adBannerAdapter;

    /**
     * 广告自动滑动
     */
    public static final int MSG_AD_SLID = 002;

    /**
     * 小圆点
     */
    private ViewPagerIndicator viewPagerIndicator;

    /**
     * 事件捕获
     */
    private MHandler handler;

    private List<CourseBean> courseBeanList;

    private volatile static CourseViewManager instance = null;

    private CourseViewManager(FragmentActivity context)
    {
        this.context = context;
        init();
    }

    public static CourseViewManager requireInstance(FragmentActivity context)
    {
        if (instance == null)
        {
            synchronized (CourseViewManager.class)
            {
                if (instance == null)
                {
                    instance = new CourseViewManager(context);
                }
            }
        }
        // 单一职责, 但是代码有点丑;
        if (!instance.context.equals(context))
        {
            instance.context = context;
        }
        return instance;
    }

    private void init()
    {
        initView();
    }

    private void initView()
    {
        this.handler = new MHandler();
        initAdData();
        accessCourseData();
        initComponent();
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
                {
                    if (adBannerAdapter.getCount() > 0)
                    {
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
        }
    }

    /**
     * 广告自动滑动;
     */
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
    private void initComponent()
    {
        view = View.inflate(context, R.layout.main_view_course, null);
        listView = view.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(context);
        adapter.setData(courseBeanListList);
        listView.setAdapter(adapter);
        adPager = view.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        adBannerAdapter = new AdBannerAdapter(context.getSupportFragmentManager(), handler);
        adPager.setAdapter(adBannerAdapter);  // 给 ViewPager 设置适配器
        adPager.setOnTouchListener(adBannerAdapter);
        viewPagerIndicator = view.findViewById(R.id.vpi_advert_indicator);
        viewPagerIndicator.setCount(adBannerAdapter.getSize());  // 设置小圆点的个数
        adBannerLay = view.findViewById(R.id.rl_addBanner);
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position)
            {
                if (adBannerAdapter.getSize() > 0)
                {
                    // 由于 index 数据在滑动时是累加的
                    // 因此用 index % ada.getSize() 来标记滑动到的当前位置
                    viewPagerIndicator.setCurrentPosition(position % adBannerAdapter.getSize());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        resetSize();
        if (courseBeanList != null)
        {
            if (courseBeanList.size() > 0)
            {
                viewPagerIndicator.setCount(courseBeanList.size());
                viewPagerIndicator.setCurrentPosition(0);
            }
            adBannerAdapter.setDatas(courseBeanList);
        }
    }

    /**
     * 计算控件大小;
     */
    private void resetSize()
    {
        int sw = getScreenWidth(context);
        int adLheight = sw / 2;
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }

    /**
     * 读取屏幕宽;
     * @param context
     * @return
     */
    public static int getScreenWidth(Activity context)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 初始化广告中的数据;
     */
    private void initAdData()
    {
        courseBeanList = new ArrayList<>();
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
            courseBeanList.add(bean);
        }
    }

    /**
     * 获取课程信息;
     */
    private void accessCourseData()
    {
        try
        {
            InputStream is = context.getResources().getAssets().open("chaptertitle.xml");
            courseBeanListList = AnalysisUtils.getCourseInfos(is);
        }
        catch (IOException | XmlPullParserException e)
        {
            e.printStackTrace();
        }
    }
}
