package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;

public class ChangeUserInfoActivity extends AppCompatActivity implements View.OnClickListener
{
    private RelativeLayout rlTitleBar;
    private TextView tvBack, tvMainTitle, tvSave;
    private EditText etContent;
    private ImageView ivDel;
    private String title, content;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        init();
    }

    private void init()
    {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);

        rlTitleBar = findViewById(R.id.title_bar);
        rlTitleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
        tvMainTitle = findViewById(R.id.main_title_text_view);
        tvMainTitle.setText(title);
        tvSave = findViewById(R.id.tv_save);
        tvSave.setVisibility(View.VISIBLE);
        tvSave.setOnClickListener(this);
        tvBack = findViewById(R.id.back_text_view);
        tvBack.setOnClickListener(this);

        etContent = findViewById(R.id.et_content);
        ivDel = findViewById(R.id.iv_delete);
        ivDel.setOnClickListener(this);

        if (!content.isEmpty())
        {
            etContent.setText(content);
            etContent.setSelection(content.length());
        }
        contentListener();
    }

    private void contentListener()
    {
        etContent.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Editable editable = etContent.getText();
                int len = editable.length();
                ivDel.setVisibility(len > 0 ? View.VISIBLE : View.GONE);
                switch (flag)
                {
                    case 1:  // 昵称
                        if (len > 8)
                        {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String string = editable.toString();

                            String newStr = string.substring(0, 8);
                            etContent.setText(newStr);
                            editable = etContent.getText();

                            int newLen = editable.length();

                            if (selEndIndex > newLen)
                            {
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    case 2:  // 签名
                        if (len > 16)
                        {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String string = editable.toString();

                            String newStr = string.substring(0, 16);
                            etContent.setText(newStr);
                            editable = etContent.getText();

                            int newLen = editable.length();

                            if (selEndIndex > newLen)
                            {
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    default:
                    {
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back_text_view:
                ChangeUserInfoActivity.this.finish();
                break;
            case R.id.iv_delete:
                etContent.setText("");
                break;
            case R.id.tv_save:
                saveUserInfo();
                break;
        }
    }

    private void saveUserInfo()
    {
        Intent data = new Intent();
        String value = etContent.getText().toString().trim();
        switch (flag)
        {
            case 1:
                if (!value.isEmpty())
                {
                    data.putExtra("nickname", value);
                    setResult(RESULT_OK, data);
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    ChangeUserInfoActivity.this.finish();
                }
                else
                {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (!value.isEmpty())
                {
                    data.putExtra("signature", value);
                    setResult(RESULT_OK, data);
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    ChangeUserInfoActivity.this.finish();
                }
                else
                {
                    Toast.makeText(this, "签名不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
