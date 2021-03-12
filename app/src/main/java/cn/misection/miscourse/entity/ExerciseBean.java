package cn.misection.miscourse.entity;

import cn.misection.miscourse.constant.EnumExercise;

/**
 * @author Administrator
 */
public class ExerciseBean
{
    /**
     * 每章习题 ID
     */
    private int id;

    /**
     * 每章习题标题
     */
    private String title;

    /**
     * 每章习题的数目
     */
    private String content;

    /**
     * 每章习题前边的序号背景
     */
    private int background;

    /**
     * 每道题的 ID
     */
    private int subjectId;

    /**
     * 每道题的题干
     */
    private String subject;

    /**
     * 选项array;
     */
    private String[] optionTextArray;

//    /**
//     * 每道题的 A 选项
//     */
//    private String optionTextA;
//
//    /**
//     * 每道题的 B 选项
//     */
//    private String optionTextB;
//
//    /**
//     * 每道题的 C 选项
//     */
//    private String optionTextC;
//
//    /**
//     * 每道题的 D 选项
//     */
//    private String optionTextD;

    /**
     * 每道题的正确答案
     */
    private EnumExercise correctAnswer;

    /**
     * select 为 0 表示所选是对的，
     * 1 表示选中的 A 选项是错的，
     * 2 表示选中的 B 选项是错的，
     * 3 表示选中的 C 选项是错的，
     * 4 表示选中的 D 选项是错的
     */
    private EnumExercise userSelect;

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

    public String[] getOptionTextArray()
    {
        return optionTextArray;
    }

    public void setOptionTextArray(String[] optionTextArray)
    {
        this.optionTextArray = optionTextArray;
    }

    public EnumExercise getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(EnumExercise correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

    public EnumExercise getUserSelect()
    {
        return userSelect;
    }

    public void setUserSelect(EnumExercise userSelect)
    {
        this.userSelect = userSelect;
    }
}
