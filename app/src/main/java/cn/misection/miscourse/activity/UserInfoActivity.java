package cn.misection.miscourse.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.bean.UserBean;
import cn.misection.miscourse.tools.DBHelper;
import cn.misection.miscourse.tools.SPLoginInfo;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBack, tvMainTitle;
    private RelativeLayout rlTitleBar;
    private RelativeLayout rlNickname, rlSex, rlSignature;
    private TextView tvUsername, tvNickname, tvSex, tvSignature;
    private String spUsername;

    private static final int CHANGE_NICKNAME = 1;
    private static final int CHANGE_SIGNATURE = 2;
    private String newInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        SPLoginInfo spLoginInfo = new SPLoginInfo(UserInfoActivity.this);
        spUsername = spLoginInfo.getLoginUsername();

        init();
        initData();
    }

    public void enterForActivityResult(Class<?> to, int requestCode, Bundle b) {
        Intent i = new Intent(this, to);
        i.putExtras(b);
        startActivityForResult(i, requestCode);
    }

    private void initData() {
        UserBean bean = null;
        bean = DBHelper.getInstance(UserInfoActivity.this).getUserInfo(spUsername);
        if (bean == null) {
            bean = new UserBean();
            bean.setUsername(spUsername);
            bean.setNickname("问答精灵");
            bean.setSex("男");
            bean.setSignature("问答精灵");
            DBHelper.getInstance(UserInfoActivity.this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    private void setValue(UserBean bean) {
        tvUsername.setText(bean.getUsername());
        tvNickname.setText(bean.getNickname());
        tvSex.setText(bean.getSex());
        tvSignature.setText(bean.getSignature());
    }

    private void init() {
        rlTitleBar = findViewById(R.id.title_bar);
        rlTitleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvMainTitle.setText("个人资料");
        tvBack = findViewById(R.id.tV_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoActivity.this.finish();
            }
        });

        tvUsername = findViewById(R.id.tv_username);
        rlNickname = findViewById(R.id.rl_nickname);
        rlNickname.setOnClickListener(this);
        tvNickname = findViewById(R.id.tv_nickname);
        rlSex = findViewById(R.id.rl_sex);
        rlSex.setOnClickListener(this);
        tvSex = findViewById(R.id.tv_sex);
        rlSignature = findViewById(R.id.rl_signature);
        rlSignature.setOnClickListener(this);
        tvSignature = findViewById(R.id.tv_signature);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_nickname:
                String nickname = tvNickname.getText().toString();
                Bundle bdName = new Bundle();
                bdName.putString("content", nickname);
                bdName.putString("title", "昵称");
                bdName.putInt("flag", 1);
                enterForActivityResult(ChangeUserInfoActivity.class, CHANGE_NICKNAME, bdName);
                break;
            case R.id.rl_sex:
                String sex = tvSex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature = tvSignature.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content", signature);
                bdSignature.putString("title", "签名");
                bdSignature.putInt("flag", 2);
                enterForActivityResult(ChangeUserInfoActivity.class, CHANGE_SIGNATURE, bdSignature);
                break;
        }
    }

    private void sexDialog(String sex) {
        int sexFlag = ("男".equals(sex)) ? 0 : 1;
        final String[] items = {"男", "女"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(UserInfoActivity.this);
        dialog.setTitle("性别");
        dialog.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                // 修改数据库
                tvSex.setText(items[which]);
                DBHelper.getInstance(UserInfoActivity.this).updateUserInfo("sex", items[which], spUsername);
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANGE_NICKNAME:
                if (data != null) {
                    newInfo = data.getStringExtra("nickname");
                    if (newInfo.isEmpty()) {
                        return;
                    }
                    tvNickname.setText(newInfo);
                    DBHelper.getInstance(UserInfoActivity.this).updateUserInfo("nickname", newInfo, spUsername);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data != null) {
                    newInfo = data.getStringExtra("signature");
                    if (newInfo.isEmpty()) {
                        return;
                    }
                    tvSignature.setText(newInfo);
                    DBHelper.getInstance(UserInfoActivity.this).updateUserInfo("signature", newInfo, spUsername);
                }
                break;
        }

    }
}
