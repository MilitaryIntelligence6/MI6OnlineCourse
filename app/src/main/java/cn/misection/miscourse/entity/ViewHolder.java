package cn.misection.miscourse.entity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class ViewHolder
{
    private TextView subject;

    private TextView[] choiceTextArray;

    private ImageView[] choiceImageArray;


    public TextView getSubject()
    {
        return subject;
    }

    public TextView[] getChoiceTextArray()
    {
        return choiceTextArray;
    }

    public void setChoiceTextArray(TextView[] choiceTextArray)
    {
        this.choiceTextArray = choiceTextArray;
    }

    public ImageView[] getChoiceImageArray()
    {
        return choiceImageArray;
    }

    public void setChoiceImageArray(ImageView[] choiceImageArray)
    {
        this.choiceImageArray = choiceImageArray;
    }

    public void setSubject(TextView subject)
    {
        this.subject = subject;
    }
}