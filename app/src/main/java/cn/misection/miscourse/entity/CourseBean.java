package cn.misection.miscourse.entity;

public class CourseBean
{
    /**
     * 每章 Id
     */
    private int id;

    /**
     * 图片上的标题
     */
    private String imgTitle;

    /**
     * 章标题
     */
    private String title;

    /**
     * 章视频简介
     */
    private String intro;

    /**
     * 广告栏上的图片
     */
    private String icon;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getImgTitle()
    {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle)
    {
        this.imgTitle = imgTitle;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
