package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumDefaultValue
 * @Description TODO
 * @CreateTime 2021年03月16日 18:32:00
 */
public enum EnumDefaultValue
{
    /**
     * intent 传参;
     */
    INT_EXTRA(0),
    ;

    private final int value;

    EnumDefaultValue(int value)
    {
        this.value = value;
    }

    public int value()
    {
        return value;
    }
}
