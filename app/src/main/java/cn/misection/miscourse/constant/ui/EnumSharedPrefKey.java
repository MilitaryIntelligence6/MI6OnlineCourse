package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumSharedPrefKey
 * @Description TODO
 * @CreateTime 2021年03月17日 22:04:00
 */
public enum EnumSharedPrefKey
{
    /**
     * sharedPref的key;
     */
    IS_LOGIN("isLogin"),

    LOGIN_USERNAME("loginUsername"),
    ;

    private final String literal;

    EnumSharedPrefKey(String literal)
    {
        this.literal = literal;
    }

    public String literal()
    {
        return literal;
    }
}
