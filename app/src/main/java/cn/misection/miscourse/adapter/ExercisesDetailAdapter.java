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
    private ArrayList<String> selectedPosition = new ArrayList<>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder vh;
        if (convertView == null)
        {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_detail_list_item, null);
            vh.setSubject((TextView) convertView.findViewById(R.id.tv_subject));
            vh.setTv_a((TextView) convertView.findViewById(R.id.tv_a));
            vh.setTv_b((TextView) convertView.findViewById(R.id.tv_b));
            vh.setTv_c((TextView) convertView.findViewById(R.id.tv_c));
            vh.setTv_d((TextView) convertView.findViewById(R.id.tv_d));
            vh.setIv_a((ImageView) convertView.findViewById(R.id.iv_a));
            vh.setIv_b((ImageView) convertView.findViewById(R.id.iv_b));
            vh.setIv_c((ImageView) convertView.findViewById(R.id.iv_c));
            vh.setIv_d((ImageView) convertView.findViewById(R.id.iv_d));
            convertView.setTag(vh);
        }
        else
        {
            vh = ((ViewHolder) convertView.getTag());
        }

        final ExerciseBean bean = getItem(position);
        if (bean != null)
        {
            vh.getSubject().setText(bean.getSubject());
            vh.getTv_a().setText(bean.getOptionTextA());
            vh.getTv_b().setText(bean.getOptionTextB());
            vh.getTv_c().setText(bean.getOptionTextC());
            vh.getTv_d().setText(bean.getOptionTextD());
        }

        if (!selectedPosition.contains("" + position))
        {
            vh.getIv_a().setImageResource(R.drawable.exercises_a);
            vh.getIv_b().setImageResource(R.drawable.exercises_b);
            vh.getIv_c().setImageResource(R.drawable.exercises_c);
            vh.getIv_d().setImageResource(R.drawable.exercises_d);
        }
        else
        {
            AnalysisUtil.setABCDEnable(false, vh.getIv_a(), vh.getIv_b(), vh.getIv_c(), vh.getIv_d());
            switch (bean.getSelect())
            {
                case 0:
                    // 用户所选项是正确的
                    if (bean.getAnswer() == 1)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 2)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 3)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_c().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 4)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    // 用户所选 A 是错误的
                    vh.getIv_a().setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2)
                    {
                        vh.getIv_b().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 3)
                    {
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_c().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 4)
                    {
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    // 用户所选 B 是错误的
                    vh.getIv_b().setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 3)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_c().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 4)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                        vh.getIv_d().setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    // 用户所选 C 是错误的
                    vh.getIv_c().setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 3)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_d().setImageResource(R.drawable.exercises_d);
                    }
                    else if (bean.getAnswer() == 4)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_d().setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    // 用户所选 D 是错误的
                    vh.getIv_d().setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_d().setImageResource(R.drawable.exercises_c);
                    }
                    else if (bean.getAnswer() == 2)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_right_icon);
                        vh.getIv_c().setImageResource(R.drawable.exercises_c);
                    }
                    else if (bean.getAnswer() == 3)
                    {
                        vh.getIv_a().setImageResource(R.drawable.exercises_a);
                        vh.getIv_b().setImageResource(R.drawable.exercises_b);
                        vh.getIv_c().setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                default:
                    break;
            }
        }
        // 当用户点击 A 选项的点击事件
        vh.getIv_a().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position))
                {
                    selectedPosition.remove("" + position);
                }
                else
                {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectA(position, vh.getIv_a(), vh.getIv_b(), vh.getIv_c(), vh.getIv_d());
            }
        });
        // 当用户点击 B 选项的点击事件
        vh.getIv_b().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position))
                {
                    selectedPosition.remove("" + position);
                }
                else
                {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectB(position, vh.getIv_a(), vh.getIv_b(), vh.getIv_c(), vh.getIv_d());
            }
        });
        // 当用户点击 C 选项的点击事件
        vh.getIv_c().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position))
                {
                    selectedPosition.remove("" + position);
                }
                else
                {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectC(position, vh.getIv_a(), vh.getIv_b(), vh.getIv_c(), vh.getIv_d());
            }
        });
        // 当用户点击 D 选项的点击事件
        vh.getIv_d().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position))
                {
                    selectedPosition.remove("" + position);
                }
                else
                {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectD(position, vh.getIv_a(), vh.getIv_b(), vh.getIv_c(), vh.getIv_d());
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        private TextView subject;
        private TextView tv_a;
        private TextView tv_b;
        private TextView tv_c;
        private TextView tv_d;
        private ImageView iv_a;
        private ImageView iv_b;
        private ImageView iv_c;
        private ImageView iv_d;

        public TextView getSubject()
        {
            return subject;
        }

        public void setSubject(TextView subject)
        {
            this.subject = subject;
        }

        public TextView getTv_a()
        {
            return tv_a;
        }

        public void setTv_a(TextView tv_a)
        {
            this.tv_a = tv_a;
        }

        public TextView getTv_b()
        {
            return tv_b;
        }

        public void setTv_b(TextView tv_b)
        {
            this.tv_b = tv_b;
        }

        public TextView getTv_c()
        {
            return tv_c;
        }

        public void setTv_c(TextView tv_c)
        {
            this.tv_c = tv_c;
        }

        public TextView getTv_d()
        {
            return tv_d;
        }

        public void setTv_d(TextView tv_d)
        {
            this.tv_d = tv_d;
        }

        public ImageView getIv_a()
        {
            return iv_a;
        }

        public void setIv_a(ImageView iv_a)
        {
            this.iv_a = iv_a;
        }

        public ImageView getIv_b()
        {
            return iv_b;
        }

        public void setIv_b(ImageView iv_b)
        {
            this.iv_b = iv_b;
        }

        public ImageView getIv_c()
        {
            return iv_c;
        }

        public void setIv_c(ImageView iv_c)
        {
            this.iv_c = iv_c;
        }

        public ImageView getIv_d()
        {
            return iv_d;
        }

        public void setIv_d(ImageView iv_d)
        {
            this.iv_d = iv_d;
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
