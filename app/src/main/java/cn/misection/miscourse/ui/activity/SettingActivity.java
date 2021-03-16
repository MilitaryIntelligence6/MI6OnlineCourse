package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

/**
 * @author Administrator
 */
public class SettingActivity extends AppCompatActivity
        implements View.OnClickListener
{
    private RelativeLayout updatePasswordRelaLayout;

    private RelativeLayout securitySettingRelaLayout;

    private RelativeLayout logoutRelaLayout;

    private RelativeLayout titleBarRelaLayout;

    private TextView backTextView;

    private TextView mainTittleTextView;

    public static SettingActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init()
    {
        instance = this;

        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30B4FF"));
        mainTittleTextView = findViewById(R.id.main_title_text_view);
        mainTittleTextView.setText("设置");
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener(this);

        updatePasswordRelaLayout = findViewById(R.id.rl_update_password);
        updatePasswordRelaLayout.setOnClickListener(this);
        securitySettingRelaLayout = findViewById(R.id.rl_security_setting);
        securitySettingRelaLayout.setOnClickListener(this);
        logoutRelaLayout = findViewById(R.id.rl_logout);
        logoutRelaLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back_text_view:
            {
                SettingActivity.this.finish();
                break;
            }
            case R.id.rl_update_password:
            {
                Intent intent = new Intent(SettingActivity.this, UpdatePwdActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.rl_security_setting:
            {
                Intent intent = new Intent(SettingActivity.this, RetrievePasswordActivity.class);
                // 设置场景值，从设置密保入口进
                intent.putExtra("from", "security");
                startActivity(intent);
                break;
            }
            case R.id.rl_logout:
            {
                ToastUtil.show(this, "已退出登录");
                // 清空用户登录状态
                new SharedPreferLoginInfo(SettingActivity.this).saveLoginStatus(false, "");
                Intent data = new Intent();
                data.putExtra("isLogin", false);
                setResult(RESULT_OK, data);
                SettingActivity.this.finish();
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
