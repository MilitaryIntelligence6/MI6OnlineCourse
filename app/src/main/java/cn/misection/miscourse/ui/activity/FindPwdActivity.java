package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.ui.uiconst.UiConst;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

/**
 * @author Administrator
 */
public class FindPwdActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_find_pwd);
        from = getIntent().getStringExtra(UiConst.FROM_INTENT_NAME);
        init();

        backTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FindPwdActivity.this.finish();
            }
        });

        validateButton.setOnClickListener((View v) ->
        {
            validateName = validateNameEditText.getText().toString().trim();
            switch (from)
            {
                case UiConst.SECURITY:
                    if (validateName.isEmpty())
                    {
                        Toast.makeText(FindPwdActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(FindPwdActivity.this, "密保设置成功", Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        finish();
                    }
                    break;
                default:
                    String username = usernameEditText.getText().toString().trim();
                    String resultSecurity = readSecurity(username);
                    if (username.isEmpty())
                    {
                        Toast.makeText(FindPwdActivity.this, "请输入你的用户名", Toast.LENGTH_SHORT).show();
                    }
                    else if (sharedPreferLoginInfo.getPwd(username).isEmpty())
                    {
                        Toast.makeText(FindPwdActivity.this, "你输入的用户名不存在", Toast.LENGTH_SHORT).show();
                    }
                    else if (validateName.isEmpty())
                    {
                        Toast.makeText(FindPwdActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                    }
                    else if (!validateName.equals(resultSecurity))
                    {
                        Toast.makeText(FindPwdActivity.this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
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

    private void init()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        backTextView = findViewById(R.id.back_text_view);
        usernameTextView = findViewById(R.id.username_text_view);
        resetPasswordTextView = findViewById(R.id.reset_password_text_view);
        usernameEditText = findViewById(R.id.username_edit_text);
        validateNameEditText = findViewById(R.id.validate_name_edit_text);
        validateButton = findViewById(R.id.validate_button);
        if (from.equals(UiConst.SECURITY))
        {
            mainTitleTextView.setText("设置密保");
        }
        else
        {
            mainTitleTextView.setText("找回密码");
            usernameTextView.setVisibility(View.VISIBLE);
            usernameEditText.setVisibility(View.VISIBLE);
        }

        sharedPreferLoginInfo = new SharedPreferLoginInfo(FindPwdActivity.this);
    }
}
