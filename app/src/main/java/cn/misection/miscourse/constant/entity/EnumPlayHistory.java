package cn.misection.miscourse.constant.entity;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumPlayHistory
 * @Description TODO
 * @CreateTime 2021年03月19日 10:36:00
 */
public enum EnumPlayHistory
{
    /**
     * 各个chapter;
     * 0 先占位;
     */
    CHAPTER_0(R.drawable.video_play_icon1),

    CHAPTER_1(R.drawable.video_play_icon1),

    CHAPTER_2(R.drawable.video_play_icon2),

    CHAPTER_3(R.drawable.video_play_icon3),

    CHAPTER_4(R.drawable.video_play_icon4),

    CHAPTER_5(R.drawable.video_play_icon10),

    CHAPTER_6(R.drawable.video_play_icon6),

    CHAPTER_7(R.drawable.video_play_icon7),

    CHAPTER_8(R.drawable.video_play_icon8),

    CHAPTER_9(R.drawable.video_play_icon9),

    CHAPTER_10(R.drawable.video_play_icon10),
    ;

    private final int resId;

    EnumPlayHistory(int resId)
    {
        this.resId = resId;
    }

    public int getResId()
    {
        return resId;
    }

    public static EnumPlayHistory valueOf(int ordinal)
    {
        return values()[ordinal];
    }
}
