package cn.misection.miscourse.constant.util.db;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumContentKey
 * @Description TODO
 * @CreateTime 2021年03月19日 10:56:00
 */
public enum EnumContentKey
{
    /**
     * contentValue 中的值;
     */
    USERNAME("username"),

    NICKNAME("nickname"),

    SEX("sex"),

    SIGNATURE("signature"),

    CHAPTER_ID("chapterId"),

    VIDEO_ID("videoId"),

    VIDEO_PATH("videoPath"),

    TITLE("title"),

    SECOND_TITLE("secondTitle"),
    ;

    private final String literal;

    EnumContentKey(String literal)
    {
        this.literal = literal;
    }

    public String literal()
    {
        return literal;
    }
}
