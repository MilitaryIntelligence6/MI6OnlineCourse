package cn.misection.miscourse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.MD5Utils;
import cn.misection.miscourse.util.SPLoginInfo;

public class UpdatePwdActivity extends AppCompatActivity {
    private TextView tvBack, tvMainTitle;
    private EditText etOldPassword, etNewPassword, etNewPasswordAgain;
    private Button btnSave;
    private String oldPassword, newPassword, newPasswordAgain, username;
    SPLoginInfo spLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        init();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                logicalJudgement();
            }
        });
    }

    private void logicalJudgement() {
        if (oldPassword.isEmpty()) {
            toastShow("请输入原始密码");
        } else if (!MD5Utils.md5(oldPassword).equals(spLoginInfo.getPwd(username))) {
            toastShow("原始密码错误");
        } else if (newPassword.isEmpty()) {
            toastShow("请输入新密码");
        } else if (newPasswordAgain.isEmpty()) {
            toastShow("请再次输入新密码");
        } else if (!newPassword.equals(newPasswordAgain)) {
            toastShow("两次输入的密码不一致");
        } else if (MD5Utils.md5(newPassword).equals(spLoginInfo.getPwd(username))) {
            toastShow("新密码不能与原始密码一直");
        } else {
            toastShow("新密码设置成功");
            // 更新密码
            spLoginInfo.saveInfo(username, newPassword);
            SettingActivity.instance.finish();
            UpdatePwdActivity.this.finish();
        }
    }

    private void toastShow(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getEditString() {
        oldPassword = etOldPassword.getText().toString().trim();
        newPassword = etNewPassword.getText().toString().trim();
        newPasswordAgain = etNewPasswordAgain.getText().toString().trim();
    }

    private void init() {
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvMainTitle.setText("修改密码");
        tvBack = findViewById(R.id.tV_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePwdActivity.this.finish();
            }
        });

        etOldPassword = findViewById(R.id.et_old_password);
        etNewPassword = findViewById(R.id.et_new_password);
        etNewPasswordAgain = findViewById(R.id.et_new_password_again);
        btnSave = findViewById(R.id.btn_save);

        spLoginInfo = new SPLoginInfo(UpdatePwdActivity.this);
        username = spLoginInfo.getLoginUsername();
    }
}
