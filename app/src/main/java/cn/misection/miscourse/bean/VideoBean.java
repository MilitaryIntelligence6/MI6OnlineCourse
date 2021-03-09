package cn.misection.miscourse.bean;

public class VideoBean
{
    /**
     * 章节Id
     */
    public int chapterId;

    /**
     * 视频Id
     */
    public int videoId;

    /**
     * 章节标题
     */
    public String title;

    /**
     * 视频标题
     */
    public String secondTitle;

    /**
     * 视频播放地址
     */
    public String videoPath;

    public int getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(int chapterId)
    {
        this.chapterId = chapterId;
    }

    public int getVideoId()
    {
        return videoId;
    }

    public void setVideoId(int videoId)
    {
        this.videoId = videoId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSecondTitle()
    {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle)
    {
        this.secondTitle = secondTitle;
    }

    public String getVideoPath()
    {
        return videoPath;
    }

    public void setVideoPath(String videoPath)
    {
        this.videoPath = videoPath;
    }
}
