package cn.misection.miscourse.ui.adapter.holder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class CourseViewHolder
{
    private TextView leftImgTitleTextView;

    private TextView leftTitleTextView;

    private TextView rightImgTitleTextView;

    private TextView rightTitleTextView;

    private ImageView leftImageView;

    private ImageView rightImageView;

    public TextView getLeftImgTitleTextView()
    {
        return leftImgTitleTextView;
    }

    public void setLeftImgTitleTextView(TextView leftImgTitleTextView)
    {
        this.leftImgTitleTextView = leftImgTitleTextView;
    }

    public TextView getLeftTitleTextView()
    {
        return leftTitleTextView;
    }

    public void setLeftTitleTextView(TextView leftTitleTextView)
    {
        this.leftTitleTextView = leftTitleTextView;
    }

    public TextView getRightImgTitleTextView()
    {
        return rightImgTitleTextView;
    }

    public void setRightImgTitleTextView(TextView rightImgTitleTextView)
    {
        this.rightImgTitleTextView = rightImgTitleTextView;
    }

    public TextView getRightTitleTextView()
    {
        return rightTitleTextView;
    }

    public void setRightTitleTextView(TextView rightTitleTextView)
    {
        this.rightTitleTextView = rightTitleTextView;
    }

    public ImageView getLeftImageView()
    {
        return leftImageView;
    }

    public void setLeftImageView(ImageView leftImageView)
    {
        this.leftImageView = leftImageView;
    }

    public ImageView getRightImageView()
    {
        return rightImageView;
    }

    public void setRightImageView(ImageView rightImageView)
    {
        this.rightImageView = rightImageView;
    }
}