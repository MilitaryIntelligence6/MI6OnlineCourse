package com.example.miscourse.bean;

public class UserBean
{
    private String username;

    private String nickname;

    private String sex;

    private String signature;

    /**
     * 用户名;
     */
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * 昵称;
     */
    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    /**
     * 性别;
     */
    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    /**
     * 签名;
     */
    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }
}
