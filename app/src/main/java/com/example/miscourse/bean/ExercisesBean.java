package com.example.miscourse.bean;

public class ExercisesBean {
    private int id;  // 每章习题 ID
    private String title;  // 每章习题标题
    private String content;  // 每章习题的数目
    private int background;  // 每章习题前边的序号背景
    private int subjectId;  // 每道题的 ID
    private String subject;  // 每道题的题干
    private String a;  // 每道题的 A 选项
    private String b;  // 每道题的 B 选项
    private String c;  // 每道题的 C 选项
    private String d;  // 每道题的 D 选项
    private int answer; // 每道题的正确答案
    /* select 为 0 表示所选是对的，
    1 表示选中的 A 选项是错的，2 表示选中的 B 选项是错的，
    3 表示选中的 C 选项是错的，4 表示选中的 D 选项是错的 */
    private int select;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getBackground()
    {
        return background;
    }

    public void setBackground(int background)
    {
        this.background = background;
    }

    public int getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(int subjectId)
    {
        this.subjectId = subjectId;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getA()
    {
        return a;
    }

    public void setA(String a)
    {
        this.a = a;
    }

    public String getB()
    {
        return b;
    }

    public void setB(String b)
    {
        this.b = b;
    }

    public String getC()
    {
        return c;
    }

    public void setC(String c)
    {
        this.c = c;
    }

    public String getD()
    {
        return d;
    }

    public void setD(String d)
    {
        this.d = d;
    }

    public int getAnswer()
    {
        return answer;
    }

    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    public int getSelect()
    {
        return select;
    }

    public void setSelect(int select)
    {
        this.select = select;
    }
}
