package com.example.miscourse.bean;

public class VideoBean
{
    private int chapterId;

    private int videoId;

    private String title;

    private String secondTitle;

    private String videoPath;

    /**
     * 章节Id
     */
    public int getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(int chapterId)
    {
        this.chapterId = chapterId;
    }

    /**
     * 视频Id
     */
    public int getVideoId()
    {
        return videoId;
    }

    public void setVideoId(int videoId)
    {
        this.videoId = videoId;
    }

    /**
     * 章节标题
     */
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * 视频标题
     */
    public String getSecondTitle()
    {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle)
    {
        this.secondTitle = secondTitle;
    }

    /**
     * 视频播放地址
     */
    public String getVideoPath()
    {
        return videoPath;
    }

    public void setVideoPath(String videoPath)
    {
        this.videoPath = videoPath;
    }
}
