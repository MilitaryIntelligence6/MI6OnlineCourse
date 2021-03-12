package cn.misection.miscourse.util;

import android.util.Xml;
import android.widget.ImageView;

import cn.misection.miscourse.bean.CourseBean;
import cn.misection.miscourse.bean.ExerciseBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AnalysisUtil
{
    public static List<ExerciseBean> getExercisesInfos(InputStream is) throws Exception
    {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExerciseBean> exercisesInfos = null;
        ExerciseBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT)
        {
            switch (type)
            {
                case XmlPullParser.START_TAG:
                {
                    switch (parser.getName())
                    {
                        case "infos":
                            exercisesInfos = new ArrayList<>();
                            break;
                        case "exercises":
                            exercisesInfo = new ExerciseBean();
                            String ids = parser.getAttributeValue(0);
                            exercisesInfo.setSubjectId(Integer.parseInt(ids));
                            break;
                        case "subject":
                            String subject = parser.nextText();
                            exercisesInfo.setSubject(subject);
                            break;
                        case "a":
                            String a = parser.nextText();
                            exercisesInfo.setOptionTextA(a);
                            break;
                        case "b":
                            String b = parser.nextText();
                            exercisesInfo.setOptionTextB(b);
                            break;
                        case "c":
                            String c = parser.nextText();
                            exercisesInfo.setOptionTextC(c);
                            break;
                        case "d":
                            String d = parser.nextText();
                            exercisesInfo.setOptionTextD(d);
                            break;
                        case "answer":
                            String answer = parser.nextText();
                            exercisesInfo.setAnswer(Integer.parseInt(answer));
                            break;
                        default:
                        {
                            break;
                        }
                    }
                    break;
                }
                case XmlPullParser.END_TAG:
                {
                    if ("exercises".equals(parser.getName()))
                    {
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
            type = parser.next();
        }
        return exercisesInfos;
    }

    public static void setABCDEnable(boolean value,
                                     ImageView... imageViewArray)
    {
        for (ImageView imageView : imageViewArray)
        {
            imageView.setEnabled(value);
        }
    }

    public static List<List<CourseBean>> requireCourseInfo(InputStream is) throws XmlPullParserException, IOException
    {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<List<CourseBean>> courseInfos = null;
        List<CourseBean> courseList = null;
        CourseBean courseInfo = null;
        int count = 0;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT)
        {
            switch (type)
            {
                case XmlPullParser.START_TAG:
                    switch (parser.getName())
                    {
                        case "infos":
                            courseInfos = new ArrayList<List<CourseBean>>();
                            courseList = new ArrayList<>();
                            break;
                        case "course":
                            courseInfo = new CourseBean();
                            String ids = parser.getAttributeValue(0);
                            courseInfo.setId(Integer.parseInt(ids));
                            break;
                        case "imgtitle":
                            String imgtitle = parser.nextText();
                            courseInfo.setImgTitle(imgtitle);
                            break;
                        case "title":
                            String title = parser.nextText();
                            courseInfo.setTitle(title);
                            break;
                        case "intro":
                            String intro = parser.nextText();
                            courseInfo.setIntro(intro);
                            break;
                        default:
                        {
                            break;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (parser.getName())
                    {
                        case "course":
                            count++;
                            courseList.add(courseInfo);
                            if (count % 2 == 0)
                            {
                                // 课程界面每两个数据是一组放在 List 集合中
                                courseInfos.add(courseList);
                                courseList = null;
                                courseList = new ArrayList<>();
                            }
                            courseInfo = null;
                            break;

                        default:
                        {
                            break;
                        }
                    }
                    break;

                default:
                {
                    break;
                }

            }
            type = parser.next();
        }
        return courseInfos;
    }
}
