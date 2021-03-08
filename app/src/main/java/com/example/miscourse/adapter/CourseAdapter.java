package com.example.miscourse.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miscourse.R;

import com.example.miscourse.activity.VideoListActivity;
import com.example.miscourse.bean.CourseBean;

import java.util.List;

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private List<List<CourseBean>> cb1;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    // 设置数据更新页面
    public void setData(List<List<CourseBean>> cb1) {
        this.cb1 = cb1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cb1 == null ? 0 : cb1.size();
    }

    @Override
    public List<CourseBean> getItem(int position) {
        return cb1 == null ? null : cb1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.course_list_item, null);
            vh.iv_left_img = convertView.findViewById(R.id.iv_left_img);
            vh.iv_right_img = convertView.findViewById(R.id.iv_right_img);
            vh.tv_left_img_title = convertView.findViewById(R.id.tv_left_img_title);
            vh.tv_right_img_title = convertView.findViewById(R.id.tv_right_img_title);
            vh.tv_left_title = convertView.findViewById(R.id.tv_left_title);
            vh.tv_right_title = convertView.findViewById(R.id.tv_right_title);
            convertView.setTag(vh);
        } else {
            // 复用 convertView
            vh = ((ViewHolder) convertView.getTag());
        }

        final List<CourseBean> list = getItem(position);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final CourseBean bean = list.get(i);
                switch (i) {
                    case 0: // 设置左边图片与标题信息
                        vh.tv_left_img_title.setText(bean.getImgTitle());
                        vh.tv_left_title.setText(bean.getTitle());
                        setLeftImg(bean.getId(), vh.iv_left_img);
                        vh.iv_left_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 跳转到课程详情界面
                                Intent intent = new Intent(context, VideoListActivity.class);
                                intent.putExtra("id", bean.getId());
                                intent.putExtra("intro", bean.getIntro());
                                context.startActivity(intent);
                            }
                        });
                        break;
                    case 1: // 设置右边图片与标题信息
                        vh.tv_right_img_title.setText(bean.getImgTitle());
                        vh.tv_right_title.setText(bean.getTitle());
                        setRightImg(bean.getId(), vh.iv_right_img);
                        vh.iv_right_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 跳转到课程详情界面
                                Intent intent = new Intent(context, VideoListActivity.class);
                                intent.putExtra("id", bean.getId());
                                intent.putExtra("intro", bean.getIntro());
                                context.startActivity(intent);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        return convertView;
    }

    // 设置右边图片
    private void setRightImg(int id, ImageView iv_right_img) {
        switch (id) {
            case 2:
                iv_right_img.setImageResource(R.drawable.chapter_2_icon);
                break;
            case 4:
                iv_right_img.setImageResource(R.drawable.chapter_4_icon);
                break;
            case 6:
                iv_right_img.setImageResource(R.drawable.chapter_6_icon);
                break;
            case 8:
                iv_right_img.setImageResource(R.drawable.chapter_8_icon);
                break;
            case 10:
                iv_right_img.setImageResource(R.drawable.chapter_10_icon);
                break;
        }
    }

    // 设置左边图片
    private void setLeftImg(int id, ImageView iv_left_img) {
        switch (id) {
            case 1:
                iv_left_img.setImageResource(R.drawable.chapter_1_icon);
                break;
            case 3:
                iv_left_img.setImageResource(R.drawable.chapter_3_icon);
                break;
            case 5:
                iv_left_img.setImageResource(R.drawable.chapter_5_icon);
                break;
            case 7:
                iv_left_img.setImageResource(R.drawable.chapter_7_icon);
                break;
            case 9:
                iv_left_img.setImageResource(R.drawable.chapter_9_icon);
                break;
        }
    }

    class ViewHolder {
        public TextView tv_left_img_title, tv_left_title, tv_right_img_title, tv_right_title;
        public ImageView iv_left_img, iv_right_img;
    }
}
