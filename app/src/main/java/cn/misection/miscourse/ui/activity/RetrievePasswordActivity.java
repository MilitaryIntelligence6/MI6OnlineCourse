package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.global.EnumCommonString;
import cn.misection.miscourse.constant.ui.EnumSecurity;
import cn.misection.miscourse.constant.ui.UiConst;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

/**
 * @author Administrator
 */
public class RetrievePasswordActivity extends AppCompatActivity
{
    private TextView mainTitleTextView;

    private TextView backTextView;

    private TextView usernameTextView;

    private TextView resetPasswordTextView;

    private EditText usernameEditText;

    private EditText validateNameEditText;

    private Button validateButton;

    private String from;

    private String validateName;

    private SharedPreferLoginInfo sharedPreferLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        initContent();
        initFrom();
        initView();
        initSharePref();
        initListener();
    }

    private void initContent()
    {
        this.setContentView(R.layout.activity_find_pwd);
    }

    private void initFrom()
    {
        from = this.getIntent().getStringExtra(UiConst.FROM_INTENT_NAME);
        if (from == null)
        {
            from = EnumCommonString.EMPTY.value();
        }
    }

    private void initView()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        backTextView = findViewById(R.id.back_text_view);

        usernameTextView = findViewById(R.id.username_text_view);
        resetPasswordTextView = findViewById(R.id.reset_password_text_view);
        usernameEditText = findViewById(R.id.username_edit_text);
        validateNameEditText = findViewById(R.id.validate_name_edit_text);
        validateButton = findViewById(R.id.validate_button);
        initFromView();
    }

    private void initFromView()
    {
        switch (from)
        {
            case UiConst.SECURITY:
            {
                mainTitleTextView.setText(EnumSecurity.SECURITY_QUESTION.text());
                break;
            }
            default:
            {
                mainTitleTextView.setText(EnumSecurity.FIND_PASSWORD.text());
                usernameTextView.setVisibility(View.VISIBLE);
                usernameEditText.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    private void initSharePref()
    {
        sharedPreferLoginInfo = new SharedPreferLoginInfo(RetrievePasswordActivity.this);
    }

    private void initListener()
    {
        backTextView.setOnClickListener((View v) ->
                RetrievePasswordActivity.this.finish());

        validateButton.setOnClickListener((View v) ->
        {
            validateName = validateNameEditText.getText().toString().trim();
            switch (from)
            {
                case UiConst.SECURITY:
                {
                    if (validateName.isEmpty())
                    {
                        ToastUtil.show(RetrievePasswordActivity.this,
                                "请输入要验证的姓名");
                    }
                    else
                    {
                        ToastUtil.show(RetrievePasswordActivity.this,
                                "密保设置成功");
                        saveSecurity(validateName);
                        finish();
                    }
                    break;
                }
                default:
                {
                    String username = usernameEditText.getText().toString().trim();
                    String resultSecurity = readSecurity(username);
                    if (username.isEmpty())
                    {
                        ToastUtil.show(RetrievePasswordActivity.this,
                                "请输入你的用户名");
                    }
                    else if (sharedPreferLoginInfo.getPwd(username).isEmpty())
                    {
                        ToastUtil.show(RetrievePasswordActivity.this,
                                "你输入的用户名不存在");
                    }
                    else if (validateName.isEmpty())
                    {
                        ToastUtil.show(RetrievePasswordActivity.this,
                                "请输入要验证的姓名");
                    }
                    else if (!validateName.equals(resultSecurity))
                    {
                        ToastUtil.show(RetrievePasswordActivity.this,
                                "输入的密保不正确");
                    }
                    else
                    {
                        resetPasswordTextView.setVisibility(View.VISIBLE);
                        resetPasswordTextView.setText("初始密码：123456");
                        // 重置密码
                        sharedPreferLoginInfo.saveInfo(username, "123456");
                    }
                    break;
                }
            }
        });
    }

    private String readSecurity(String username)
    {
        SharedPreferences sharedPref = this.getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sharedPref.getString(String.format("%s_security", username), "");
    }

    private void saveSecurity(String validateName)
    {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(String.format("%s_security", sharedPreferLoginInfo.getLoginUsername()), validateName);
        editor.commit();
    }
}
