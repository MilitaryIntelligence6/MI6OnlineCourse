package cn.misection.miscourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.bean.ExerciseBean;
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
                choiceTextArray[i] = convertView.findViewById(
                        EnumExerciseResource.valueOf(i).getTextView());
                choiceImageArray[i] = convertView.findViewById(
                        EnumExerciseResource.valueOf(i).getImageView());
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
//            viewHolder.getTextViewA().setText(bean.getOptionTextA());
//            viewHolder.getTextViewB().setText(bean.getOptionTextB());
//            viewHolder.getTextViewC().setText(bean.getOptionTextC());
//            viewHolder.getTextViewD().setText(bean.getOptionTextD());
        }

        if (!selectedPosition.contains(String.valueOf(position)))
        {
            viewHolder.getChoiceImageArray()[0].setImageResource(R.drawable.exercises_a);
            viewHolder.getChoiceImageArray()[1].setImageResource(R.drawable.exercises_b);
            viewHolder.getChoiceImageArray()[2].setImageResource(R.drawable.exercises_c);
            viewHolder.getChoiceImageArray()[3].setImageResource(R.drawable.exercises_d);
//            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
//            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
//            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
        }
        else
        {
            AnalysisUtil.setChoiceEnable(false, viewHolder.getChoiceImageArray());
            switch (bean.getUserSelect())
            {
                case CORRECT:
                    // 用户所选项是正确的
//                    bean.getCorrectAnswer();
                    viewHolder.getChoiceImageArray()[0].setImageResource(R.drawable.exercises_a);
                    viewHolder.getChoiceImageArray()[1].setImageResource(R.drawable.exercises_b);
                    viewHolder.getChoiceImageArray()[2].setImageResource(R.drawable.exercises_c);
                    viewHolder.getChoiceImageArray()[3].setImageResource(R.drawable.exercises_d);

                    // 正确选项反查;
                    viewHolder.getChoiceImageArray()[bean.getCorrectAnswer().ordinal()]
                            .setImageResource(R.drawable.exercises_right_icon);
                    break;
                case A_WRONG:
                case B_WRONG:
                case C_WRONG:
                case D_WRONG:
                    viewHolder.getChoiceImageArray()[0].setImageResource(R.drawable.exercises_a);
                    viewHolder.getChoiceImageArray()[1].setImageResource(R.drawable.exercises_b);
                    viewHolder.getChoiceImageArray()[2].setImageResource(R.drawable.exercises_c);
                    viewHolder.getChoiceImageArray()[3].setImageResource(R.drawable.exercises_d);

                    viewHolder.getChoiceImageArray()[bean.getUserSelect().ordinal()]
                            .setImageResource(R.drawable.exercises_error_icon);

                    viewHolder.getChoiceImageArray()[bean.getCorrectAnswer().ordinal()]
                            .setImageResource(R.drawable.exercises_right_icon);
                    break;
//                case 2:
//                    // 用户所选 B 是错误的
//                    viewHolder.getImageViewB().setImageResource(R.drawable.exercises_error_icon);
//                    switch (bean.getCorrectAnswer())
//                    {
//                        case 1:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
//                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
//                            break;
//                        case 3:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_right_icon);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
//                            break;
//                        case 4:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_right_icon);
//                            break;
//                        default:
//                        {
//                            break;
//                        }
//                    }
//                    break;
//                case 3:
//                    // 用户所选 C 是错误的
//                    viewHolder.getImageViewC().setImageResource(R.drawable.exercises_error_icon);
//                    switch (bean.getCorrectAnswer())
//                    {
//                        case 1:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
//                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
//                            break;
//                        case 3:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_right_icon);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
//                            break;
//                        case 4:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_right_icon);
//                            break;
//                        default:
//                        {
//                            break;
//                        }
//                    }
//                    break;
//                case 4:
//                    // 用户所选 D 是错误的
//                    viewHolder.getImageViewD().setImageResource(R.drawable.exercises_error_icon);
//                    switch (bean.getCorrectAnswer())
//                    {
//                        case 1:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
//                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
//                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_c);
//                            break;
//                        case 2:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_right_icon);
//                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
//                            break;
//                        case 3:
//                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
//                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
//                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_right_icon);
//                            break;
//                        default:
//                        {
//                            break;
//                        }
//                    }
//                    break;
                default:
                    break;
            }
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

    class ViewHolder
    {
        private TextView subject;
        private TextView[] choiceTextArray;
        private ImageView[] choiceImageArray;

        /**
         * @deprecated
         */
//        private TextView textViewA;
//        private TextView textViewB;
//        private TextView textViewC;
//        private TextView textViewD;
//        private ImageView imageViewA;
//        private ImageView imageViewB;
//        private ImageView imageViewC;
//        private ImageView imageViewD;
        public TextView getSubject()
        {
            return subject;
        }

        public TextView[] getChoiceTextArray()
        {
            return choiceTextArray;
        }

        public void setChoiceTextArray(TextView[] choiceTextArray)
        {
            this.choiceTextArray = choiceTextArray;
        }

        public ImageView[] getChoiceImageArray()
        {
            return choiceImageArray;
        }

        public void setChoiceImageArray(ImageView[] choiceImageArray)
        {
            this.choiceImageArray = choiceImageArray;
        }

        public void setSubject(TextView subject)
        {
            this.subject = subject;
        }

//        public TextView getTextViewA()
//        {
//            return textViewA;
//        }
//
//        public void setTextViewA(TextView textViewA)
//        {
//            this.textViewA = textViewA;
//        }
//
//        public TextView getTextViewB()
//        {
//            return textViewB;
//        }
//
//        public void setTextViewB(TextView textViewB)
//        {
//            this.textViewB = textViewB;
//        }
//
//        public TextView getTextViewC()
//        {
//            return textViewC;
//        }
//
//        public void setTextViewC(TextView textViewC)
//        {
//            this.textViewC = textViewC;
//        }
//
//        public TextView getTextViewD()
//        {
//            return textViewD;
//        }
//
//        public void setTextViewD(TextView textViewD)
//        {
//            this.textViewD = textViewD;
//        }
//
//        public ImageView getImageViewA()
//        {
//            return imageViewA;
//        }
//
//        public void setImageViewA(ImageView imageViewA)
//        {
//            this.imageViewA = imageViewA;
//        }
//
//        public ImageView getImageViewB()
//        {
//            return imageViewB;
//        }
//
//        public void setImageViewB(ImageView imageViewB)
//        {
//            this.imageViewB = imageViewB;
//        }
//
//        public ImageView getImageViewC()
//        {
//            return imageViewC;
//        }
//
//        public void setImageViewC(ImageView imageViewC)
//        {
//            this.imageViewC = imageViewC;
//        }
//
//        public ImageView getImageViewD()
//        {
//            return imageViewD;
//        }
//
//        public void setImageViewD(ImageView imageViewD)
//        {
//            this.imageViewD = imageViewD;
//        }
    }

    public interface OnSelectListener
    {
        void onSelectA(int position, ImageView... imageViewArray);

        void onSelectB(int position, ImageView... imageViewArray);

        void onSelectC(int position, ImageView... imageViewArray);

        void onSelectD(int position, ImageView... imageViewArray);
    }
}
