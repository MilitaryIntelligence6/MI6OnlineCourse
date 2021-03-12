package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.ui.adapter.ExercisesDetailAdapter;
import cn.misection.miscourse.entity.ExerciseBean;
import cn.misection.miscourse.constant.EnumExerciseChoice;
import cn.misection.miscourse.constant.EnumExercise;
import cn.misection.miscourse.util.AnalysisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ExercisesDetailActivity extends AppCompatActivity
{
    private TextView mainTitleTextView;

    private TextView backTextView;

    private RelativeLayout titleBarRelaLayout;

    private ListView listView;

    private String title;

    private int id;

    private List<ExerciseBean> exerciseList;

    private ExercisesDetailAdapter adapter;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 获取从习题界面传递过来的章节 id
        id = getIntent().getIntExtra("id", 0);
        // 获取从习题界面传递过来的章节标题
        title = getIntent().getStringExtra("title");
        exerciseList = new ArrayList<>();
        initData();
        init();
    }

    private void init()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        backTextView = findViewById(R.id.back_text_view);
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30B4FF"));
        listView = findViewById(R.id.lv_list);
        TextView textView = new TextView(this);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(16.0f);
        textView.setText("一、选择题");
        textView.setPadding(10, 15, 0, 0);
        listView.addHeaderView(textView);
        mainTitleTextView.setText(title);
        backTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ExercisesDetailActivity.this.finish();
            }
        });
        initAdapter();
        adapter.putExerciseList(exerciseList);
        listView.setAdapter(adapter);
    }

    private void initAdapter()
    {
        adapter = new ExercisesDetailAdapter(
                ExercisesDetailActivity.this,
                new ExercisesDetailAdapter.OnSelectListener()
                {
                    @Override
                    public void onSelect(int position, ImageView... imageViewArray)
                    {
                        // 判断如果答案不是 1 即 A 选项
                        if (exerciseList.get(position).getCorrectAnswer() != EnumExercise.A)
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.A_WRONG);
                        }
                        else
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.CORRECT);
                        }
                        // hash 表;
                        switch (exerciseList.get(position).getCorrectAnswer())
                        {
                            case A:
                                imageViewArray[0].setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case B:
                                imageViewArray[1].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[0].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case C:
                                imageViewArray[2].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[0].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case D:
                                imageViewArray[3].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[0].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            default:
                            {
                                break;
                            }
                        }
                        AnalysisUtil.setChoiceEnable(false, imageViewArray);
                    }

                    @Override
                    public void onSelectB(int position, ImageView... imageViewArray)
                    {
                        // 判断如果答案不是 2 即 B 选项
                        if (exerciseList.get(position).getCorrectAnswer() != EnumExercise.B)
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.B_WRONG);
                        }
                        else
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.CORRECT);
                        }
                        switch (exerciseList.get(position).getCorrectAnswer())
                        {
                            case A:
                                imageViewArray[0].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[1].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case B:
                                imageViewArray[1].setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case C:
                                imageViewArray[2].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[1].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case D:
                                imageViewArray[3].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[1].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            default:
                            {

                            }
                        }
                        AnalysisUtil.setChoiceEnable(false, imageViewArray);
                    }

                    @Override
                    public void onSelectC(int position, ImageView... imageViewArray)
                    {
                        // 判断如果答案不是 3 即 C 选项
                        if (exerciseList.get(position).getCorrectAnswer() != EnumExercise.C)
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.C_WRONG);
                        }
                        else
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.CORRECT);
                        }
                        switch (exerciseList.get(position).getCorrectAnswer())
                        {
                            case A:
                                imageViewArray[0].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[2].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case B:
                                imageViewArray[1].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[2].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case C:
                                imageViewArray[2].setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case D:
                                imageViewArray[3].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[2].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            default:
                            {
                                break;
                            }
                        }
                        AnalysisUtil.setChoiceEnable(false, imageViewArray);
                    }

                    @Override
                    public void onSelectD(int position,
                                          ImageView... imageViewArray)
                    {
                        // 判断如果答案不是 4 即 D 选项
                        if (exerciseList.get(position).getCorrectAnswer() != EnumExercise.D)
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.D_WRONG);
                        }
                        else
                        {
                            exerciseList.get(position).setUserSelect(EnumExerciseChoice.CORRECT);
                        }
                        switch (exerciseList.get(position).getCorrectAnswer())
                        {
                            case A:
                                imageViewArray[0].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[3].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case B:
                                imageViewArray[1].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[3].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case C:
                                imageViewArray[2].setImageResource(R.drawable.exercises_right_icon);
                                imageViewArray[3].setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case D:
                                imageViewArray[3].setImageResource(R.drawable.exercises_right_icon);
                                break;
                            default:
                            {
                                break;
                            }
                        }
                        AnalysisUtil.setChoiceEnable(false, imageViewArray);
                    }
                });
    }

    private void initData()
    {
        try
        {
            InputStream is = getResources().getAssets().open(String.format("chapter%d.xml", id));
            exerciseList = AnalysisUtil.requireExerciseListInfo(is);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
