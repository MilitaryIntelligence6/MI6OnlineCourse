package cn.misection.miscourse.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.global.EnumAssets;
import cn.misection.miscourse.constant.global.EnumCommonString;
import cn.misection.miscourse.constant.ui.EnumDefaultValue;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
import cn.misection.miscourse.constant.ui.EnumJsonObj;
import cn.misection.miscourse.ui.adapter.VideoListAdapter;
import cn.misection.miscourse.entity.VideoBean;
import cn.misection.miscourse.util.DataBaseHelper;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView introTextView;

    private TextView videoTextView;

    private TextView chapterIntroTextView;

    private ListView videoListView;

    private ScrollView chapIntroScrollView;

    private VideoListAdapter adapter;

    private List<VideoBean> videoList;

    private int chapterId;

    private String intro;

    private DataBaseHelper dataBaseHelper;

    private SharedPreferLoginInfo sharedPreferLoginInfo;

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
        initDataBase();
        initData();
        initView();
    }

    private void initContent()
    {
        this.setContentView(R.layout.activity_video_list);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // ????????????????????????????????????Id
        chapterId = getIntent().getIntExtra(
                EnumExtraParam.ID.literal(),
                EnumDefaultValue.INSTANCE.intVal());
        // ???????????????????????????????????????
        intro = getIntent().getStringExtra(
                EnumExtraParam.INTRO.literal());
    }

    private void initDataBase()
    {
        // ?????????????????????????????????
        dataBaseHelper = DataBaseHelper.requireInstance(
                VideoListActivity.this);
        sharedPreferLoginInfo = new SharedPreferLoginInfo(this);
    }

    private void initView()
    {
        introTextView = findViewById(R.id.intro_text_view);
        videoTextView = findViewById(R.id.video_text_view);
        videoListView = findViewById(R.id.video_list_view);
        chapterIntroTextView = findViewById(R.id.chapter_intro_text_view);
        chapIntroScrollView = findViewById(R.id.chapter_intro_scroll_view);
        initAdapter();
        chapterIntroTextView.setText(intro);
        introTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
        videoTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        introTextView.setTextColor(Color.parseColor("#FFFFFF"));
        videoTextView.setTextColor(Color.parseColor("#000000"));
    }

    private void initAdapter()
    {
        adapter = new VideoListAdapter(this,
                (int position, ImageView imageView) ->
                {
                    adapter.setSelectedPosition(position);
                    VideoBean bean = videoList.get(position);
                    String videoPath = bean.getVideoPath();
                    adapter.notifyDataSetChanged();  // ???????????????
                    if (TextUtils.isEmpty(videoPath))
                    {
                        ToastUtil.show(
                                VideoListActivity.this,
                                R.string.local_video_not_found);
                        return;
                    }
                    else
                    {
                        // ?????????????????????????????????????????????????????????????????????
                        if (sharedPreferLoginInfo.hasLogin())
                        {
                            String username = sharedPreferLoginInfo.getLoginUsername();
                            dataBaseHelper.saveVideoPlayList(videoList.get(position), username);
                        }
                        // ???????????????????????????
                        Intent intent = new Intent(VideoListActivity.this, VideoPlayActivity.class);
                        intent.putExtra(
                                EnumExtraParam.VIDEO_PATH.literal(),
                                videoPath);
                        intent.putExtra(
                                EnumExtraParam.POSITION.literal(),
                                position);
                        startActivityForResult(intent, 1);
                    }
                });
        videoListView.setAdapter(adapter);
        introTextView.setOnClickListener(this);
        videoTextView.setOnClickListener(this);
        adapter.setData(videoList);
    }

    private void initData()
    {
        JSONArray jsonArray;
        InputStream is = null;
        try
        {
            is = getResources().getAssets().open(EnumAssets.VIDEO_DATA.getPath());
            jsonArray = new JSONArray(read(is));
            videoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                VideoBean bean = new VideoBean();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getInt(
                        EnumJsonObj.CHAPTER_ID.getJsonName())
                        == chapterId)
                {
                    bean.setChapterId(jsonObject.getInt(
                            EnumJsonObj.CHAPTER_ID.getJsonName()));
                    bean.setVideoId(Integer.parseInt(jsonObject.getString(
                            EnumJsonObj.VIDEO_ID.getJsonName())));
                    bean.setTitle(jsonObject.getString(
                            EnumJsonObj.TITLE.getJsonName()));
                    bean.setSecondTitle(jsonObject.getString(
                            EnumJsonObj.SECOND_TITLE.getJsonName()));
                    bean.setVideoPath(jsonObject.getString(
                            EnumJsonObj.VIDEO_ID.getJsonName()));
                    videoList.add(bean);
                }
                bean = null;
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    private String read(InputStream is)
    {
        BufferedReader reader = null;
        StringBuilder builder = null;
        String line = null;
        try
        {
            builder = new StringBuilder();
            // ???InputStreamReader???is??????????????????????????????BufferedReader
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
                builder.append(EnumCommonString.NEW_LINE.value());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return EnumCommonString.EMPTY.value();
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return String.valueOf(builder);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // ??????;
            case R.id.intro_text_view:
            {
                videoListView.setVisibility(View.GONE);
                chapIntroScrollView.setVisibility(View.VISIBLE);
                introTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
                videoTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                introTextView.setTextColor(Color.parseColor("#FFFFFF"));
                videoTextView.setTextColor(Color.parseColor("#000000"));
                break;
            }
            // ??????;
            case R.id.video_text_view:
            {
                videoListView.setVisibility(View.VISIBLE);
                chapIntroScrollView.setVisibility(View.GONE);
                introTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                videoTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
                introTextView.setTextColor(Color.parseColor("#000000"));
                videoTextView.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            default:
            {
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            // ????????????????????????????????????????????????????????????
            int position = data.getIntExtra(
                    EnumExtraParam.POSITION.literal(),
                    EnumDefaultValue.INSTANCE.intVal());
            // ????????????????????????
            adapter.setSelectedPosition(position);
            // ???????????????????????????????????????????????????
            videoListView.setVisibility(View.VISIBLE);
            chapIntroScrollView.setVisibility(View.GONE);
            introTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            videoTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
            introTextView.setTextColor(Color.parseColor("#000000"));
            videoTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}
