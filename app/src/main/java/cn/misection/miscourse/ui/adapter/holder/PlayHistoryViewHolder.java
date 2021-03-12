package cn.misection.miscourse.ui.adapter.holder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class PlayHistoryViewHolder
{
    private TextView tvTitle;

    private TextView tvVideoTitle;

    private ImageView ivIcon;

    public TextView getTvTitle()
    {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle)
    {
        this.tvTitle = tvTitle;
    }

    public TextView getTvVideoTitle()
    {
        return tvVideoTitle;
    }

    public void setTvVideoTitle(TextView tvVideoTitle)
    {
        this.tvVideoTitle = tvVideoTitle;
    }

    public ImageView getIvIcon()
    {
        return ivIcon;
    }

    public void setIvIcon(ImageView ivIcon)
    {
        this.ivIcon = ivIcon;
    }
}