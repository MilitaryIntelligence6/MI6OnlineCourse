package cn.misection.miscourse.mvp.model.impl;

import java.util.ArrayList;
import java.util.List;

import cn.misection.miscourse.R;
import cn.misection.miscourse.bean.ExerciseBean;
import cn.misection.miscourse.mvp.constant.EnumExerciseProp;
import cn.misection.miscourse.mvp.model.IExerciseModel;
import cn.misection.miscourse.mvp.presenter.impl.ExercisesPresenterImpl;

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

    private List<ExerciseBean> exerciseList;

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
        exerciseList = new ArrayList<>();
        int[] backgroundArray = new int[]{
                R.drawable.exercises_bg_1,
                R.drawable.exercises_bg_2,
                R.drawable.exercises_bg_3,
                R.drawable.exercises_bg_4,
        };
        int exerciseCount = EnumExerciseProp.values().length;
        for (int i = 0; i < exerciseCount; i++)
        {
            ExerciseBean bean = new ExerciseBean();
            int id = i + 1;
            int color = i % backgroundArray.length;
            EnumExerciseProp prop = EnumExerciseProp.valueOf(i);
            bean.setId(id);
            bean.setTitle(String.format("第 %d 章 %s", i, prop.getChapterName()));
            bean.setContent(String.format("共计 %d 题", prop.getTopicCount()));
            bean.setBackground(backgroundArray[color]);
            exerciseList.add(bean);
        }
    }

    @Override
    public List<ExerciseBean> exerciseList()
    {
        return exerciseList;
    }
}
