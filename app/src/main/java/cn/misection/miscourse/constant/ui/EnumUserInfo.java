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
    NICE_NAME(1, 8, "nickname", "昵称"),

    SIGNATURE(2, 16, "signature", "签名"),
    ;

    private final int flag;

    private final int lengthLimit;

    private final String englishLiteral;

    private final String chineseLiteral;

    EnumUserInfo(int flag,
                 int lengthLimit,
                 String englishLiteral,
                 String chineseLiteral)
    {
        this.flag = flag;
        this.lengthLimit = lengthLimit;
        this.englishLiteral = englishLiteral;
        this.chineseLiteral = chineseLiteral;
    }

    public int flag()
    {
        return flag;
    }

    public int lengthLimit()
    {
        return lengthLimit;
    }

    public String englishLiteral()
    {
        return englishLiteral;
    }

    public String chineseLiteral()
    {
        return chineseLiteral;
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
