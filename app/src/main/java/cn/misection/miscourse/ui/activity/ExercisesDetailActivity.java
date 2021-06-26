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
import cn.misection.miscourse.constant.ui.EnumDefaultValue;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
import cn.misection.miscourse.constant.ui.EnumViewParam;
import cn.misection.miscourse.ui.adapter.ExercisesDetailAdapter;
import cn.misection.miscourse.entity.ExerciseBean;
import cn.misection.miscourse.constant.global.EnumExercise;
import cn.misection.miscourse.util.AnalysisUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ExercisesDetailActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initParam();
        initData();
        initView();
    }

    /**
     * 获取另一个activity传参;
     */
    private void initParam() {
        this.setContentView(R.layout.activity_exercises_detail);
        // 设置此界面为竖屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 获取从习题界面传递过来的章节 id
        id = this.getIntent().getIntExtra(
                EnumExtraParam.ID.literal(),
                EnumDefaultValue.INSTANCE.intVal());
        // 获取从习题界面传递过来的章节标题
        title = this.getIntent().getStringExtra(EnumExtraParam.TITLE.literal());
        exerciseList = new ArrayList<>();
    }

    private void initView() {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        backTextView = findViewById(R.id.back_text_view);
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30B4FF"));
        listView = findViewById(R.id.list_view);

        initTextView();

        mainTitleTextView.setText(title);
        backTextView.setOnClickListener((View v) ->
                ExercisesDetailActivity.this.finish());
        initAdapter();
        adapter.putExerciseList(exerciseList);
        listView.setAdapter(adapter);
    }

    private void initTextView() {
        TextView textView = new TextView(this);
        EnumViewParam viewParam = EnumViewParam.EXERCISE_DETAIL_TEXT_VIEW;
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(viewParam.getTextSize());
        textView.setText(viewParam.getText());
        textView.setPadding(
                viewParam.left(),
                viewParam.top(),
                viewParam.right(),
                viewParam.bottom());
        listView.addHeaderView(textView);
    }

    private void initAdapter() {
        adapter = new ExercisesDetailAdapter(
                ExercisesDetailActivity.this,
                (int position,
                 EnumExercise userSelected,
                 ImageView[] imageViewArray) ->
                {
                    // 先把所有的设置为错误;
                    imageViewArray
                            [userSelected.ordinal()]
                            .setImageResource(
                                    R.drawable.exercise_error_icon);
                    // 正确答案的设为正确img;
                    imageViewArray
                            [exerciseList
                            .get(position)
                            .getCorrectAnswer()
                            .ordinal()]
                            .setImageResource(
                                    R.drawable.exercise_right_icon);

                });
    }


    private void initData() {
        try {
            InputStream is = getResources().getAssets().open(String.format("chapter%d.xml", id));
            exerciseList = AnalysisUtil.requireExerciseListInfo(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
