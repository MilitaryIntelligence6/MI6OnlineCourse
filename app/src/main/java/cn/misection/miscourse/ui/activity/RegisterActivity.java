package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
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
        initView();
        // 注册按钮点击事件
        registerButton.setOnClickListener((View v) ->
        {
            sharedPrefLoginInfo = new SharedPreferLoginInfo(RegisterActivity.this);
            getEditString();
            logicalJudgement();
        });
    }

    private void initView()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(R.string.register);
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

    private void logicalJudgement()
    {
        if (username.isEmpty())
        {
            ToastUtil.show(this, R.string.empty_username);
        }
        else if (password.isEmpty())
        {
            ToastUtil.show(this, R.string.empty_password);
        }
        else if (passwordAgain.isEmpty())
        {
            ToastUtil.show(this, R.string.type_password_again);
        }
        else if (!sharedPrefLoginInfo.getPwd(username).isEmpty())
        {
            ToastUtil.show(this, R.string.username_already_exist);
        }
        else if (!password.equals(passwordAgain))
        {
            ToastUtil.show(this, R.string.not_unanimous_password);
        }
        else
        {
            ToastUtil.show(this, R.string.register_successfully);
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
        intent.putExtra(EnumExtraParam.USERNAME.literal(), username);
        setResult(RESULT_OK, intent);
        RegisterActivity.this.finish();
    }

    /**
     * 获取文本输入;
     */
    private void getEditString()
    {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        passwordAgain = passwordAgainEditText.getText().toString().trim();
    }
}
