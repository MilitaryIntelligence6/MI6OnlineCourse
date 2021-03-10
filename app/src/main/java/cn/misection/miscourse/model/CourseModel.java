package cn.misection.miscourse.model;

import android.content.Context;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.misection.miscourse.bean.CourseBean;
import cn.misection.miscourse.util.AnalysisUtil;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName CourseModel
 * @Description TODO
 * @CreateTime 2021年03月11日 00:05:00
 */
public class CourseModel implements IModel
{
    private static final int AD_COUNT = 3;

    private List<CourseBean> slideBeanList;

    private List<List<CourseBean>> beanListList;

    public CourseModel()
    {
        init();
    }

    private void init()
    {
        initData();
    }

    private void initData()
    {
        initSlide();
    }

    /**
     * 初始化滑动条数据;
     */
    private void initSlide()
    {
        slideBeanList = new ArrayList<>();
        for (int i = 0; i < AD_COUNT; i++)
        {
            CourseBean bean = new CourseBean();
            int id = i + 1;
            bean.setId(id);
            bean.setIcon(String.format("banner_%d", id));
            slideBeanList.add(bean);
        }
    }

    /**
     * 初始化课程数据;
     * @param context c;
     * @return list;
     */
    public List<List<CourseBean>> requireBeanListList(Context context)
    {
        applyCourseListList(context);
        return beanListList;
    }

    @Override
    public List requireData()
    {
        return slideBeanList;
    }

    private void applyCourseListList(Context context)
    {
        try
        {
            InputStream is = context.getResources().getAssets().open("chaptertitle.xml");
            beanListList = AnalysisUtil.requireCourseInfo(is);
        }
        catch (IOException | XmlPullParserException e)
        {
            e.printStackTrace();
        }
    }
}
