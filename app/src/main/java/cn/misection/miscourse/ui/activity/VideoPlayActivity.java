package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.ToastUtil;

public class VideoPlayActivity extends AppCompatActivity
{
    private VideoView videoView;
    private MediaController controller;
    private String videoPath;  // 本地视频地址
    private int position;  // 传递视频详情界面点击的视频位置

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        // 设置界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置此界面为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 获取从播放记录界面传递过来的视频地址
        videoPath = getIntent().getStringExtra("videoPath");
        position = getIntent().getIntExtra("position", 0);
        init();
    }

    private void init()
    {
        videoView = findViewById(R.id.video_view);
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }

    /**
     * 播放视频;
     */
    private void play()
    {
        if (TextUtils.isEmpty(videoPath))
        {
//            Toast.makeText(this, "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
            ToastUtil.show(this, "本地没有此视频，暂无法播放");
            return;
        }
        String url = String.format("android.resource://%s/%d", getPackageName(), R.raw.video11);
        videoView.setVideoPath(url);
        videoView.start();
    }

    /**
     * 点击后退按钮;
     *
     * @param keyCode keyCode;
     * @param event   event;
     * @return succ ?;
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // 把视频详情界面传递过来的被点击视频的位置传递回去
        Intent data = new Intent();
        data.putExtra("position", position);
        setResult(RESULT_OK, data);
        return super.onKeyDown(keyCode, event);
    }
}
