package cn.misection.miscourse.ui.adapter.holder;

import android.widget.TextView;

/**
 * @author Administrator
 */
public class ExerciseViewHolder
{
    private TextView titleTextView;

    private TextView contentTextView;

    private TextView orderTextView;

    public TextView getTitleTextView()
    {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView)
    {
        this.titleTextView = titleTextView;
    }

    public TextView getContentTextView()
    {
        return contentTextView;
    }

    public void setContentTextView(TextView contentTextView)
    {
        this.contentTextView = contentTextView;
    }

    public TextView getOrderTextView()
    {
        return orderTextView;
    }

    public void setOrderTextView(TextView orderTextView)
    {
        this.orderTextView = orderTextView;
    }
}