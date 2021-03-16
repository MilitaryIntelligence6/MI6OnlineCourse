package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumFindPassword
 * @Description TODO
 * @CreateTime 2021年03月16日 19:47:00
 */
public enum EnumSecurity
{
    /**
     *
     */
    SECURITY_QUESTION("密保问题"),

    FIND_PASSWORD("找回密码"),
    ;

    private final String text;

    EnumSecurity(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }
}
