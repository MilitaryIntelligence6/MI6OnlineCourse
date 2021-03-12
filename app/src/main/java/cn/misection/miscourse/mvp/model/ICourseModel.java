package cn.misection.miscourse.mvp.model;

import java.util.List;

import cn.misection.miscourse.entity.CourseBean;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ICourseModel
 * @Description TODO
 * @CreateTime 2021年03月11日 15:12:00
 */
public interface ICourseModel extends IModel
{
    /**
     * 获取滑动;
     * @return 滑动;
     */
    List<CourseBean> slideList();

    /**
     * 获取课程;
     * @return 课程;
     */
    List<List<CourseBean>> courseListList();
}
