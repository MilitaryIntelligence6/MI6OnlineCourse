package cn.misection.miscourse.constant.util.db;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumTable
 * @Description TODO
 * @CreateTime 2021年03月19日 11:03:00
 */
public enum EnumDbInfo
{
    /**
     * 数据库信息;
     */
    DB_NAME("bxg.db"),

    /**
     * 用户表;
     */
    TB_USER_INFO("userinfo"),

    /**
     * 视频播放表;
     */
    U_VIDEO_PLAY_LIST("videoplaylist"),
    ;

    private final String value;

    EnumDbInfo(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }
}
