package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.MD5Util;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

public class UpdatePwdActivity extends AppCompatActivity
{
    private TextView backTextView;

    private TextView mainTitleTextView;

    private EditText oldPasswordEditText;

    private EditText newPasswordEditText;

    private EditText newPasswordAgainEditText;

    private Button saveButton;

    private String oldPassword;

    private String newPassword;

    private String newPasswordAgain;

    private String username;

    private SharedPreferLoginInfo sharedPreferLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        init();
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getEditString();
                logicalJudgement();
            }
        });
    }

    private void logicalJudgement()
    {
        if (oldPassword.isEmpty())
        {
            toastShow("请输入原始密码");
        }
        else if (!MD5Util.md5(oldPassword).equals(sharedPreferLoginInfo.getPwd(username)))
        {
            toastShow("原始密码错误");
        }
        else if (newPassword.isEmpty())
        {
            toastShow("请输入新密码");
        }
        else if (newPasswordAgain.isEmpty())
        {
            toastShow("请再次输入新密码");
        }
        else if (!newPassword.equals(newPasswordAgain))
        {
            toastShow("两次输入的密码不一致");
        }
        else if (MD5Util.md5(newPassword).equals(sharedPreferLoginInfo.getPwd(username)))
        {
            toastShow("新密码不能与原始密码一直");
        }
        else
        {
            toastShow("新密码设置成功");
            // 更新密码
            sharedPreferLoginInfo.saveInfo(username, newPassword);
            SettingActivity.instance.finish();
            UpdatePwdActivity.this.finish();
        }
    }

    private void toastShow(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getEditString()
    {
        oldPassword = oldPasswordEditText.getText().toString().trim();
        newPassword = newPasswordEditText.getText().toString().trim();
        newPasswordAgain = newPasswordAgainEditText.getText().toString().trim();
    }

    private void init()
    {
        mainTitleTextView = findViewById(R.id.tv_main_title);
        mainTitleTextView.setText("修改密码");
        backTextView = findViewById(R.id.text_view_back);
        backTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UpdatePwdActivity.this.finish();
            }
        });

        oldPasswordEditText = findViewById(R.id.et_old_password);
        newPasswordEditText = findViewById(R.id.et_new_password);
        newPasswordAgainEditText = findViewById(R.id.et_new_password_again);
        saveButton = findViewById(R.id.btn_save);

        sharedPreferLoginInfo = new SharedPreferLoginInfo(UpdatePwdActivity.this);
        username = sharedPreferLoginInfo.getLoginUsername();
    }
}
