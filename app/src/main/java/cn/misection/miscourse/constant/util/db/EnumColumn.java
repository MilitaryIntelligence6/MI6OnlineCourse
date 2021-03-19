package cn.misection.miscourse.constant.util.db;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumColumn
 * @Description TODO
 * @CreateTime 2021年03月19日 10:49:00
 */
public enum EnumColumn
{
    /**
     * json 中的列名;
     */
    CHAPTER_ID("chapterId"),

    VIDEO_ID("videoId"),

    VIDEO_PATH("videoPath"),

    TITLE("title"),

    SECOND_TITLE("secondTitle"),
    ;

    private final String literal;

    EnumColumn(String literal)
    {
        this.literal = literal;
    }

    public String literal()
    {
        return literal;
    }
}
