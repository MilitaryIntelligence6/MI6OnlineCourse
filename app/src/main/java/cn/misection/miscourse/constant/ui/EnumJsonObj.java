package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumJsonEle
 * @Description TODO
 * @CreateTime 2021年03月17日 19:14:00
 */
public enum EnumJsonObj
{
    /**
     * json 中的对象名;
     */
    CHAPTER_ID("chapterId"),

    TITLE("title"),

    VIDEO_ID("videoId"),

    SECOND_TITLE("secondTitle"),
    ;

    private final String jsonName;

    EnumJsonObj(String jsonName)
    {
        this.jsonName = jsonName;
    }

    public String getJsonName()
    {
        return jsonName;
    }
}
