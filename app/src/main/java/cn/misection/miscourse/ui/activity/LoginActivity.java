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

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
import cn.misection.miscourse.util.MdFiveUtil;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

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
        init();
    }

    private void init()
    {
        initContent();
        initView();
        initSharedPref();
    }

    private void initContent()
    {
        this.setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initView()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(R.string.login);
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
    }

    private void initSharedPref()
    {
        sharedPreferLoginInfo = new SharedPreferLoginInfo(LoginActivity.this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back_text_view:
            {
                LoginActivity.this.finish();
                break;
            }
            case R.id.login_button:
            {
                fetchEditString();
                logicalJudgement();
                break;
            }
            case R.id.register_text_view:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.find_password_text_view:
                intent = new Intent(LoginActivity.this, RetrievePasswordActivity.class);
                startActivity(intent);
                break;
            default:
            {
                break;
            }
        }
    }

    /**
     * ????????????;
     */
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
        else if (sharedPreferLoginInfo.getPwd(username).isEmpty())
        {
            ToastUtil.show(this, R.string.username_not_found);
        }
        else if (!loginCheck(username, password))
        {
            ToastUtil.show(this, R.string.wrong_username_or_password);
        }
        else
        {
            ToastUtil.show(this, R.string.login_successfully);
            sharedPreferLoginInfo.saveLoginStatus(true, username);
            gotoActivity();
        }
    }

    /**
     * ???????????????;
     */
    private void gotoActivity()
    {
        intent = new Intent();
        intent.putExtra(EnumExtraParam.IS_LOGIN.literal(), true);
        setResult(RESULT_OK, intent);
        LoginActivity.this.finish();
    }

    /**
     * ????????????????????????;
     * @param username
     * @param password
     * @return
     */
    private boolean loginCheck(String username, String password)
    {
        String md5Pwd = MdFiveUtil.md5(password);
        return sharedPreferLoginInfo.getPwd(username).equals(md5Pwd);
    }

    private void fetchEditString()
    {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
    }

    /**
     * ????????????????????????;
     * @param requestCode request;
     * @param resultCode  result
     * @param data        data;
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            String username = data.getStringExtra(EnumExtraParam.USERNAME.literal());
            if (!username.isEmpty())
            {
                usernameEditText.setText(username);
                // ??????????????????;
                usernameEditText.setSelection(username.length());
            }
        }
    }
}
