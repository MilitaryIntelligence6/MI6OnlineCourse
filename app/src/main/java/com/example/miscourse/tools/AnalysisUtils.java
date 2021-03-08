package com.example.miscourse.tools;

import android.util.Xml;
import android.widget.ImageView;

import com.example.miscourse.bean.CourseBean;
import com.example.miscourse.bean.ExercisesBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AnalysisUtils {
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExercisesBean> exercisesInfos = null;
        ExercisesBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        exercisesInfos = new ArrayList<>();
                    } else if ("exercises".equals(parser.getName())) {
                        exercisesInfo = new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesInfo.setSubjectId(Integer.parseInt(ids));
                    } else if ("subject".equals(parser.getName())) {
                        String subject = parser.nextText();
                        exercisesInfo.setSubject(subject);
                    } else if ("a".equals(parser.getName())) {
                        String a = parser.nextText();
                        exercisesInfo.setA(a);
                    } else if ("b".equals(parser.getName())) {
                        String b = parser.nextText();
                        exercisesInfo.setB(b);
                    } else if ("c".equals(parser.getName())) {
                        String c = parser.nextText();
                        exercisesInfo.setC(c);
                    } else if ("d".equals(parser.getName())) {
                        String d = parser.nextText();
                        exercisesInfo.setD(d);
                    } else if ("answer".equals(parser.getName())) {
                        String answer = parser.nextText();
                        exercisesInfo.setAnswer(Integer.parseInt(answer));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())) {
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesInfos;
    }

    public static void setABCDEnable(boolean value, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
        iv_a.setEnabled(value);
        iv_b.setEnabled(value);
        iv_c.setEnabled(value);
        iv_d.setEnabled(value);
    }

    public static List<List<CourseBean>> getCourseInfos(InputStream is) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<List<CourseBean>> courseInfos = null;
        List<CourseBean> courseList = null;
        CourseBean courseInfo = null;
        int count = 0;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        courseInfos = new ArrayList<List<CourseBean>>();
                        courseList = new ArrayList<>();
                    } else if ("course".equals(parser.getName())) {
                        courseInfo = new CourseBean();
                        String ids = parser.getAttributeValue(0);
                        courseInfo.setId(Integer.parseInt(ids));
                    } else if ("imgtitle".equals(parser.getName())) {
                        String imgtitle = parser.nextText();
                        courseInfo.setImgTitle(imgtitle);
                    } else if ("title".equals(parser.getName())) {
                        String title = parser.nextText();
                        courseInfo.setTitle(title);
                    } else if ("intro".equals(parser.getName())) {
                        String intro = parser.nextText();
                        courseInfo.setIntro(intro);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("course".equals(parser.getName())) {
                        count++;
                        courseList.add(courseInfo);
                        if (count % 2 == 0) {
                            // 课程界面每两个数据是一组放在 List 集合中
                            courseInfos.add(courseList);
                            courseList = null;
                            courseList = new ArrayList<>();
                        }
                        courseInfo = null;
                    }
                    break;

            }
            type = parser.next();
        }
        return courseInfos;
    }
}
