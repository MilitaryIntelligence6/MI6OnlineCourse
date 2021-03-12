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
import cn.misection.miscourse.entity.ViewHolder;
import cn.misection.miscourse.constant.EnumExerciseResource;
import cn.misection.miscourse.util.AnalysisUtil;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDetailAdapter extends BaseAdapter
{
    private Context context;
    private List<ExerciseBean> exerciseList;
    private OnSelectListener onSelectListener;

    public ExercisesDetailAdapter(Context context, OnSelectListener onSelectListener)
    {
        this.context = context;
        this.onSelectListener = onSelectListener;
    }

    public void setData(List<ExerciseBean> exerciseList)
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
        return exerciseList == null ? null : exerciseList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * 记录点击的位置;
     */
    private List<String> selectedPosition = new ArrayList<>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_detail_list_item, null);
            viewHolder.setSubject((TextView) convertView.findViewById(R.id.tv_subject));

            TextView[] choiceTextArray =
                    new TextView[EnumExerciseResource.count()];
            ImageView[] choiceImageArray =
                    new ImageView[EnumExerciseResource.count()];

            for (int i = 0; i < choiceTextArray.length; i++)
            {
                EnumExerciseResource enumExercise = EnumExerciseResource.valueOf(i);
                choiceTextArray[i] = convertView.findViewById(
                        enumExercise.getTextView());
                choiceImageArray[i] = convertView.findViewById(
                        enumExercise.getImageView());

                choiceImageArray[i].setImageResource(
                        enumExercise.getImageResource());
            }
            viewHolder.setChoiceImageArray(choiceImageArray);
            viewHolder.setChoiceTextArray(choiceTextArray);
            // 这里原来有一坨;

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        final ExerciseBean bean = getItem(position);
        if (bean != null)
        {
            viewHolder.getSubject().setText(bean.getSubject());
            viewHolder.getChoiceTextArray()[0].setText(bean.getOptionTextA());
            viewHolder.getChoiceTextArray()[1].setText(bean.getOptionTextB());
            viewHolder.getChoiceTextArray()[2].setText(bean.getOptionTextC());
            viewHolder.getChoiceTextArray()[3].setText(bean.getOptionTextD());
        }

        if (selectedPosition.contains(String.valueOf(position)))
        {
            AnalysisUtil.setChoiceEnable(false, viewHolder.getChoiceImageArray());
            // 先把用户选择置为错, 这么快用户发现不了;
            viewHolder.getChoiceImageArray()[bean.getUserSelect().ordinal()]
                    .setImageResource(R.drawable.exercises_error_icon);

            viewHolder.getChoiceImageArray()[bean.getCorrectAnswer().ordinal()]
                    .setImageResource(R.drawable.exercises_right_icon);

        }
        // 当用户点击 A 选项的点击事件
        viewHolder.getChoiceImageArray()[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
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
                onSelectListener.onSelectA(position, viewHolder.getChoiceImageArray());
            }
        });
        // 当用户点击 B 选项的点击事件
        viewHolder.getChoiceImageArray()[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
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
                onSelectListener.onSelectB(position, viewHolder.getChoiceImageArray());
            }
        });
        // 当用户点击 C 选项的点击事件
        viewHolder.getChoiceImageArray()[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
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
                onSelectListener.onSelectC(position, viewHolder.getChoiceImageArray());
            }
        });
        // 当用户点击 D 选项的点击事件
        viewHolder.getChoiceImageArray()[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
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
                onSelectListener.onSelectD(position, viewHolder.getChoiceImageArray());
            }
        });
        return convertView;
    }

    public interface OnSelectListener
    {
        void onSelectA(int position, ImageView... imageViewArray);

        void onSelectB(int position, ImageView... imageViewArray);

        void onSelectC(int position, ImageView... imageViewArray);

        void onSelectD(int position, ImageView... imageViewArray);
    }
}
