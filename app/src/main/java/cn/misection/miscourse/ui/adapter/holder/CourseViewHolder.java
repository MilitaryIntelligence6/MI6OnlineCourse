package cn.misection.miscourse.ui.adapter.holder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class CourseViewHolder
{
    private TextView[] imgTitleTextViewArray;

    private TextView[] titleTextViewArray;

    private ImageView[] imgViewArray;

    private CourseViewHolder(Builder builder)
    {
        this.imgTitleTextViewArray = builder.imgTitleTextViewArray;
        this.imgViewArray = builder.imgViewArray;
        this.titleTextViewArray = builder.titleTextViewArray;
    }

    public static class Builder
    {
        private TextView[] imgTitleTextViewArray;

        private TextView[] titleTextViewArray;

        private ImageView[] imgViewArray;
        
        public Builder() {}

        public CourseViewHolder build()
        {
            return new CourseViewHolder(this);
        }
        
        public Builder putImgTitleTextViewArray(TextView[] imgTitleTextViewArray)
        {
            this.imgTitleTextViewArray = imgTitleTextViewArray;
            return this;
        }

        public Builder putTitleTextViewArray(TextView[] titleTextViewArray)
        {
            this.titleTextViewArray = titleTextViewArray;
            return this;
        }

        public Builder putImgViewArray(ImageView[] imgViewArray)
        {
            this.imgViewArray = imgViewArray;
            return this;
        }
    }

    public TextView[] getImgTitleTextViewArray()
    {
        return imgTitleTextViewArray;
    }

    public void setImgTitleTextViewArray(TextView[] imgTitleTextViewArray)
    {
        this.imgTitleTextViewArray = imgTitleTextViewArray;
    }

    public TextView[] getTitleTextViewArray()
    {
        return titleTextViewArray;
    }

    public void setTitleTextViewArray(TextView[] titleTextViewArray)
    {
        this.titleTextViewArray = titleTextViewArray;
    }

    public ImageView[] getImgViewArray()
    {
        return imgViewArray;
    }

    public void setImgViewArray(ImageView[] imgViewArray)
    {
        this.imgViewArray = imgViewArray;
    }
}