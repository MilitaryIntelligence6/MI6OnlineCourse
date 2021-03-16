package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

/**
 * @author Administrator
 */
public class RegisterActivity extends AppCompatActivity
{
    private TextView mainTitleTextView;

    private TextView backTextView;

    private EditText usernameEditText;

    private EditText passwordEditText;

    private EditText passwordAgainEditText;

    private Button registerButton;

    private Intent intent;

    private String username;

    private String password;

    private String passwordAgain;

    private SharedPreferLoginInfo sharedPrefLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        // 注册按钮点击事件
        registerButton.setOnClickListener((View v) ->
        {
            sharedPrefLoginInfo = new SharedPreferLoginInfo(RegisterActivity.this);
            getEditString();
            logicalJudgement();
        });
    }

    private void logicalJudgement()
    {
        if (username.isEmpty())
        {
//            toastShow("请输入用户名");
            ToastUtil.show(this, "请输入用户名");
        }
        else if (password.isEmpty())
        {
//            toastShow("请输入密码");
            ToastUtil.show(this, "请输入密码");
        }
        else if (passwordAgain.isEmpty())
        {
//            toastShow("请再次输入密码");
            ToastUtil.show(this, "请再次输入密码");
        }
        else if (!sharedPrefLoginInfo.getPwd(username).isEmpty())
        {
//            toastShow("用户名已存在");
            ToastUtil.show(this, "用户名已存在");
        }
        else if (!password.equals(passwordAgain))
        {
//            toastShow("两次输入的密码不一致");
            ToastUtil.show(this, "两次输入的密码不一致");
        }
        else
        {
//            toastShow("用户注册成功!");
            ToastUtil.show(this, "用户注册成功!");
            sharedPrefLoginInfo.saveInfo(username, password);
            gotoActivity();
        }
    }

    /**
     * 带参数返回;
     */
    private void gotoActivity()
    {
        intent = new Intent();
        intent.putExtra("username", username);
        setResult(RESULT_OK, intent);
        RegisterActivity.this.finish();
    }

//    private void toastShow(String message)
//    {
//        Toast.makeText(
//                RegisterActivity.this,
//                message,
//                Toast.LENGTH_SHORT).
//                show();
//    }

    /**
     * 获取文本输入;
     */
    private void getEditString()
    {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        passwordAgain = passwordAgainEditText.getText().toString().trim();
    }

    private void init()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText("注册");
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RegisterActivity.this.finish();
            }
        });

        usernameEditText = findViewById(R.id.et_register_username);
        passwordEditText = findViewById(R.id.et_register_password);
        passwordAgainEditText = findViewById(R.id.et_register_password_again);
        registerButton = findViewById(R.id.btn_register);
    }
}
