package com.example.miscourse.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miscourse.R;
import com.example.miscourse.activity.LoginActivity;
import com.example.miscourse.activity.PlayHistoryActivity;
import com.example.miscourse.activity.SettingActivity;
import com.example.miscourse.activity.UserInfoActivity;
import com.example.miscourse.tools.SPLoginInfo;

public class MineView implements View.OnClickListener {
    private Context context;
    private final LayoutInflater inflater;
    private View view;
    private TextView tvUsername;
    private RelativeLayout rlPlayHistory, rlSetting;
    private LinearLayout llLogin;
    private Intent intent;
    SPLoginInfo spLoginInfo;

    public MineView(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public View getView() {
        if (view == null) {
            createView();
        }
        return view;
    }

    private void createView() {
        init();
    }


    private void init() {
        view = inflater.inflate(R.layout.main_view_mine, null);
        tvUsername = view.findViewById(R.id.tv_username);
        rlPlayHistory = view.findViewById(R.id.rl_play_history);
        rlPlayHistory.setOnClickListener(this);
        rlSetting = view.findViewById(R.id.rl_setting);
        rlSetting.setOnClickListener(this);
        llLogin = view.findViewById(R.id.ll_login);
        llLogin.setOnClickListener(this);
        view.setVisibility(View.VISIBLE);

        spLoginInfo = new SPLoginInfo(context);
        setLoginParams(spLoginInfo.getLoginStatus());
    }

    public void setLoginParams(boolean isLogin) {
        tvUsername.setText(isLogin ? spLoginInfo.getLoginUsername() : "点击登陆");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login:
                if (spLoginInfo.getLoginStatus()) {
                    intent = new Intent(context, UserInfoActivity.class);
                    ((Activity) context).startActivityForResult(intent, 1);
                } else {
                    intent = new Intent(context, LoginActivity.class);
                    ((Activity) context).startActivityForResult(intent, 1);
                }
                break;
            case R.id.rl_play_history:
                if (spLoginInfo.getLoginStatus()) {
                    intent = new Intent(context, PlayHistoryActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "你还未登陆，请先登陆", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (spLoginInfo.getLoginStatus()) {
                    intent = new Intent(context, SettingActivity.class);
                    ((Activity) context).startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(context, "你还未登陆，请先登陆", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void showView() {
        if (view == null) {
            init();
        }
        view.setVisibility(View.VISIBLE);
    }
}
