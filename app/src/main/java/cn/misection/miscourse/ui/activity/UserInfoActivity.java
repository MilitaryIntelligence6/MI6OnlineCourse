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

import cn.misection.miscourse.R;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
import cn.misection.miscourse.constant.ui.EnumUserSex;
import cn.misection.miscourse.constant.ui.EnumUserInfo;
import cn.misection.miscourse.entity.UserBean;
import cn.misection.miscourse.util.DataBaseHelper;
import cn.misection.miscourse.util.SharedPreferLoginInfo;
import cn.misection.miscourse.util.ToastUtil;

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
        init();
    }

    private void init()
    {
        initContent();
        initSharedPref();
        initView();
        initData();
    }

    private void initContent()
    {
        setContentView(R.layout.activity_user_info);
    }

    private void initSharedPref()
    {
        SharedPreferLoginInfo sharedPreferLoginInfo = new SharedPreferLoginInfo(UserInfoActivity.this);
        spUsername = sharedPreferLoginInfo.getLoginUsername();
    }

    private void initView()
    {
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30b4ff"));
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(R.string.user_info);
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener((View v) ->
                UserInfoActivity.this.finish());

        usernameTextView = findViewById(R.id.username_text_view);
        nicknameRelaLayout = findViewById(R.id.rela_layout_nickname);
        nicknameRelaLayout.setOnClickListener(this);
        nicknameTextView = findViewById(R.id.nickname_text_view);
        sexRelaLayout = findViewById(R.id.rela_layout_sex);
        sexRelaLayout.setOnClickListener(this);
        sexTextView = findViewById(R.id.sex_text_view);
        signatureRelaLayout = findViewById(R.id.signature_rela_layout);
        signatureRelaLayout.setOnClickListener(this);
        signatureTextView = findViewById(R.id.signature_text_view);
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
        bean = DataBaseHelper.requireInstance(UserInfoActivity.this).getUserInfo(spUsername);
        if (bean == null)
        {
            bean = new UserBean();
            bean.setUsername(spUsername);
            bean.setNickname(this.getString(R.string.default_nickname));
            bean.setSex(EnumUserSex.MALE.text());
            bean.setSignature(this.getString(R.string.default_signature));
            DataBaseHelper.requireInstance(UserInfoActivity.this).saveUserInfo(bean);
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

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rela_layout_nickname:
            {
                String nickname = nicknameTextView.getText().toString();
                Bundle bdName = new Bundle();
                bdName.putString(
                        EnumExtraParam.CONTENT.literal(),
                        nickname);
                bdName.putString(
                        EnumExtraParam.TITLE.literal(),
                        this.getString(R.string.nickname_literal));
                bdName.putInt(
                        EnumExtraParam.FLAG.literal(),
                        EnumUserInfo.NICE_NAME.flag());
                enterForActivityResult(ChangeUserInfoActivity.class, CHANGE_NICKNAME, bdName);
                break;
            }
            case R.id.rela_layout_sex:
            {
                String sex = sexTextView.getText().toString();
                sexDialog(sex);
                break;
            }
            case R.id.signature_rela_layout:
            {
                String signature = signatureTextView.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString(
                        EnumExtraParam.CONTENT.literal(),
                        signature);
                bdSignature.putString(
                        EnumExtraParam.TITLE.literal(),
                        this.getString(R.string.signature_literal));
                // FIXME, 直接传ordinal;
                bdSignature.putInt(
                        EnumExtraParam.FLAG.literal(),
                        EnumUserInfo.SIGNATURE.flag());
                enterForActivityResult(ChangeUserInfoActivity.class, CHANGE_SIGNATURE, bdSignature);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    private void sexDialog(String sex)
    {
        final String[] items = new String[EnumUserSex.count()];
        for (int i = 0; i < EnumUserSex.count(); i++)
        {
            items[i] = EnumUserSex.valueOf(i).text();
        }
        int sexFlag = EnumUserSex.selectByText(sex).ordinal();

        AlertDialog.Builder dialog = new AlertDialog.Builder(
                UserInfoActivity.this);
        dialog.setTitle(R.string.sex_literal);
        dialog.setSingleChoiceItems(items, sexFlag,
                (DialogInterface dialog1, int which) ->
                {
                    dialog1.dismiss();
                    ToastUtil.show(UserInfoActivity.this,
                            items[which]);
                    // 修改数据库
                    sexTextView.setText(items[which]);
                    DataBaseHelper
                            .requireInstance(UserInfoActivity.this)
                            .updateUserInfo(
                                    EnumExtraParam.SEX.literal(),
                                    items[which],
                                    spUsername);
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
            {
                if (data != null)
                {
                    newInfo = data.getStringExtra(EnumExtraParam.NICKNAME.literal());
                    if (newInfo.isEmpty())
                    {
                        return;
                    }
                    nicknameTextView.setText(newInfo);
                    DataBaseHelper
                            .requireInstance(UserInfoActivity.this)
                            .updateUserInfo(
                                    EnumExtraParam.NICKNAME.literal(),
                                    newInfo,
                                    spUsername);
                }
                break;
            }
            case CHANGE_SIGNATURE:
            {
                if (data != null)
                {
                    newInfo = data.getStringExtra(EnumExtraParam.SIGNATURE.literal());
                    if (newInfo.isEmpty())
                    {
                        return;
                    }
                    signatureTextView.setText(newInfo);
                    DataBaseHelper
                            .requireInstance(UserInfoActivity.this)
                            .updateUserInfo(
                                    EnumExtraParam.SIGNATURE.literal(),
                                    newInfo,
                                    spUsername);
                }
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
