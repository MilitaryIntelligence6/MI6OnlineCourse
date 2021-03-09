package cn.misection.miscourse.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.MD5Utils;
import cn.misection.miscourse.util.SPLoginInfo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivHead;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvMainTitle, tvBack, tvRegister, tvFindPassword;
    private Intent intent;
    private String username, password;
    SPLoginInfo spLoginInfo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tV_back:
                LoginActivity.this.finish();
                break;
            case R.id.btn_login:
                getEditString();
                logicalJudgement();
                break;
            case R.id.tv_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_find_password:
                intent = new Intent(LoginActivity.this, FindPwdActivity.class);
                startActivity(intent);
                break;
        }
    }

    // 逻辑判断
    private void logicalJudgement() {
        if (username.isEmpty()) {
            toastShow("请输入用户名");
        } else if (password.isEmpty()) {
            toastShow("请输入密码");
        } else if (spLoginInfo.getPwd(username).isEmpty()) {
            toastShow("用户名不存在");
        } else if (!loginCheck(username, password)) {
            toastShow("用户名或密码错误");
        } else {
            toastShow("用户登陆成功！");
            spLoginInfo.saveLoginStatus(true, username);
            jumpActivity();
        }
    }

    // 带参数返回
    private void jumpActivity() {
        intent = new Intent();
        intent.putExtra("isLogin", true);
        setResult(RESULT_OK, intent);
        LoginActivity.this.finish();
    }

    // 登录账号密码验证
    private boolean loginCheck(String username, String password) {
        String md5Pwd = MD5Utils.md5(password);
        return spLoginInfo.getPwd(username).equals(md5Pwd);
    }

    private void toastShow(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void getEditString() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
    }

    private void init() {
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvMainTitle.setText("登陆");
        tvBack = findViewById(R.id.tV_back);
        tvBack.setOnClickListener(this);

        ivHead = findViewById(R.id.iv_head);
        etUsername = findViewById(R.id.et_login_username);
        etPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        tvFindPassword = findViewById(R.id.tv_find_password);
        tvFindPassword.setOnClickListener(this);

        spLoginInfo = new SPLoginInfo(LoginActivity.this);
    }

    // 接受注册返回参数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String username = data.getStringExtra("username");
            if (!username.isEmpty()) {
                etUsername.setText(username);
                etUsername.setSelection(username.length());  // 设置光标位置
            }
        }
    }
}
