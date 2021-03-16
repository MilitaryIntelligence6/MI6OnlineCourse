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

import cn.misection.miscourse.constant.mvp.EnumViewCode;
import cn.misection.miscourse.mvp.presenter.ICoursePresenter;
import cn.misection.miscourse.mvp.presenter.IExercisesPresenter;
import cn.misection.miscourse.mvp.presenter.IMinePresenter;
import cn.misection.miscourse.mvp.presenter.impl.CoursePresenterImpl;
import cn.misection.miscourse.mvp.presenter.impl.ExercisesPresenterImpl;
import cn.misection.miscourse.mvp.presenter.impl.MinePresenterImpl;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

/**
 * 目前为了代码整洁, 去除了 == null 判断, 都是饿加载,
 * 这样有不好的地方, 但是毕竟是首页;
 * @author Administrator
 */
public class MainActivity
        extends AppCompatActivity implements View.OnClickListener
{
    /*
    TODO
     bottomCourseRelaLayout = findViewById(R.id.bottom_bar_course);
        bottomCourseRelaLayout.setOnClickListener(this);
        bottomCourseImageView = findViewById(R.id.bottom_bar_image_course);
        bottomCourseTextView = findViewById(R.id.bottom_bar_text_course);

        用建造者模式创建之group;

    把这些元素直接放入枚举或者一个类中, 用类似策略模式去写统一的调用;
     */

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

    private RelativeLayout bottomExerciseRelaLayout;

    private RelativeLayout bottomMineRelaLayout;

    private ImageView bottomCourseImageView;

    private ImageView bottomExercisesImageView;

    private ImageView bottomMineImageView;

    private FrameLayout bodyFrameLayout;

    private LinearLayout bottomBarLinearLayout;

    /**
     * 三个子view;
     */
    private IMinePresenter minePresenter;

    private IExercisesPresenter exercisesPresenter;

    private ICoursePresenter coursePresenter;

    private SharedPreferLoginInfo sharedPrefLoginInfo;

    /**
     * 上次按下退出键时间;
     */
    protected long lastRecentlyPressExitTime;

    private static final long EXIT_AGAIN_DELAY = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        initIndex();
        initBottomCourse();
        initBottomExercises();
        initBottomMine();
        initPresenter();
        initSharePref();
        selectDisplayView(EnumViewCode.COURSE);
    }

    private void initIndex()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(R.string.course_name);
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setVisibility(View.GONE);
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30B4FF"));

        bodyFrameLayout = findViewById(R.id.body_frame_layout);
        bottomBarLinearLayout = findViewById(R.id.bottom_bar);
    }

    private void initPresenter()
    {
        minePresenter = MinePresenterImpl.requireInstance(this);
        coursePresenter = CoursePresenterImpl.requireInstance(this);
        exercisesPresenter = ExercisesPresenterImpl.requireInstance(this);

        bodyFrameLayout.addView(minePresenter.requireView());
        bodyFrameLayout.addView(coursePresenter.requireView());
        bodyFrameLayout.addView(exercisesPresenter.requireView());
    }

    private void initBottomCourse()
    {
        bottomCourseRelaLayout = findViewById(R.id.bottom_bar_course);
        bottomCourseRelaLayout.setOnClickListener(this);
        bottomCourseImageView = findViewById(R.id.bottom_bar_image_course);
        bottomCourseTextView = findViewById(R.id.bottom_bar_text_course);
    }

    private void initBottomExercises()
    {
        bottomExerciseRelaLayout = findViewById(R.id.bottom_bar_exercises);
        bottomExerciseRelaLayout.setOnClickListener(this);
        bottomExercisesImageView = findViewById(R.id.bottom_bar_image_exercises);
        bottomExercisesTextView = findViewById(R.id.bottom_bar_text_exercises);
    }

    private void initBottomMine()
    {
        bottomMineRelaLayout = findViewById(R.id.bottom_bar_mine);
        bottomMineRelaLayout.setOnClickListener(this);
        bottomMineImageView = findViewById(R.id.bottom_bar_image_mine);
        bottomMineTextView = findViewById(R.id.bottom_bar_text_mine);
    }

    private void initSharePref()
    {
        sharedPrefLoginInfo = new SharedPreferLoginInfo(MainActivity.this);
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
        hideAllView();
        showView(viewCode);
        updateSelectStatus(viewCode);
    }

    private void updateSelectStatus(EnumViewCode viewCode)
    {
        switch (viewCode)
        {
            case COURSE:
            {
                bottomCourseRelaLayout.setSelected(true);
                bottomCourseImageView.setImageResource(R.drawable.main_course_icon_selected);
                bottomCourseTextView.setTextColor(Color.parseColor("#0097f7"));
                titleBarRelaLayout.setVisibility(View.VISIBLE);
                mainTitleTextView.setText(R.string.course_name);
                break;
            }
            case EXERCISES:
            {
                bottomExerciseRelaLayout.setSelected(true);
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

    private void showView(EnumViewCode viewCode)
    {
        switch (viewCode)
        {
            case COURSE:
            {
                coursePresenter.showView();
                break;
            }
            case EXERCISES:
            {
                exercisesPresenter.showView();
                break;
            }
            case MINE:
            {
                minePresenter.showView();
                break;
            }
            default:
            {
                throw new IllegalArgumentException("illegal arg");
            }
        }
    }

    private void hideAllView()
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
            if (timeMillis - lastRecentlyPressExitTime > EXIT_AGAIN_DELAY)
            {
                ToastUtil.show(this, R.string.exit_again);
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
            // FIXME 这里逻辑没弄清楚, 到底会不会改变其;, 原来是传入该loginFlag;
            minePresenter.showLoginState();
        }
    }
}
