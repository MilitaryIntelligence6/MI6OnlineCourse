package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.util.MdFiveUtil;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

/**
 * @author Administrator
 */
public class UpdatePasswordActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initContent();
        initView();
        initSharedPref();
        initListener();
    }

    private void initContent() {
        this.setContentView(R.layout.activity_update_pwd);
    }

    private void initView() {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(R.string.mod_password);
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener((View v) ->
                UpdatePasswordActivity.this.finish());

        oldPasswordEditText = findViewById(R.id.old_password_edit_text);
        newPasswordEditText = findViewById(R.id.new_password_edit_text);
        newPasswordAgainEditText = findViewById(R.id.new_password_again_edit_text);
        saveButton = findViewById(R.id.btn_save);
    }

    private void initSharedPref() {
        sharedPreferLoginInfo = new SharedPreferLoginInfo(UpdatePasswordActivity.this);
        username = sharedPreferLoginInfo.getLoginUsername();
    }

    private void logicalJudgement() {
        if (oldPassword.isEmpty()) {
            ToastUtil.show(this, R.string.empty_old_password);
        } else if (!MdFiveUtil.md5(oldPassword).equals(sharedPreferLoginInfo.getPwd(username))) {
            ToastUtil.show(this, R.string.wrong_old_password);
        } else if (newPassword.isEmpty()) {
            ToastUtil.show(this, R.string.empty_new_password);
        } else if (newPasswordAgain.isEmpty()) {
            ToastUtil.show(this, R.string.empty_new_password_confirm);
        } else if (!newPassword.equals(newPasswordAgain)) {
            ToastUtil.show(this, R.string.not_unanimous_password);
        } else if (MdFiveUtil.md5(newPassword).equals(sharedPreferLoginInfo.getPwd(username))) {
            ToastUtil.show(this, R.string.same_old_and_new_password);
        } else {
            ToastUtil.show(this, R.string.update_password_successfully);
            // 更新密码
            sharedPreferLoginInfo.saveInfo(username, newPassword);
            SettingActivity.instance.finish();
            UpdatePasswordActivity.this.finish();
        }
    }

    private void initListener() {
        saveButton.setOnClickListener((View v) ->
        {
            fetchEditString();
            logicalJudgement();
        });
    }

    private void fetchEditString() {
        oldPassword = oldPasswordEditText.getText().toString().trim();
        newPassword = newPasswordEditText.getText().toString().trim();
        newPasswordAgain = newPasswordAgainEditText.getText().toString().trim();
    }
}
