package com.misection.miscourse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.misection.miscourse.tools.SPLoginInfo;
import com.misection.miscourse.view.CourseView;
import com.misection.miscourse.view.ExercisesView;
import com.misection.miscourse.view.MineView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBack, tvMainTitle, tvBottomCourse, tvBottomExercises, tvBottomMine;
    private RelativeLayout rlTitleBar, rlBottomCourse, rlBottomExercises, rlBottomMine;
    private ImageView ivBottomCourse, ivBottomExercises, ivBottomMine;
    private FrameLayout flBody;
    private LinearLayout llBottomBar;
    SPLoginInfo spLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        selectDisplayView(0);
    }

    private void init() {
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvMainTitle.setText("博学谷课程");
        tvBack = findViewById(R.id.tV_back);
        tvBack.setVisibility(View.GONE);
        rlTitleBar = findViewById(R.id.title_bar);
        rlTitleBar.setBackgroundColor(Color.parseColor("#30B4FF"));

        flBody = findViewById(R.id.fl_body);
        llBottomBar = findViewById(R.id.bottom_bar);

        rlBottomCourse = findViewById(R.id.bottom_bar_course);
        rlBottomCourse.setOnClickListener(this);
        ivBottomCourse = findViewById(R.id.bottom_bar_image_course);
        tvBottomCourse = findViewById(R.id.bottom_bar_text_course);

        rlBottomExercises = findViewById(R.id.bottom_bar_exercises);
        rlBottomExercises.setOnClickListener(this);
        ivBottomExercises = findViewById(R.id.bottom_bar_image_exercises);
        tvBottomExercises = findViewById(R.id.bottom_bar_text_exercises);

        rlBottomMine = findViewById(R.id.bottom_bar_mine);
        rlBottomMine.setOnClickListener(this);
        ivBottomMine = findViewById(R.id.bottom_bar_image_mine);
        tvBottomMine = findViewById(R.id.bottom_bar_text_mine);

        spLoginInfo = new SPLoginInfo(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_bar_course:
                clearBottomState();
                selectDisplayView(0);
                break;
            case R.id.bottom_bar_exercises:
                clearBottomState();
                selectDisplayView(1);
                break;
            case R.id.bottom_bar_mine:
                clearBottomState();
                selectDisplayView(2);
                break;
        }
    }

    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectStatus(index);
    }

    private void setSelectStatus(int index) {
        switch (index) {
            case 0:
                rlBottomCourse.setSelected(true);
                ivBottomCourse.setImageResource(R.drawable.main_course_icon_selected);
                tvBottomCourse.setTextColor(Color.parseColor("#0097f7"));
                rlTitleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("博学谷课程");
                break;
            case 1:
                rlBottomExercises.setSelected(true);
                ivBottomExercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tvBottomExercises.setTextColor(Color.parseColor("#0097f7"));
                rlTitleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("博学谷习题");
                break;
            case 2:
                rlBottomMine.setSelected(true);
                ivBottomMine.setImageResource(R.drawable.main_my_icon_selected);
                tvBottomMine.setTextColor(Color.parseColor("#0097f7"));
                rlTitleBar.setVisibility(View.GONE);
                break;
        }
    }

    private MineView mineView;
    private ExercisesView exercisesView;
    private CourseView courseView;

    private void createView(int viewIndex) {
        switch (viewIndex) {
            case 0:  // 课程界面
                if (courseView == null) {
                    courseView = new CourseView(this);
                    flBody.addView(courseView.getView());
                } else {
                    courseView.getView();
                }
                courseView.showView();
                break;
            case 1:  // 习题界面
                if (exercisesView == null) {
                    exercisesView = new ExercisesView(this);
                    flBody.addView(exercisesView.getView());
                } else {
                    exercisesView.getView();
                }
                exercisesView.showView();
                break;
            case 2:  // 我界面
                if (mineView == null) {
                    mineView = new MineView(this);
                    flBody.addView(mineView.getView());
                } else {
                    mineView.getView();
                }
                mineView.showView();
                break;
        }
    }

    private void removeAllView() {
        for (int i = 0; i < flBody.getChildCount(); i++) {
            flBody.getChildAt(i).setVisibility(View.GONE);
        }
    }

    private void clearBottomState() {
        ivBottomCourse.setImageResource(R.drawable.main_course_icon);
        ivBottomExercises.setImageResource(R.drawable.main_exercises_icon);
        ivBottomMine.setImageResource(R.drawable.main_my_icon);

        tvBottomCourse.setTextColor(Color.parseColor("#666666"));
        tvBottomExercises.setTextColor(Color.parseColor("#666666"));
        tvBottomMine.setTextColor(Color.parseColor("#666666"));

        for (int i = 0; i < llBottomBar.getChildCount(); i++) {
            llBottomBar.getChildAt(i).setSelected(false);
        }
    }

    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出博学谷", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                if (spLoginInfo.getLoginStatus()) {
                    // 清除用户登录状态
                    spLoginInfo.saveLoginStatus(false, "");
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            boolean isLogin = data.getBooleanExtra("isLogin", false);
            if (isLogin) {
                clearBottomState();
                setSelectStatus(0);
            }
            if (mineView != null) {
                mineView.setLoginParams(isLogin);
            }
        }
    }
}
