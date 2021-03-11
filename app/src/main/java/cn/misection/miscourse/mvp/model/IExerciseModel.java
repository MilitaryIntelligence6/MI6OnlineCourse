package cn.misection.miscourse.mvp.model;

import java.util.List;

import cn.misection.miscourse.bean.ExercisesBean;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName IModel
 * @Description TODO
 * @CreateTime 2021年03月10日 22:51:00
 */
public interface IExerciseModel
{
    /**
     * 获取数据;
     * @return 获取数据;
     */
    List<ExercisesBean> exerciseList();
}
