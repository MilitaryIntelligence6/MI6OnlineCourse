package cn.misection.miscourse.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.entity.VideoBean;

import java.util.List;

public class VideoListAdapter extends BaseAdapter
{
    private Context context;

    /**
     * 视频列表数据;
     */
    private List<VideoBean> videoList;

    /**
     * 点击时选中的位置;
     */
    private int selectedPosition = -1;

    private OnSelectListener onSelectListener;

    public VideoListAdapter(Context context, OnSelectListener onSelectListener)
    {
        this.context = context;
        this.onSelectListener = onSelectListener;
    }

    public void setSelectedPosition(int position)
    {
        this.selectedPosition = position;
    }

    /**
     * 设置数据更新界面;
     * @param videoList list;
     */
    public void setData(List<VideoBean> videoList)
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.video_list_item, null);
            viewHolder.setTitleTextView((TextView) convertView.findViewById(R.id.tv_video_title));
            viewHolder.setIconImageView((ImageView) convertView.findViewById(R.id.iv_left_icon));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        final VideoBean bean = getItem(position);
        viewHolder.getIconImageView().setImageResource(R.drawable.course_bar_icon);
        viewHolder.getTitleTextView().setTextColor(Color.parseColor("#333333"));
        if (bean != null)
        {
            viewHolder.getTitleTextView().setText(bean.getSecondTitle());
            // 设置选中效果
            if (selectedPosition == position)
            {
                viewHolder.getIconImageView().setImageResource(R.drawable.course_intro_icon);
                viewHolder.getTitleTextView().setTextColor(Color.parseColor("#009958"));
            }
            else
            {
                viewHolder.getIconImageView().setImageResource(R.drawable.course_bar_icon);
                viewHolder.getTitleTextView().setTextColor(Color.parseColor("#333333"));
            }
        }
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (bean == null)
                {
                    return;
                }
                // 播放视频
                onSelectListener.onSelect(position, viewHolder.getIconImageView());
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        private TextView titleTextView;
        private ImageView iconImageView;

        public TextView getTitleTextView()
        {
            return titleTextView;
        }

        public void setTitleTextView(TextView titleTextView)
        {
            this.titleTextView = titleTextView;
        }

        public ImageView getIconImageView()
        {
            return iconImageView;
        }

        public void setIconImageView(ImageView iconImageView)
        {
            this.iconImageView = iconImageView;
        }
    }

    public interface OnSelectListener
    {
        /**
         * 被选中时;
         * @param position
         * @param imageView
         */
        void onSelect(int position, ImageView imageView);
    }
}
