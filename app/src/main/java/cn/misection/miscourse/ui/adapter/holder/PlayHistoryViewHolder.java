package cn.misection.miscourse.ui.adapter.holder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class PlayHistoryViewHolder
{
    private TextView titleTextView;

    private TextView VideoTitleTextView;

    private ImageView iconImageView;

    public TextView getTitleTextView()
    {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView)
    {
        this.titleTextView = titleTextView;
    }

    public TextView getVideoTitleTextView()
    {
        return VideoTitleTextView;
    }

    public void setVideoTitleTextView(TextView videoTitleTextView)
    {
        this.VideoTitleTextView = videoTitleTextView;
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