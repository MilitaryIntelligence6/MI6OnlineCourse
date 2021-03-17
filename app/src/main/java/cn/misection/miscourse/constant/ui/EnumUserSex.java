package cn.misection.miscourse.constant.ui;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumSex
 * @Description TODO
 * @CreateTime 2021年03月17日 00:35:00
 */
public enum EnumUserSex
{
    /**
     * 性别;
     */
    MALE("男"),

    FEMALE("女"),
    ;

    private final String text;

    EnumUserSex(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    private static final Map<String, EnumUserSex> lookup = new HashMap<>();

    static
    {
        for (EnumUserSex userSex : EnumSet.allOf(EnumUserSex.class))
        {
            lookup.put(userSex.text, userSex);
        }
    }

    public static int count()
    {
        return values().length;
    }

    public static EnumUserSex valueOf(int ordinal)
    {
        return values()[ordinal];
    }

    public static EnumUserSex selectByText(String text)
    {
        return lookup.get(text);
    }
}
