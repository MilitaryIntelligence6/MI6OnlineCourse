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
            viewHolder.setTextViewA((TextView) convertView.findViewById(R.id.tv_a));
            viewHolder.setTextViewB((TextView) convertView.findViewById(R.id.tv_b));
            viewHolder.setTextViewC((TextView) convertView.findViewById(R.id.tv_c));
            viewHolder.setTextViewD((TextView) convertView.findViewById(R.id.tv_d));
            viewHolder.setImageViewA((ImageView) convertView.findViewById(R.id.iv_a));
            viewHolder.setImageViewB((ImageView) convertView.findViewById(R.id.iv_b));
            viewHolder.setImageViewC((ImageView) convertView.findViewById(R.id.iv_c));
            viewHolder.setImageViewD((ImageView) convertView.findViewById(R.id.iv_d));
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
            viewHolder.getTextViewA().setText(bean.getOptionTextA());
            viewHolder.getTextViewB().setText(bean.getOptionTextB());
            viewHolder.getTextViewC().setText(bean.getOptionTextC());
            viewHolder.getTextViewD().setText(bean.getOptionTextD());
        }

        if (!selectedPosition.contains(String.valueOf(position)))
        {
            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
        }
        else
        {
            AnalysisUtil.setABCDEnable(false, viewHolder.getImageViewA(), viewHolder.getImageViewB(), viewHolder.getImageViewC(), viewHolder.getImageViewD());
            switch (bean.getSelect())
            {
                case 0:
                    // 用户所选项是正确的
                    switch (bean.getAnswer())
                    {
                        case 1:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 2:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 3:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 4:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_right_icon);
                            break;
                        default:
                        {
                            break;
                        }
                    }
                    break;
                case 1:
                    // 用户所选 A 是错误的
                    viewHolder.getImageViewA().setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2)
                    {
                        viewHolder.getImageViewB().setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                        viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 3)
                    {
                        viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                        viewHolder.getImageViewC().setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 4)
                    {
                        viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                        viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                        viewHolder.getImageViewD().setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    // 用户所选 B 是错误的
                    viewHolder.getImageViewB().setImageResource(R.drawable.exercises_error_icon);
                    switch (bean.getAnswer())
                    {
                        case 1:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 3:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 4:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_right_icon);
                            break;
                        default:
                        {
                            break;
                        }
                    }
                    break;
                case 3:
                    // 用户所选 C 是错误的
                    viewHolder.getImageViewC().setImageResource(R.drawable.exercises_error_icon);
                    switch (bean.getAnswer())
                    {
                        case 1:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 3:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_d);
                            break;
                        case 4:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_right_icon);
                            break;
                        default:
                        {
                            break;
                        }
                    }
                    break;
                case 4:
                    // 用户所选 D 是错误的
                    viewHolder.getImageViewD().setImageResource(R.drawable.exercises_error_icon);
                    switch (bean.getAnswer())
                    {
                        case 1:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewD().setImageResource(R.drawable.exercises_c);
                            break;
                        case 2:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_c);
                            break;
                        case 3:
                            viewHolder.getImageViewA().setImageResource(R.drawable.exercises_a);
                            viewHolder.getImageViewB().setImageResource(R.drawable.exercises_b);
                            viewHolder.getImageViewC().setImageResource(R.drawable.exercises_right_icon);
                            break;
                        default:
                        {
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        // 当用户点击 A 选项的点击事件
        viewHolder.getImageViewA().setOnClickListener(new View.OnClickListener()
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
                onSelectListener.onSelectA(position, viewHolder.getImageViewA(), viewHolder.getImageViewB(), viewHolder.getImageViewC(), viewHolder.getImageViewD());
            }
        });
        // 当用户点击 B 选项的点击事件
        viewHolder.getImageViewB().setOnClickListener(new View.OnClickListener()
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
                onSelectListener.onSelectB(position, viewHolder.getImageViewA(), viewHolder.getImageViewB(), viewHolder.getImageViewC(), viewHolder.getImageViewD());
            }
        });
        // 当用户点击 C 选项的点击事件
        viewHolder.getImageViewC().setOnClickListener(new View.OnClickListener()
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
                onSelectListener.onSelectC(position, viewHolder.getImageViewA(), viewHolder.getImageViewB(), viewHolder.getImageViewC(), viewHolder.getImageViewD());
            }
        });
        // 当用户点击 D 选项的点击事件
        viewHolder.getImageViewD().setOnClickListener(new View.OnClickListener()
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
                onSelectListener.onSelectD(position, viewHolder.getImageViewA(), viewHolder.getImageViewB(), viewHolder.getImageViewC(), viewHolder.getImageViewD());
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        private TextView subject;
        private TextView textViewA;
        private TextView textViewB;
        private TextView textViewC;
        private TextView textViewD;
        private ImageView imageViewA;
        private ImageView imageViewB;
        private ImageView imageViewC;
        private ImageView imageViewD;

        public TextView getSubject()
        {
            return subject;
        }

        public void setSubject(TextView subject)
        {
            this.subject = subject;
        }

        public TextView getTextViewA()
        {
            return textViewA;
        }

        public void setTextViewA(TextView textViewA)
        {
            this.textViewA = textViewA;
        }

        public TextView getTextViewB()
        {
            return textViewB;
        }

        public void setTextViewB(TextView textViewB)
        {
            this.textViewB = textViewB;
        }

        public TextView getTextViewC()
        {
            return textViewC;
        }

        public void setTextViewC(TextView textViewC)
        {
            this.textViewC = textViewC;
        }

        public TextView getTextViewD()
        {
            return textViewD;
        }

        public void setTextViewD(TextView textViewD)
        {
            this.textViewD = textViewD;
        }

        public ImageView getImageViewA()
        {
            return imageViewA;
        }

        public void setImageViewA(ImageView imageViewA)
        {
            this.imageViewA = imageViewA;
        }

        public ImageView getImageViewB()
        {
            return imageViewB;
        }

        public void setImageViewB(ImageView imageViewB)
        {
            this.imageViewB = imageViewB;
        }

        public ImageView getImageViewC()
        {
            return imageViewC;
        }

        public void setImageViewC(ImageView imageViewC)
        {
            this.imageViewC = imageViewC;
        }

        public ImageView getImageViewD()
        {
            return imageViewD;
        }

        public void setImageViewD(ImageView imageViewD)
        {
            this.imageViewD = imageViewD;
        }
    }

    public interface OnSelectListener
    {
        void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);

        void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);

        void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);

        void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
    }
}
