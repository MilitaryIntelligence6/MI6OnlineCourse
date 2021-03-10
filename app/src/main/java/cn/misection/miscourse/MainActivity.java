package cn.misection.miscourse;

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

import cn.misection.miscourse.opcode.EnumViewCode;
import cn.misection.miscourse.presenter.CoursePresenter;
import cn.misection.miscourse.presenter.MinePresenter;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.view.ExercisesViewManager;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener
{
    /**
     * 页面元素;
     */
    private TextView backTextView;
    private TextView mainTitleTextView;
    private TextView bottomCourseTextView;
    private TextView bottomExercisesTextView;
    private TextView bottomMineTextView;

    private RelativeLayout titleBarRelaLayout;
    private RelativeLayout bottomCourseRelaLayout;
    private RelativeLayout bottomExercisesRelaLayout;
    private RelativeLayout bottomMineRelaLayout;

    private ImageView bottomCourseImageView;
    private ImageView bottomExercisesImageView;
    private ImageView bottomMineImageView;

    private FrameLayout bodyFrameLayout;

    private LinearLayout bottomBarLinearLayout;

    /**
     * 三个子view;
     */
    private MinePresenter minePresenter;
    private ExercisesViewManager exercisesViewManager;
//    private CourseViewManager courseViewManager;
    private CoursePresenter coursePresenter;

    private SharedPreferLoginInfo sharedPrefLoginInfo;

    /**
     * 上次按下退出键时间;
     */
    protected long lastRecentlyPressExitTime;

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
        mainTitleTextView = findViewById(R.id.tv_main_title);
        // TODO 放 string.xml 资源里;
        mainTitleTextView.setText(R.string.course_name);
        backTextView = findViewById(R.id.text_view_back);
        backTextView.setVisibility(View.GONE);
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30B4FF"));

        bodyFrameLayout = findViewById(R.id.body_frame_layout);
        bottomBarLinearLayout = findViewById(R.id.bottom_bar);

        bottomCourseRelaLayout = findViewById(R.id.bottom_bar_course);
        bottomCourseRelaLayout.setOnClickListener(this);
        bottomCourseImageView = findViewById(R.id.bottom_bar_image_course);
        bottomCourseTextView = findViewById(R.id.bottom_bar_text_course);

        bottomExercisesRelaLayout = findViewById(R.id.bottom_bar_exercises);
        bottomExercisesRelaLayout.setOnClickListener(this);
        bottomExercisesImageView = findViewById(R.id.bottom_bar_image_exercises);
        bottomExercisesTextView = findViewById(R.id.bottom_bar_text_exercises);

        bottomMineRelaLayout = findViewById(R.id.bottom_bar_mine);
        bottomMineRelaLayout.setOnClickListener(this);
        bottomMineImageView = findViewById(R.id.bottom_bar_image_mine);
        bottomMineTextView = findViewById(R.id.bottom_bar_text_mine);

        minePresenter = MinePresenter.requireInstance(this);
        coursePresenter = CoursePresenter.requireInstance(this);

        bodyFrameLayout.addView(minePresenter.requireView());
        bodyFrameLayout.addView(coursePresenter.requireView());

        sharedPrefLoginInfo = new SharedPreferLoginInfo(MainActivity.this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
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
                bottomCourseRelaLayout.setSelected(true);
                bottomCourseImageView.setImageResource(R.drawable.main_course_icon_selected);
                bottomCourseTextView.setTextColor(Color.parseColor("#0097f7"));
                titleBarRelaLayout.setVisibility(View.VISIBLE);
                // FIXME;
                mainTitleTextView.setText(R.string.course_name);
                break;
            }
            case EXERCISES:
            {
                bottomExercisesRelaLayout.setSelected(true);
                bottomExercisesImageView.setImageResource(R.drawable.main_exercises_icon_selected);
                bottomExercisesTextView.setTextColor(Color.parseColor("#0097f7"));
                titleBarRelaLayout.setVisibility(View.VISIBLE);
                mainTitleTextView.setText(R.string.exercise_name);
                break;
            }
            case MINE:
            {
                bottomMineRelaLayout.setSelected(true);
                bottomMineImageView.setImageResource(R.drawable.main_my_icon_selected);
                bottomMineTextView.setTextColor(Color.parseColor("#0097f7"));
                titleBarRelaLayout.setVisibility(View.GONE);
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
        coursePresenter.showView();
    }

    private void turnToExerciseView()
    {
        if (exercisesViewManager == null)
        {
            exercisesViewManager = ExercisesViewManager.requireInstance(this);
            View view = exercisesViewManager.view();
            bodyFrameLayout.addView(view);
        }
        exercisesViewManager.show();
    }

    private void turnToMineView()
    {
        minePresenter.showView();
    }

    private void removeAllView()
    {
        for (int i = 0; i < bodyFrameLayout.getChildCount(); i++)
        {
            bodyFrameLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    private void clearBottomState()
    {
        bottomCourseImageView.setImageResource(R.drawable.main_course_icon);
        bottomExercisesImageView.setImageResource(R.drawable.main_exercises_icon);
        bottomMineImageView.setImageResource(R.drawable.main_my_icon);

        bottomCourseTextView.setTextColor(Color.parseColor("#666666"));
        bottomExercisesTextView.setTextColor(Color.parseColor("#666666"));
        bottomMineTextView.setTextColor(Color.parseColor("#666666"));

        for (int i = 0; i < bottomBarLinearLayout.getChildCount(); i++)
        {
            bottomBarLinearLayout.getChildAt(i).setSelected(false);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            long timeMillis = System.currentTimeMillis();
            if (timeMillis - lastRecentlyPressExitTime > 2000)
            {
                Toast.makeText(this, R.string.exit_again, Toast.LENGTH_SHORT).show();
                lastRecentlyPressExitTime = timeMillis;
            }
            else
            {
                MainActivity.this.finish();
                if (sharedPrefLoginInfo.hasLogin())
                {
                    sharedPrefLoginInfo.saveLoginStatus(false, "");
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
            if (minePresenter != null)
            {
                minePresenter.putLoginParams(loginFlag);
            }
        }
    }
}
