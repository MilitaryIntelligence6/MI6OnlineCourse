package cn.misection.miscourse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.SPLoginInfo;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvMainTitle, tvBack;
    private EditText etUsername, etPassword, etPasswordAgain;
    private Button btnRegister;
    private Intent intent;
    private String username, password, passwordAgain;
    SPLoginInfo spLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        // 注册按钮点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spLoginInfo = new SPLoginInfo(RegisterActivity.this);
                getEditString();
                logicalJudgement();
            }
        });
    }
    private void logicalJudgement() {
        if (username.isEmpty()) {
            toastShow("请输入用户名");
        } else if (password.isEmpty()) {
            toastShow("请输入密码");
        } else if (passwordAgain.isEmpty()) {
            toastShow("请再次输入密码");
        } else if (!spLoginInfo.getPwd(username).isEmpty()) {
            toastShow("用户名已存在");
        } else if (!password.equals(passwordAgain)) {
            toastShow("两次输入的密码不一致");
        } else {
            toastShow("用户注册成功！");
            spLoginInfo.saveInfo(username, password);
            jumpActivity();
        }
    }

    // 带参数返回
    private void jumpActivity() {
        intent = new Intent();
        intent.putExtra("username", username);
        setResult(RESULT_OK, intent);
        RegisterActivity.this.finish();
    }

    private void toastShow(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    // 获取文本输入
    private void getEditString() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        passwordAgain = etPasswordAgain.getText().toString().trim();
    }

    private void init() {
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvMainTitle.setText("注册");
        tvBack = findViewById(R.id.tV_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        etUsername = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        etPasswordAgain = findViewById(R.id.et_register_password_again);
        btnRegister = findViewById(R.id.btn_register);
    }
}
