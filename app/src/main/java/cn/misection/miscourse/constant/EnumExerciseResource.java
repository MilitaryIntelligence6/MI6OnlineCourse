package cn.misection.miscourse.constant;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumExerciseImageView
 * @Description TODO
 * @CreateTime 2021年03月12日 09:52:00
 */
public enum EnumExerciseResource
{
    /**
     * 选项;
     */
    A(R.id.iv_a, R.id.tv_a),

    B(R.id.iv_b, R.id.tv_b),

    C(R.id.iv_c, R.id.tv_c),

    D(R.id.iv_d, R.id.tv_d),
    ;

    private int imageView;

    private int textView;

    EnumExerciseResource(int imageView, int textView)
    {
        this.imageView = imageView;
        this.textView = textView;
    }

    public int getImageView()
    {
        return imageView;
    }

    public void setImageView(int imageView)
    {
        this.imageView = imageView;
    }

    public int getTextView()
    {
        return textView;
    }

    public void setTextView(int textView)
    {
        this.textView = textView;
    }

    public static EnumExerciseResource valueOf(int ordinal)
    {
        if (ordinal < 0 || ordinal > values().length - 1)
        {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return EnumExerciseResource.values()[ordinal];
    }

    public static int count()
    {
        return values().length;
    }
}
