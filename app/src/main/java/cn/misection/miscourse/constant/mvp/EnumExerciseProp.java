package cn.misection.miscourse.constant.mvp;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumCourseProp
 * @Description TODO
 * @CreateTime 2021年03月10日 22:23:00
 */
public enum EnumExerciseProp
{
    /**
     * 章节;
     */
    CHAPTER_01("Android 基础入门", 5),

    CHAPTER_02("Android UI 开发", 5),

    CHAPTER_03("Activity", 5),

    CHAPTER_04("数据存储", 5),

    CHAPTER_05("SQLite 数据库", 5),

    CHAPTER_06("广播接收者", 5),

    CHAPTER_07("服务", 5),

    CHAPTER_08("内容提供者", 5),

    CHAPTER_09("网络编程", 5),

    CHAPTER_10("高级编程", 5),
    ;

    private final String chapterName;

    private final int topicCount;

    EnumExerciseProp(String chapterName, int topicCount)
    {
        this.chapterName = chapterName;
        this.topicCount = topicCount;
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public int getTopicCount()
    {
        return topicCount;
    }

    public static EnumExerciseProp valueOf(int ordinal)
    {
        if (ordinal < 0 || ordinal >= values().length)
        {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return EnumExerciseProp.values()[ordinal];
    }
}
