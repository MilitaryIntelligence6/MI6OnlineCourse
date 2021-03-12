package cn.misection.miscourse.activity;

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
import cn.misection.miscourse.adapter.ExercisesDetailAdapter;
import cn.misection.miscourse.bean.ExercisesBean;
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
    private TextView MainTitleTextView, backTextView;
    private RelativeLayout titleBarRelaLayout;
    private ListView listView;
    private String title;
    private int id;
    private List<ExercisesBean> beanList;
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
        beanList = new ArrayList<>();
        initData();
        init();
    }

    private void init()
    {
        MainTitleTextView = findViewById(R.id.tv_main_title);
        backTextView = findViewById(R.id.text_view_back);
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30B4FF"));
        listView = findViewById(R.id.lv_list);
        TextView textView = new TextView(this);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(16.0f);
        textView.setText("一、选择题");
        textView.setPadding(10, 15, 0, 0);
        listView.addHeaderView(textView);
        MainTitleTextView.setText(title);
        backTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ExercisesDetailActivity.this.finish();
            }
        });
        adapter = new ExercisesDetailAdapter(ExercisesDetailActivity.this, new ExercisesDetailAdapter.OnSelectListener()
        {
            @Override
            public void onSelectA(int position, ImageView imageViewA, ImageView imageViewB, ImageView imageViewC, ImageView imageViewD)
            {
                // 判断如果答案不是 1 即 A 选项
                if (beanList.get(position).getAnswer() != 1)
                {
                    beanList.get(position).setSelect(1);
                }
                else
                {
                    beanList.get(position).setSelect(0);
                }
                // hash 表;
                switch (beanList.get(position).getAnswer())
                {
                    case 1:
                        imageViewA.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 2:
                        imageViewB.setImageResource(R.drawable.exercises_right_icon);
                        imageViewA.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        imageViewC.setImageResource(R.drawable.exercises_right_icon);
                        imageViewA.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        imageViewD.setImageResource(R.drawable.exercises_right_icon);
                        imageViewA.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    default:
                    {
                        break;
                    }
                }
                AnalysisUtil.setABCDEnable(false, imageViewA, imageViewB, imageViewC, imageViewD);
            }

            @Override
            public void onSelectB(int position, ImageView imageViewA, ImageView imageViewB, ImageView imageViewC, ImageView imageViewD)
            {
                // 判断如果答案不是 2 即 B 选项
                if (beanList.get(position).getAnswer() != 2)
                {
                    beanList.get(position).setSelect(2);
                }
                else
                {
                    beanList.get(position).setSelect(0);
                }
                switch (beanList.get(position).getAnswer())
                {
                    case 1:
                        imageViewA.setImageResource(R.drawable.exercises_right_icon);
                        imageViewB.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        imageViewB.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        imageViewC.setImageResource(R.drawable.exercises_right_icon);
                        imageViewB.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        imageViewD.setImageResource(R.drawable.exercises_right_icon);
                        imageViewB.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    default:
                    {

                    }
                }
                AnalysisUtil.setABCDEnable(false, imageViewA, imageViewB, imageViewC, imageViewD);
            }

            @Override
            public void onSelectC(int position,
                                  ImageView imageViewA,
                                  ImageView imageViewB,
                                  ImageView imageViewC,
                                  ImageView imageViewD)
            {
                // 判断如果答案不是 3 即 C 选项
                if (beanList.get(position).getAnswer() != 3)
                {
                    beanList.get(position).setSelect(3);
                }
                else
                {
                    beanList.get(position).setSelect(0);
                }
                switch (beanList.get(position).getAnswer())
                {
                    case 1:
                        imageViewA.setImageResource(R.drawable.exercises_right_icon);
                        imageViewC.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        imageViewB.setImageResource(R.drawable.exercises_right_icon);
                        imageViewC.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        imageViewC.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        imageViewD.setImageResource(R.drawable.exercises_right_icon);
                        imageViewC.setImageResource(R.drawable.exercises_error_icon);
                        break;
                }
                AnalysisUtil.setABCDEnable(false, imageViewA, imageViewB, imageViewC, imageViewD);
            }

            @Override
            public void onSelectD(int position,
                                  ImageView imageViewA,
                                  ImageView imageViewB,
                                  ImageView imageViewC,
                                  ImageView imageViewD)
            {
                // 判断如果答案不是 4 即 D 选项
                if (beanList.get(position).getAnswer() != 4)
                {
                    beanList.get(position).setSelect(4);
                }
                else
                {
                    beanList.get(position).setSelect(0);
                }
                switch (beanList.get(position).getAnswer())
                {
                    case 1:
                        imageViewA.setImageResource(R.drawable.exercises_right_icon);
                        imageViewD.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        imageViewB.setImageResource(R.drawable.exercises_right_icon);
                        imageViewD.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        imageViewC.setImageResource(R.drawable.exercises_right_icon);
                        imageViewD.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        imageViewD.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtil.setABCDEnable(false, imageViewA, imageViewB, imageViewC, imageViewD);
            }
        });
        adapter.setData(beanList);
        listView.setAdapter(adapter);
    }

    private void initData()
    {
        try
        {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            beanList = AnalysisUtil.getExercisesInfos(is);
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
