package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumName
 * @Description TODO
 * @CreateTime 2021年03月16日 18:26:00
 */
public enum EnumParamName
{
    /**
     * activity 间传参;
     */
    ID("id"),

    TITLE("title"),
    ;

    private final String literal;

    EnumParamName(String literal)
    {
        this.literal = literal;
    }

    public String literal()
    {
        return literal;
    }
}
