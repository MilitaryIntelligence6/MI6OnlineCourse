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
    INSTANCE(0, false),
    ;

    private final int intVal;

    /**
     * boolean 其实也是 int;
     */
    private final boolean boolVal;

    EnumDefaultValue(int intVal, boolean boolVal)
    {
        this.intVal = intVal;
        this.boolVal = boolVal;
    }

    public int intVal()
    {
        return intVal;
    }

    public boolean boolVal()
    {
        return boolVal;
    }
}
