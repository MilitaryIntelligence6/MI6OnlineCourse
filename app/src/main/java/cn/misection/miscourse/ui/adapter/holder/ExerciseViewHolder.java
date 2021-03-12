package cn.misection.miscourse.ui.adapter.holder;

import android.widget.TextView;

/**
 * @author Administrator
 */
public class ExerciseViewHolder
{
    private TextView title;

    private TextView content;

    private TextView order;

    public TextView getTitle()
    {
        return title;
    }

    public void setTitle(TextView title)
    {
        this.title = title;
    }

    public TextView getContent()
    {
        return content;
    }

    public void setContent(TextView content)
    {
        this.content = content;
    }

    public TextView getOrder()
    {
        return order;
    }

    public void setOrder(TextView order)
    {
        this.order = order;
    }
}