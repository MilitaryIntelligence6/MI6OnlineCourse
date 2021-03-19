package cn.misection.miscourse.constant.ui.adapter;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ImageResourceMapper
 * @Description TODO
 * @CreateTime 2021年03月13日 15:11:00
 */
public enum EnumImageResMapper
{
    /**
     * 图片资源映射;
     * 0 暂时占位;
     */
    IMAGE_0(R.drawable.chapter_1_icon),

    IMAGE_1(R.drawable.chapter_1_icon),

    IMAGE_2(R.drawable.chapter_2_icon),

    IMAGE_3(R.drawable.chapter_3_icon),

    IMAGE_4(R.drawable.chapter_4_icon),

    IMAGE_5(R.drawable.chapter_5_icon),

    IMAGE_6(R.drawable.chapter_6_icon),

    IMAGE_7(R.drawable.chapter_7_icon),

    IMAGE_8(R.drawable.chapter_8_icon),

    IMAGE_9(R.drawable.chapter_9_icon),

    IMAGE_10(R.drawable.chapter_10_icon),
    ;

    private final int imgRes;

    EnumImageResMapper(int imgRes)
    {
        this.imgRes = imgRes;
    }

    public int getImgRes()
    {
        return imgRes;
    }

    public static EnumImageResMapper valueOf(int ordinal)
    {
        return values()[ordinal];
    }
}
