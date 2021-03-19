package cn.misection.miscourse.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.entity.EnumPlayHistory;
import cn.misection.miscourse.ui.activity.VideoPlayActivity;
import cn.misection.miscourse.entity.VideoBean;
import cn.misection.miscourse.ui.adapter.holder.PlayHistoryViewHolder;

import java.util.List;

/**
 * @author Administrator
 */
public class PlayHistoryAdapter extends BaseAdapter
{
    private Context context;

    private List<VideoBean> videoList;

    public PlayHistoryAdapter(Context context)
    {
        this.context = context;
    }

    public void putVideoList(List<VideoBean> videoList)
    {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return videoList == null ? 0 : videoList.size();
    }

    @Override
    public VideoBean getItem(int position)
    {
        return videoList == null ? null : videoList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final PlayHistoryViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new PlayHistoryViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.play_history_list_item, null);
            viewHolder.setTitleTextView(convertView.findViewById(R.id.adapter_title_text_view));
            viewHolder.setVideoTitleTextView(convertView.findViewById(R.id.video_title_text_view));
            viewHolder.setIconImageView(convertView.findViewById(R.id.video_icon_image_view));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((PlayHistoryViewHolder) convertView.getTag());
        }
        final VideoBean bean = getItem(position);
        if (bean != null)
        {
            viewHolder.getTitleTextView()
                    .setText(bean.getTitle());
            viewHolder.getVideoTitleTextView()
                    .setText(bean.getSecondTitle());
            viewHolder.getIconImageView()
                    .setImageResource(
                            EnumPlayHistory.valueOf(bean.getChapterId())
                                    .getResId()
                    );
        }
        convertView.setOnClickListener((View v) ->
        {
            if (bean == null)
            {
                return;
            }
            // 跳转到播放视频界面
            Intent intent = new Intent(context, VideoPlayActivity.class);
            intent.putExtra("videoPath", bean.getVideoPath());
            context.startActivity(intent);
        });
        return convertView;
    }
}
