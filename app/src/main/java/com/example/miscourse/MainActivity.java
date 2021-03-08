package com.example.miscourse;

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

import com.example.miscourse.constant.EnumViewCode;
import com.example.miscourse.tools.SPLoginInfo;
import com.example.miscourse.view.CourseView;
import com.example.miscourse.view.ExercisesView;
import com.example.miscourse.view.MineView;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener
{
    /**
     * 页面元素;
     */
    private TextView tvBack;
    private TextView tvMainTitle;
    private TextView tvBottomCourse;
    private TextView tvBottomExercises;
    private TextView tvBottomMine;

    private RelativeLayout rlTitleBar;
    private RelativeLayout rlBottomCourse;
    private RelativeLayout rlBottomExercises;
    private RelativeLayout rlBottomMine;

    private ImageView ivBottomCourse;
    private ImageView ivBottomExercises;
    private ImageView ivBottomMine;

    private FrameLayout flBody;

    private LinearLayout llBottomBar;

    /**
     * 三个view;
     */
    private MineView mineView;
    private ExercisesView exercisesView;
    private CourseView courseView;

    private SPLoginInfo spLoginInfo;

    /**
     * 上次按下退出键时间;
     */
    protected long lastRecentlyExitPressTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        selectDisplayView(EnumViewCode.COURSE);
    }

    private void init()
    {
        tvMainTitle = findViewById(R.id.tv_main_title);
        // TODO 放 string.xml 资源里;
        tvMainTitle.setText("MI6 线上课堂");
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
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bottom_bar_course:
            {
                clearBottomState();
                selectDisplayView(EnumViewCode.COURSE);
                break;
            }
            case R.id.bottom_bar_exercises:
            {
                clearBottomState();
                selectDisplayView(EnumViewCode.EXERCISES);
                break;
            }
            case R.id.bottom_bar_mine:
            {
                clearBottomState();
                selectDisplayView(EnumViewCode.MINE);
                break;
            }
            default:
            {
                throw new IllegalArgumentException("illegal arg!");
            }
        }
    }

    private void selectDisplayView(EnumViewCode viewCode)
    {
        removeAllView();
        createView(viewCode);
        updateSelectStatus(viewCode);
    }

    private void updateSelectStatus(EnumViewCode index)
    {
        switch (index)
        {
            case COURSE:
            {
                rlBottomCourse.setSelected(true);
                ivBottomCourse.setImageResource(R.drawable.main_course_icon_selected);
                tvBottomCourse.setTextColor(Color.parseColor("#0097f7"));
                rlTitleBar.setVisibility(View.VISIBLE);
                // FIXME;
                tvMainTitle.setText("MI6 在线课堂");
                break;
            }
            case EXERCISES:
            {
                rlBottomExercises.setSelected(true);
                ivBottomExercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tvBottomExercises.setTextColor(Color.parseColor("#0097f7"));
                rlTitleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("MI6 习题");
                break;
            }
            case MINE:
            {
                rlBottomMine.setSelected(true);
                ivBottomMine.setImageResource(R.drawable.main_my_icon_selected);
                tvBottomMine.setTextColor(Color.parseColor("#0097f7"));
                rlTitleBar.setVisibility(View.GONE);
                break;
            }
            default:
            {
                throw new IllegalArgumentException("illegal arg!");
            }
        }
    }

    private void createView(EnumViewCode viewCode)
    {
        switch (viewCode)
        {
            case COURSE:
            {
                turnToCourseView();
                break;
            }
            case EXERCISES:
            {
                turnToExerciseView();
                break;
            }
            case MINE:
            {
                turnToMineView();
                break;
            }
            default:
            {
                throw new IllegalArgumentException("illegal arg");
            }
        }
    }

    private void turnToCourseView()
    {
        if (courseView == null)
        {
            courseView = CourseView.requireInstance(this);
            View view = courseView.getView();
            if (flBody.indexOfChild(view) != -1)
            {
                flBody.addView(view);
            }
        }
        courseView.showView();
    }

    private void turnToExerciseView()
    {
        if (exercisesView == null)
        {
            exercisesView = ExercisesView.requireInstance(this);
            View view = exercisesView.getView();
            if (flBody.indexOfChild(view) != -1)
            {
                flBody.addView(view);
            }
        }
        exercisesView.showView();
    }

    private void turnToMineView()
    {
        if (courseView == null)
        {
            mineView = MineView.requireInstance(this);
            View view = mineView.getView();
            if (flBody.indexOfChild(view) != -1)
            {
                flBody.addView(view);
            }
        }
        mineView.showView();
    }

    private void removeAllView()
    {
        for (int i = 0; i < flBody.getChildCount(); i++)
        {
            flBody.getChildAt(i).setVisibility(View.GONE);
        }
    }

    private void clearBottomState()
    {
        ivBottomCourse.setImageResource(R.drawable.main_course_icon);
        ivBottomExercises.setImageResource(R.drawable.main_exercises_icon);
        ivBottomMine.setImageResource(R.drawable.main_my_icon);

        tvBottomCourse.setTextColor(Color.parseColor("#666666"));
        tvBottomExercises.setTextColor(Color.parseColor("#666666"));
        tvBottomMine.setTextColor(Color.parseColor("#666666"));

        for (int i = 0; i < llBottomBar.getChildCount(); i++)
        {
            llBottomBar.getChildAt(i).setSelected(false);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            long timeMillis = System.currentTimeMillis();
            if (timeMillis - lastRecentlyExitPressTime > 2000)
            {
                Toast.makeText(this, "再按一次退出博学谷", Toast.LENGTH_SHORT).show();
                lastRecentlyExitPressTime = timeMillis;
            }
            else
            {
                MainActivity.this.finish();
                if (spLoginInfo.getLoginStatus())
                {
                    spLoginInfo.saveLoginStatus(false, "");
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            boolean loginFlag = data.getBooleanExtra("isLogin", false);
            if (loginFlag)
            {
                clearBottomState();
                updateSelectStatus(EnumViewCode.COURSE);
            }
            if (mineView != null)
            {
                mineView.setLoginParams(loginFlag);
            }
        }
    }
}
