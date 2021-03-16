package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import cn.misection.miscourse.MainActivity;
import cn.misection.miscourse.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Administrator
 */
public class LoadActivity extends AppCompatActivity
{
    private static final long LOADING_TIME = 3000;

    private TextView versionTextView;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);
        // 设置界面为竖屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
    }

    private void initView()
    {
        // 显示应用版本号
        versionTextView = findViewById(R.id.version_text_view);
        try
        {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionTextView.setText(String.format("v %s", packageInfo.versionName));
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            versionTextView.setText(R.string.version);
        }

        // FIXME;
        // 三秒后跳转到主页面
        Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(intent);
                LoadActivity.this.finish();
            }
        };
        timer.schedule(timerTask, LOADING_TIME);
    }
}
