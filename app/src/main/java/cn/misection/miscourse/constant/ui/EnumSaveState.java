package cn.misection.miscourse.constant.ui;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumSaveState
 * @Description TODO
 * @CreateTime 2021年03月16日 19:02:00
 */
public enum EnumSaveState
{
    /**
     * 保存成功之类的状态;
     */
    SUCCESSFULLY_SAVE("保存成功"),

    CAN_NOT_BE_EMPTY_WARNING("%s不能为空"),
    ;

    private final String literal;

    EnumSaveState(String literal)
    {
        this.literal = literal;
    }

    public String literal()
    {
        return literal;
    }
}
