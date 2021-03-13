package cn.misection.miscourse.ui.adapter.adapterconst;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumCourseViewEle
 * @Description TODO
 * @CreateTime 2021年03月13日 15:44:00
 */
public enum EnumCourseViewEle
{
    /**
     * course分成两部分;
     */
    LEFT(R.id.left_image_view,
            R.id.left_title_text_view,
            R.id.left_img_title_text_view),

    RIGHT(R.id.right_image_view,
            R.id.right_title_text_view,
            R.id.right_img_title_text_view),
    ;

    private int imageViewRes;

    private int titleTextViewRes;

    private int imgTitleTextViewRes;

    EnumCourseViewEle(int imageViewRes, int titleTextViewRes, int imgTitleTextViewRes)
    {
        this.imageViewRes = imageViewRes;
        this.titleTextViewRes = titleTextViewRes;
        this.imgTitleTextViewRes = imgTitleTextViewRes;
    }

    public int getImageViewRes()
    {
        return imageViewRes;
    }

    public int getTitleTextViewRes()
    {
        return titleTextViewRes;
    }

    public int getImgTitleTextViewRes()
    {
        return imgTitleTextViewRes;
    }

    public static int count()
    {
        return values().length;
    }

    public static EnumCourseViewEle valueOf(int ordinal)
    {
        return values()[ordinal];
    }
}
