package cn.misection.miscourse.constant.ui;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumUserInfoChange
 * @Description TODO
 * @CreateTime 2021年03月17日 16:26:00
 */
public enum EnumUpdateInfoChoice
{
    /**
     * 改昵称改签名;
     * 思考到底是绑定枚举还是直接绑定字符串;
     */
    NICKNAME(R.id.nickname_text_view, EnumExtraParam.NICKNAME),

    SIGNATURE(R.id.signature_text_view, EnumExtraParam.SIGNATURE),
    ;

    private final int resId;

    private final EnumExtraParam extraParam;

    EnumUpdateInfoChoice(int resId, EnumExtraParam extraParam)
    {
        this.resId = resId;
        this.extraParam = extraParam;
    }

    public int getResId()
    {
        return resId;
    }

    public EnumExtraParam getExtraParam()
    {
        return extraParam;
    }

    public static int count()
    {
        return values().length;
    }

    public static EnumUpdateInfoChoice valueOf(int ordinal)
    {
        return values()[ordinal];
    }
}
