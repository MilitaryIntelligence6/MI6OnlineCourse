package cn.misection.miscourse.model.impl;

import java.util.ArrayList;
import java.util.List;

import cn.misection.miscourse.R;
import cn.misection.miscourse.bean.ExercisesBean;
import cn.misection.miscourse.constant.EnumExerciseProp;
import cn.misection.miscourse.model.IExerciseModel;
import cn.misection.miscourse.presenter.impl.ExercisesPresenterImpl;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ExerciseModel
 * @Description TODO
 * @CreateTime 2021年03月10日 22:57:00
 */
public class ExerciseModelImpl implements IExerciseModel
{
    private final ExercisesPresenterImpl presenter;

    private List<ExercisesBean> beanList;

    public ExerciseModelImpl(ExercisesPresenterImpl presenter)
    {
        this.presenter = presenter;
        init();
    }

    private void init()
    {
        initData();
    }

    private void initData()
    {
        beanList = new ArrayList<>();
        int[] backgroundArray = new int[]{
                R.drawable.exercises_bg_1,
                R.drawable.exercises_bg_2,
                R.drawable.exercises_bg_3,
                R.drawable.exercises_bg_4,
        };
        int exerciseCount = EnumExerciseProp.values().length;
        for (int i = 0; i < exerciseCount; i++)
        {
            ExercisesBean bean = new ExercisesBean();
            int id = i + 1;
            int color = i % backgroundArray.length;
            EnumExerciseProp prop = EnumExerciseProp.valueOf(i);
            bean.setId(id);
            bean.setTitle(String.format("第 %d 章 %s", i, prop.getChapterName()));
            bean.setContent(String.format("共计 %d 题", prop.getTopicCount()));
            bean.setBackground(backgroundArray[color]);
            beanList.add(bean);
        }
    }

    @Override
    public List<ExercisesBean> exerciseList()
    {
        return beanList;
    }
}
