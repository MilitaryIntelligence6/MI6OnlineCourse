package cn.misection.miscourse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.SPLoginInfo;

public class FindPwdActivity extends AppCompatActivity {
    private TextView tvBack, tvMainTitle, tvUsername, tvResetPassword;
    private EditText etUsername, etValidateName;
    private Button btnValidate;
    private String from, validateName;
    SPLoginInfo spLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        from = getIntent().getStringExtra("from");
        init();

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPwdActivity.this.finish();
            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateName = etValidateName.getText().toString().trim();
                if ("security".equals(from)){
                    if (validateName.isEmpty()){
                        Toast.makeText(FindPwdActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(FindPwdActivity.this, "密保设置成功", Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        finish();
                    }
                }else{
                    String username = etUsername.getText().toString().trim();
                    String resultSecurity = readSecurity(username);
                    if (username.isEmpty()){
                        Toast.makeText(FindPwdActivity.this, "请输入你的用户名", Toast.LENGTH_SHORT).show();
                    }else if (spLoginInfo.getPwd(username).isEmpty()){
                        Toast.makeText(FindPwdActivity.this, "你输入的用户名不存在", Toast.LENGTH_SHORT).show();
                    }else if (validateName.isEmpty()){
                        Toast.makeText(FindPwdActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                    }else if (!validateName.equals(resultSecurity)){
                        Toast.makeText(FindPwdActivity.this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
                    }else{
                        tvResetPassword.setVisibility(View.VISIBLE);
                        tvResetPassword.setText("初始密码：123456");
                        // 重置密码
                        spLoginInfo.saveInfo(username, "123456");
                    }
                }
            }
        });
    }

    private String readSecurity(String username) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(username + "_security", "");
    }

    private void saveSecurity(String validateName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(spLoginInfo.getLoginUsername() + "_security", validateName);
        editor.commit();
    }

    private void init() {
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvBack = findViewById(R.id.text_view_back);
        tvUsername = findViewById(R.id.tv_username);
        tvResetPassword = findViewById(R.id.tv_reset_password);
        etUsername = findViewById(R.id.et_username);
        etValidateName = findViewById(R.id.et_validate_name);
        btnValidate = findViewById(R.id.btn_validate);
        if ("security".equals(from)){
            tvMainTitle.setText("设置密保");
        }else{
            tvMainTitle.setText("找回密码");
            tvUsername.setVisibility(View.VISIBLE);
            etUsername.setVisibility(View.VISIBLE);
        }

        spLoginInfo = new SPLoginInfo(FindPwdActivity.this);
    }
}
