package cn.misection.miscourse.constant.ui;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumLegthLimit
 * @Description TODO
 * @CreateTime 2021年03月16日 15:43:00
 */
public enum EnumUserInfo
{
    /**
     * 限制包内属性长度;
     * 如最长昵称;
     */
    NICE_NAME(1, 8),

    SIGNATURE(2, 16),
    ;

    private final int flag;

    private final int lengthLimit;

    EnumUserInfo(int flag, int lengthLimit)
    {
        this.flag = flag;
        this.lengthLimit = lengthLimit;
    }

    public int flag()
    {
        return flag;
    }

    public int lengthLimit()
    {
        return lengthLimit;
    }

    private static final Map<Integer, EnumUserInfo> flagLookup
            = new HashMap<>(count());

    static
    {
        for (EnumUserInfo enumUserInfo : EnumSet.allOf(EnumUserInfo.class))
        {
            flagLookup.put(enumUserInfo.flag, enumUserInfo);
        }
    }

    public static EnumUserInfo selectEnumByFlag(Integer flag)
    {
        return flagLookup.get(flag);
    }

    public static int count()
    {
        return values().length;
    }
}
