package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumViewParam
 * @Description TODO
 * @CreateTime 2021年03月16日 18:37:00
 */
public enum EnumViewParam
{
    /**
     * view 的属性;
     */
    EXERCISE_DETAIL_TEXT_VIEW("一、选择题", 16.0f, new int[] {10, 15, 0, 0}),
    ;

    private final String text;

    private final double textSize;

    private final int[] padding;

    EnumViewParam(String text, double textSize, int[] padding)
    {
        this.text = text;
        this.textSize = textSize;
        this.padding = padding;
    }

    public String getText()
    {
        return text;
    }

    public float getTextSize()
    {
        return (float) textSize;
    }

    public int left()
    {
        return padding[LEFT_INDEX];
    }

    public int top()
    {
        return padding[TOP_INDEX];
    }


    public int right()
    {
        return padding[RIGHT_INDEX];
    }

    public int bottom()
    {
        return padding[BOTTOM_INDEX];
    }

    private static final int LEFT_INDEX = 0;

    private static final int TOP_INDEX = 1;

    private static final int RIGHT_INDEX = 2;

    private static final int BOTTOM_INDEX = 3;


}
