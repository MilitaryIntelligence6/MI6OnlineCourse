package cn.misection.miscourse.ui.adapter.holder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class VideoViewHolder
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