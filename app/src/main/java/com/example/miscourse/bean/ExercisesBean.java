package com.example.miscourse.bean;

public class ExercisesBean {
    public int id;  // 每章习题 ID
    public String title;  // 每章习题标题
    public String content;  // 每章习题的数目
    public int background;  // 每章习题前边的序号背景
    public int subjectId;  // 每道题的 ID
    public String subject;  // 每道题的题干
    public String a;  // 每道题的 A 选项
    public String b;  // 每道题的 B 选项
    public String c;  // 每道题的 C 选项
    public String d;  // 每道题的 D 选项
    public int answer; // 每道题的正确答案
    /* select 为 0 表示所选是对的，
    1 表示选中的 A 选项是错的，2 表示选中的 B 选项是错的，
    3 表示选中的 C 选项是错的，4 表示选中的 D 选项是错的 */
    public int select;
}
