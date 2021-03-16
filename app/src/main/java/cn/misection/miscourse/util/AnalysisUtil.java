package cn.misection.miscourse.util;

import android.util.Xml;
import android.widget.ImageView;

import cn.misection.miscourse.entity.CourseBean;
import cn.misection.miscourse.entity.ExerciseBean;
import cn.misection.miscourse.constant.global.EnumExercise;
import cn.misection.miscourse.util.utilconst.ParserConst;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @FIXME
 */
public class AnalysisUtil
{
    public static List<ExerciseBean> requireExerciseListInfo(InputStream is) throws Exception
    {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExerciseBean> exercisesInfoList = null;
        ExerciseBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT)
        {
            switch (type)
            {
                case XmlPullParser.START_TAG:
                {
                    String name = parser.getName();
                    switch (name)
                    {
                        case ParserConst.EXERCISE_INFO:
                        {
                            exercisesInfoList = new ArrayList<>();
                            break;
                        }
                        case ParserConst.EXERCISE_BEAN:
                        {
                            exercisesInfo = new ExerciseBean();
                            exercisesInfo.setOptionTextArray(new String[4]);
                            String ids = parser.getAttributeValue(0);
                            exercisesInfo.setSubjectId(Integer.parseInt(ids));
                            break;
                        }
                        case ParserConst.EXERCISE_SUBJECT:
                        {
                            String subject = parser.nextText();
                            exercisesInfo.setSubject(subject);
                            break;
                        }
                        case ParserConst.EXERCISE_CORRECT_ANSWER:
                        {
                            String answer = parser.nextText();
                            // FIXME, 对了, 尽量更优雅;
                            exercisesInfo.setCorrectAnswer(
                                    EnumExercise.valueOf(Integer.parseInt(answer) - 1)
                            );
                            break;
                        }
                        default:
                        {
                            if (EnumExercise.containsChoice(name))
                            {
                                exercisesInfo.getOptionTextArray()[
                                        EnumExercise.selectEnumByLowerCase(name).ordinal()]
                                        = parser.nextText();
                            }
                            break;
                        }
                    }
                    break;
                }
                case XmlPullParser.END_TAG:
                {
                    if (parser.getName().equals(ParserConst.EXERCISE_BEAN))
                    {
                        exercisesInfoList.add(exercisesInfo);
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
        return exercisesInfoList;
    }

    public static void setChoiceEnable(boolean value,
                                       ImageView... imageViewArray)
    {
        for (ImageView imageView : imageViewArray)
        {
            imageView.setEnabled(value);
        }
    }

    public static List<List<CourseBean>> requireCourseInfo(InputStream is)
            throws XmlPullParserException, IOException
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
                        case ParserConst.EXERCISE_INFO:
                        {
                            courseInfos = new ArrayList<List<CourseBean>>();
                            courseList = new ArrayList<>();
                            break;
                        }
                        case ParserConst.EXERCISE_COURSE:
                        {
                            courseInfo = new CourseBean();
                            String ids = parser.getAttributeValue(0);
                            courseInfo.setId(Integer.parseInt(ids));
                            break;
                        }
                        case ParserConst.EXERCISE_IMAGE_TITLE:
                        {
                            String imgtitle = parser.nextText();
                            courseInfo.setImgTitle(imgtitle);
                            break;
                        }
                        case ParserConst.EXERCISE_TITLE:
                        {
                            String title = parser.nextText();
                            courseInfo.setTitle(title);
                            break;
                        }
                        case ParserConst.EXERCISE_INTRO:
                        {
                            String intro = parser.nextText();
                            courseInfo.setIntro(intro);
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (parser.getName())
                    {
                        case ParserConst.EXERCISE_COURSE:
                        {
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
                        }
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
