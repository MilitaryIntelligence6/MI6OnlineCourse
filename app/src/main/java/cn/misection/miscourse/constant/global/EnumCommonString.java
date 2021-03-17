package cn.misection.miscourse.constant.global;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumCommonString
 * @Description TODO
 * @CreateTime 2021年03月16日 19:12:00
 */
public enum EnumCommonString
{
    /**
     * 常用字符串;
     */
    EMPTY(""),

    NEW_LINE("\n");
    ;

    private final String value;

    EnumCommonString(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }
}
