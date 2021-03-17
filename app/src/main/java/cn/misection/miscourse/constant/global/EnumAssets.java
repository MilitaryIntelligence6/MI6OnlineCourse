package cn.misection.miscourse.constant.global;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumAssets
 * @Description TODO
 * @CreateTime 2021年03月17日 19:11:00
 */
public enum EnumAssets
{
    /**
     * 指向 assets pkg下的文件;
     */
    VIDEO_DATA("data.json"),
    ;

    private final String path;

    EnumAssets(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
}
