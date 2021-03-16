package cn.misection.miscourse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.entity.ExerciseBean;
import cn.misection.miscourse.ui.adapter.holder.ExerciseDetailViewHolder;
import cn.misection.miscourse.constant.global.EnumExercise;
import cn.misection.miscourse.ui.adapter.listener.OnSelectListener;
import cn.misection.miscourse.util.AnalysisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ExercisesDetailAdapter extends BaseAdapter
{
    private Context context;

    private List<ExerciseBean> exerciseList;

    private OnSelectListener onSelectListener;

    /**
     * 记录点击的位置;
     */
    private List<String> selectedPosition;

    public ExercisesDetailAdapter(Context context, OnSelectListener onSelectListener)
    {
        this.context = context;
        this.onSelectListener = onSelectListener;
        init();
    }

    private void init()
    {
        selectedPosition = new ArrayList<>();
    }

    public void putExerciseList(List<ExerciseBean> exerciseList)
    {
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return exerciseList == null ? 0 : exerciseList.size();
    }

    @Override
    public ExerciseBean getItem(int position)
    {
        // FIXME null obj;
        return exerciseList == null ? null
                : exerciseList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ExerciseDetailViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ExerciseDetailViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_detail_list_item, null);
            viewHolder.setSubject(convertView.findViewById(R.id.subject_text_view));

            TextView[] choiceTextArray =
                    new TextView[EnumExercise.count()];
            ImageView[] choiceImageArray =
                    new ImageView[EnumExercise.count()];

            for (int i = 0; i < choiceTextArray.length; i++)
            {
                EnumExercise enumExercise = EnumExercise.valueOf(i);

                choiceTextArray[i] = convertView.findViewById(
                        enumExercise.getTextView());

                choiceImageArray[i] = convertView.findViewById(
                        enumExercise.getImageView());

                choiceImageArray[i].setImageResource(
                        enumExercise.getImageResource());
            }
            viewHolder.setChoiceImageArray(choiceImageArray);
            viewHolder.setChoiceTextArray(choiceTextArray);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((ExerciseDetailViewHolder) convertView.getTag());
        }

        final ExerciseBean exercise = getItem(position);
        if (exercise != null)
        {
            viewHolder.getSubject().setText(exercise.getSubject());
            for (int i = 0; i < EnumExercise.count(); i++)
            {
                viewHolder.getChoiceTextArray()[i].setText(exercise.getOptionTextArray()[i]);
            }
        }

        if (selectedPosition.contains(String.valueOf(position)))
        {
            AnalysisUtil.setChoiceEnable(false, viewHolder.getChoiceImageArray());
            // 先把用户选择置为错, 这么快用户发现不了;
            // FIXME 空对象消灭他们;
            if (exercise != null)
            {
                if (exercise.getUserSelect() != null)
                {
                    viewHolder.getChoiceImageArray()[exercise.getUserSelect().ordinal()]
                            .setImageResource(R.drawable.exercise_error_icon);

                    viewHolder.getChoiceImageArray()[exercise.getCorrectAnswer().ordinal()]
                            .setImageResource(R.drawable.exercise_right_icon);

                }
            }
        }
        // 点击选项事件;
        ImageView[] choiceImageArray = viewHolder.getChoiceImageArray();
        for (int i = 0; i < choiceImageArray.length; i++)
        {
            int finalI = i;
            choiceImageArray[i].setOnClickListener((View v) ->
                    {
                        // 判断 selectedPosition 中是否包含此时点击的 position
                        if (selectedPosition.contains(String.valueOf(position)))
                        {
                            selectedPosition.remove(String.valueOf(position));
                        }
                        else
                        {
                            selectedPosition.add(String.valueOf(position));
                        }
                        onSelectListener.onSelect(position,
                                EnumExercise.valueOf(finalI),
                                viewHolder.getChoiceImageArray());
                    }
            );
        }
        return convertView;
    }
}
