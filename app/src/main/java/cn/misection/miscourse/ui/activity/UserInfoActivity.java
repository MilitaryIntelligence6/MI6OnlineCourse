package cn.misection.miscourse.ui.activity;

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
import cn.misection.miscourse.entity.UserBean;
import cn.misection.miscourse.util.DataBaseHelper;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

/**
 * @author Administrator
 */
public class UserInfoActivity extends AppCompatActivity
        implements View.OnClickListener
{
    private TextView backTextView;

    private TextView mainTitleTextView;

    private RelativeLayout titleBarRelaLayout;

    private RelativeLayout nicknameRelaLayout;

    private RelativeLayout sexRelaLayout;

    private RelativeLayout signatureRelaLayout;

    private TextView usernameTextView;

    private TextView nicknameTextView;

    private TextView sexTextView;

    private TextView signatureTextView;

    private String spUsername;

    private static final int CHANGE_NICKNAME = 1;

    private static final int CHANGE_SIGNATURE = 2;

    private String newInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        SharedPreferLoginInfo sharedPreferLoginInfo = new SharedPreferLoginInfo(UserInfoActivity.this);
        spUsername = sharedPreferLoginInfo.getLoginUsername();

        init();
        initData();
    }

    public void enterForActivityResult(Class<?> to, int requestCode, Bundle b)
    {
        Intent intent = new Intent(this, to);
        intent.putExtras(b);
        startActivityForResult(intent, requestCode);
    }

    private void initData()
    {
        UserBean bean = null;
        bean = DataBaseHelper.getInstance(UserInfoActivity.this).getUserInfo(spUsername);
        if (bean == null)
        {
            bean = new UserBean();
            bean.setUsername(spUsername);
            bean.setNickname("问答精灵");
            bean.setSex("男");
            bean.setSignature("问答精灵");
            DataBaseHelper.getInstance(UserInfoActivity.this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    private void setValue(UserBean bean)
    {
        usernameTextView.setText(bean.getUsername());
        nicknameTextView.setText(bean.getNickname());
        sexTextView.setText(bean.getSex());
        signatureTextView.setText(bean.getSignature());
    }

    private void init()
    {
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30b4ff"));
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText("个人资料");
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UserInfoActivity.this.finish();
            }
        });

        usernameTextView = findViewById(R.id.tv_username);
        nicknameRelaLayout = findViewById(R.id.rl_nickname);
        nicknameRelaLayout.setOnClickListener(this);
        nicknameTextView = findViewById(R.id.tv_nickname);
        sexRelaLayout = findViewById(R.id.rl_sex);
        sexRelaLayout.setOnClickListener(this);
        sexTextView = findViewById(R.id.tv_sex);
        signatureRelaLayout = findViewById(R.id.rl_signature);
        signatureRelaLayout.setOnClickListener(this);
        signatureTextView = findViewById(R.id.tv_signature);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rl_nickname:
                String nickname = nicknameTextView.getText().toString();
                Bundle bdName = new Bundle();
                bdName.putString("content", nickname);
                bdName.putString("title", "昵称");
                bdName.putInt("flag", 1);
                enterForActivityResult(ChangeUserInfoActivity.class, CHANGE_NICKNAME, bdName);
                break;
            case R.id.rl_sex:
                String sex = sexTextView.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature = signatureTextView.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content", signature);
                bdSignature.putString("title", "签名");
                bdSignature.putInt("flag", 2);
                enterForActivityResult(ChangeUserInfoActivity.class, CHANGE_SIGNATURE, bdSignature);
                break;
        }
    }

    private void sexDialog(String sex)
    {
        int sexFlag = ("男".equals(sex)) ? 0 : 1;
        final String[] items = {"男", "女"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(UserInfoActivity.this);
        dialog.setTitle("性别");
        dialog.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                // 修改数据库
                sexTextView.setText(items[which]);
                DataBaseHelper.getInstance(UserInfoActivity.this).updateUserInfo("sex", items[which], spUsername);
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case CHANGE_NICKNAME:
                if (data != null)
                {
                    newInfo = data.getStringExtra("nickname");
                    if (newInfo.isEmpty())
                    {
                        return;
                    }
                    nicknameTextView.setText(newInfo);
                    DataBaseHelper.getInstance(UserInfoActivity.this).updateUserInfo("nickname", newInfo, spUsername);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data != null)
                {
                    newInfo = data.getStringExtra("signature");
                    if (newInfo.isEmpty())
                    {
                        return;
                    }
                    signatureTextView.setText(newInfo);
                    DataBaseHelper.getInstance(UserInfoActivity.this).updateUserInfo("signature", newInfo, spUsername);
                }
                break;
        }

    }
}
