package cn.misection.miscourse.ui.activity;

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
import cn.misection.miscourse.util.MD5Util;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView headImageView;

    private EditText usernameEditText;

    private EditText passwordEditText;

    private Button loginButton;

    private TextView mainTitleTextView;

    private TextView backTextView;

    private TextView registerTextView;

    private TextView findPasswordTextView;

    private Intent intent;

    private String username;

    private String password;

    private SharedPreferLoginInfo sharedPreferLoginInfo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back_text_view:
                LoginActivity.this.finish();
                break;
            case R.id.login_button:
                getEditString();
                logicalJudgement();
                break;
            case R.id.register_text_view:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.find_password_text_view:
                intent = new Intent(LoginActivity.this, FindPwdActivity.class);
                startActivity(intent);
                break;
            default:
            {
                break;
            }
        }
    }

    // 逻辑判断
    private void logicalJudgement()
    {
        if (username.isEmpty())
        {
            toastShow("请输入用户名");
        }
        else if (password.isEmpty())
        {
            toastShow("请输入密码");
        }
        else if (sharedPreferLoginInfo.getPwd(username).isEmpty())
        {
            toastShow("用户名不存在");
        }
        else if (!loginCheck(username, password))
        {
            toastShow("用户名或密码错误");
        }
        else
        {
            toastShow("用户登陆成功！");
            sharedPreferLoginInfo.saveLoginStatus(true, username);
            jumpActivity();
        }
    }

    // 带参数返回
    private void jumpActivity()
    {
        intent = new Intent();
        intent.putExtra("isLogin", true);
        setResult(RESULT_OK, intent);
        LoginActivity.this.finish();
    }

    // 登录账号密码验证
    private boolean loginCheck(String username, String password)
    {
        String md5Pwd = MD5Util.md5(password);
        return sharedPreferLoginInfo.getPwd(username).equals(md5Pwd);
    }

    private void toastShow(String message)
    {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void getEditString()
    {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
    }

    private void init()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText("登陆");
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener(this);

        headImageView = findViewById(R.id.head_image_view);
        usernameEditText = findViewById(R.id.login_username_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        registerTextView = findViewById(R.id.register_text_view);
        registerTextView.setOnClickListener(this);
        findPasswordTextView = findViewById(R.id.find_password_text_view);
        findPasswordTextView.setOnClickListener(this);

        sharedPreferLoginInfo = new SharedPreferLoginInfo(LoginActivity.this);
    }

    /**
     * 接受注册返回参数;
     * @param requestCode request;
     * @param resultCode result
     * @param data data;
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            String username = data.getStringExtra("username");
            if (!username.isEmpty())
            {
                usernameEditText.setText(username);
                usernameEditText.setSelection(username.length());  // 设置光标位置
            }
        }
    }
}
