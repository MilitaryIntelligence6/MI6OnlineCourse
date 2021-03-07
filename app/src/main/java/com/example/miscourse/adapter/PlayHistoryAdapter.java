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
import com.example.miscourse.activity.VideoPlayActivity;
import com.example.miscourse.bean.VideoBean;

import java.util.List;

public class PlayHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<VideoBean> vb1;

    public PlayHistoryAdapter(Context context){
        this.context = context;
    }

    public void setData(List<VideoBean> vb1){
        this.vb1 = vb1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return vb1 == null ? 0 : vb1.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return vb1 == null ? null : vb1.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.play_history_list_item, null);
            vh.tvTitle = convertView.findViewById(R.id.tv_adapter_title);
            vh.tvVideoTitle = convertView.findViewById(R.id.tv_video_title);
            vh.ivIcon = convertView.findViewById(R.id.iv_video_icon);
            convertView.setTag(vh);
        }else{
            vh = ((ViewHolder) convertView.getTag());
        }
        final VideoBean bean = getItem(position);
        if (bean != null) {
            vh.tvTitle.setText(bean.title);
            vh.tvVideoTitle.setText(bean.secondTitle);
            switch (bean.chapterId){
                case 1:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon1);
                    break;
                case 2:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon2);
                    break;
                case 3:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon3);
                    break;
                case 4:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon4);
                    break;
                case 5:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon10);
                    break;
                case 6:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon6);
                    break;
                case 7:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon7);
                    break;
                case 8:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon8);
                    break;
                case 9:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon9);
                    break;
                case 10:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon10);
                    break;
                default:
                    vh.ivIcon.setImageResource(R.drawable.video_play_icon1);
                    break;
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null){
                    return;
                }
                // 跳转到播放视频界面
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("videoPath", bean.videoPath);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        public TextView tvTitle, tvVideoTitle;
        public ImageView ivIcon;
    }
}
