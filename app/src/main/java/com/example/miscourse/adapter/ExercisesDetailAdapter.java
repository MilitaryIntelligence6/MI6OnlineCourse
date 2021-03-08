package com.example.miscourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miscourse.R;
import com.example.miscourse.bean.ExercisesBean;
import com.example.miscourse.tools.AnalysisUtils;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context context;
    private List<ExercisesBean> eb1;
    private OnSelectListener onSelectListener;

    public ExercisesDetailAdapter(Context context, OnSelectListener onSelectListener){
        this.context = context;
        this.onSelectListener = onSelectListener;
    }

    public void setData(List<ExercisesBean> eb1){
        this.eb1 = eb1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return eb1 == null ? 0 : eb1.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return eb1 == null ? null : eb1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 记录点击的位置
    private ArrayList<String> selectedPosition = new ArrayList<>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_detail_list_item, null);
            vh.subject = convertView.findViewById(R.id.tv_subject);
            vh.tv_a = convertView.findViewById(R.id.tv_a);
            vh.tv_b = convertView.findViewById(R.id.tv_b);
            vh.tv_c = convertView.findViewById(R.id.tv_c);
            vh.tv_d = convertView.findViewById(R.id.tv_d);
            vh.iv_a = convertView.findViewById(R.id.iv_a);
            vh.iv_b = convertView.findViewById(R.id.iv_b);
            vh.iv_c = convertView.findViewById(R.id.iv_c);
            vh.iv_d = convertView.findViewById(R.id.iv_d);
            convertView.setTag(vh);
        }else{
            vh = ((ViewHolder) convertView.getTag());
        }

        final ExercisesBean bean = getItem(position);
        if (bean != null) {
            vh.subject.setText(bean.getSubject());
            vh.tv_a.setText(bean.getA());
            vh.tv_b.setText(bean.getB());
            vh.tv_c.setText(bean.getC());
            vh.tv_d.setText(bean.getD());
        }

        if (!selectedPosition.contains("" + position)){
            vh.iv_a.setImageResource(R.drawable.exercises_a);
            vh.iv_b.setImageResource(R.drawable.exercises_b);
            vh.iv_c.setImageResource(R.drawable.exercises_c);
            vh.iv_d.setImageResource(R.drawable.exercises_d);
        }else{
            AnalysisUtils.setABCDEnable(false, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            switch (bean.getSelect()){
                case 0:
                    // 用户所选项是正确的
                    if (bean.getAnswer() == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 2){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 4){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    // 用户所选 A 是错误的
                    vh.iv_a.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2){
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 3){
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 4){
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    // 用户所选 B 是错误的
                    vh.iv_b.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 4){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    // 用户所选 C 是错误的
                    vh.iv_c.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.getAnswer() == 4){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    // 用户所选 D 是错误的
                    vh.iv_d.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_c);
                    }else if (bean.getAnswer() == 2){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    }else if (bean.getAnswer() == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                default:
                    break;
            }
        }
        // 当用户点击 A 选项的点击事件
        vh.iv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position)){
                    selectedPosition.remove("" + position);
                }else{
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectA(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            }
        });
        // 当用户点击 B 选项的点击事件
        vh.iv_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position)){
                    selectedPosition.remove("" + position);
                }else{
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectB(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            }
        });
        // 当用户点击 C 选项的点击事件
        vh.iv_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position)){
                    selectedPosition.remove("" + position);
                }else{
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectC(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            }
        });
        // 当用户点击 D 选项的点击事件
        vh.iv_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的 position
                if (selectedPosition.contains("" + position)){
                    selectedPosition.remove("" + position);
                }else{
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectD(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            }
        });
        return convertView;
    }

    class ViewHolder{
        public TextView subject, tv_a, tv_b, tv_c, tv_d;
        public ImageView iv_a, iv_b, iv_c, iv_d;
    }

    public interface OnSelectListener{
        void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
    }
}
