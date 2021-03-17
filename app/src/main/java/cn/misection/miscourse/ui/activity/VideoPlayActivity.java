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
import android.widget.VideoView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.ui.EnumDefaultValue;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
import cn.misection.miscourse.util.ToastUtil;

public class VideoPlayActivity extends AppCompatActivity
{
    private VideoView videoView;

    private MediaController controller;

    /**
     * 本地视频地址;
     */
    private String videoPath;

    /**
     * 传递视频详情界面点击的视频位置;
     */
    private int position;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        initContent();
        initData();
        initView();
        play();
    }

    private void initContent()
    {
        this.setContentView(R.layout.activity_video_play);
        // 设置界面全屏显示
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置此界面为横屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void initData()
    {
        // 获取从播放记录界面传递过来的视频地址
        videoPath = getIntent().getStringExtra(
                EnumExtraParam.VIDEO_PATH.literal());
        position = getIntent().getIntExtra(
                EnumExtraParam.POSITION.literal(),
                EnumDefaultValue.INT_EXTRA.value());
    }

    private void initView()
    {
        videoView = findViewById(R.id.video_view);
        controller = new MediaController(this);
        videoView.setMediaController(controller);
    }

    /**
     * 播放视频;
     */
    private void play()
    {
        if (TextUtils.isEmpty(videoPath))
        {
            ToastUtil.show(this, R.string.video_not_found_in_local);
            return;
        }
        String url = String.format("android.resource://%s/%d", getPackageName(), R.raw.video11);
        videoView.setVideoPath(url);
        videoView.start();
    }

    /**
     * 点击后退按钮;
     * @param keyCode keyCode;
     * @param event   event;
     * @return succ ?;
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // 把视频详情界面传递过来的被点击视频的位置传递回去
        Intent data = new Intent();
        data.putExtra(
                EnumExtraParam.POSITION.literal(),
                position);
        setResult(RESULT_OK, data);
        return super.onKeyDown(keyCode, event);
    }
}
