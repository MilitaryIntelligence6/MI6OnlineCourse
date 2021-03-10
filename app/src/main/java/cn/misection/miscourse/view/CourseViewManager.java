package cn.misection.miscourse.view;

import android.os.Handler;
import android.os.Message;
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
import cn.misection.miscourse.util.ScreenUtil;

import java.util.List;

public class CourseViewManager extends AbstractView
{
    private ListView listView;

    private CourseAdapter adapter;

    private FragmentActivity context;

    private List<List<CourseBean>> beanListList;


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

    private List<CourseBean> beanList;

    public CourseViewManager(FragmentActivity context,
                             List<CourseBean> beanList,
                             List<List<CourseBean>> beanListList
    )
    {
        this.context = context;
        this.beanList = beanList;
        this.beanListList = beanListList;
        init();
    }

    private void init()
    {
        initView();
    }

    private void initView()
    {
        this.handler = new MHandler();
        initComponent();
        new AutoSlidThread().start();
    }

    /**
     * 事件捕获;
      */
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
    private class AutoSlidThread extends Thread
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

    /**
     * 初始化控件;
     */
    private void initComponent()
    {
        view = View.inflate(context, R.layout.main_view_course, null);
        listView = view.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(context);
        adapter.setData(beanListList);
        listView.setAdapter(adapter);
        adPager = view.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        adBannerAdapter = new AdBannerAdapter(context.getSupportFragmentManager(), handler);
        // 给 ViewPager 设置适配器;
        adPager.setAdapter(adBannerAdapter);
        adPager.setOnTouchListener(adBannerAdapter);
        viewPagerIndicator = view.findViewById(R.id.vpi_advert_indicator);
        // 设置小圆点的个数;
        viewPagerIndicator.setCount(adBannerAdapter.getSize());
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
        if (beanList != null)
        {
            if (beanList.size() > 0)
            {
                viewPagerIndicator.setCount(beanList.size());
                viewPagerIndicator.setCurrentPosition(0);
            }
            adBannerAdapter.setDatas(beanList);
        }
    }

    /**
     * 计算控件大小;
     */
    private void resetSize()
    {
        int screenWidth = ScreenUtil.screenWidth(context);
        int adLheight = screenWidth / 2;
        ViewGroup.LayoutParams slideLayoutParams = adBannerLay.getLayoutParams();
        slideLayoutParams.width = screenWidth;
        slideLayoutParams.height = adLheight;
        adBannerLay.setLayoutParams(slideLayoutParams);
    }
}
