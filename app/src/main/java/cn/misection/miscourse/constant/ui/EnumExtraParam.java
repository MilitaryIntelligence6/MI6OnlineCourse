package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumName
 * @Description TODO
 * @CreateTime 2021年03月16日 18:26:00
 */
public enum EnumExtraParam
{
    /**
     * activity 间传参;
     */
    ID("id"),

    TITLE("title"),

    CONTENT("content"),

    FLAG("flag"),

    IS_LOGIN("isLogin"),

    USERNAME("username"),

    NICKNAME("nickname"),

    SIGNATURE("signature"),

    SEX("sex"),

    INTRO("intro"),

    VIDEO_PATH("videoPath"),

    POSITION("position"),
    ;

    private final String literal;

    EnumExtraParam(String literal)
    {
        this.literal = literal;
    }

    public String literal()
    {
        return literal;
    }
}
