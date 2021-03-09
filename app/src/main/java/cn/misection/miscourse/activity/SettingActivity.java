package cn.misection.miscourse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.SPLoginInfo;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rlUpdatePassword, rlSecuritySetting, rlLogout, rlTitleBar;
    private TextView tvBack, tvMainTittle;
    public static SettingActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        instance = this;

        rlTitleBar = findViewById(R.id.title_bar);
        rlTitleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvMainTittle = findViewById(R.id.tv_main_title);
        tvMainTittle.setText("设置");
        tvBack = findViewById(R.id.tV_back);
        tvBack.setOnClickListener(this);

        rlUpdatePassword = findViewById(R.id.rl_update_password);
        rlUpdatePassword.setOnClickListener(this);
        rlSecuritySetting = findViewById(R.id.rl_security_setting);
        rlSecuritySetting.setOnClickListener(this);
        rlLogout = findViewById(R.id.rl_logout);
        rlLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tV_back:
                SettingActivity.this.finish();
                break;
            case R.id.rl_update_password:
                Intent intent = new Intent(SettingActivity.this, UpdatePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_security_setting:
                Intent intent1 = new Intent(SettingActivity.this, FindPwdActivity.class);
                // 设置场景值，从设置密保入口进
                intent1.putExtra("from", "security");
                startActivity(intent1);
                break;
            case R.id.rl_logout:
                toastShow("已退出登录");
                // 清空用户登录状态
                new SPLoginInfo(SettingActivity.this).saveLoginStatus(false, "");
                Intent data = new Intent();
                data.putExtra("isLogin", false);
                setResult(RESULT_OK, data);
                SettingActivity.this.finish();
                break;
        }
    }

    private void toastShow(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
