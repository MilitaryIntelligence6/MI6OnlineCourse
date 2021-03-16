package cn.misection.miscourse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    private RelativeLayout titleBarRelaLayout;

    private TextView backTextView;

    private TextView mainTitleTextView;

    private TextView saveTextView;

    private EditText contentEditText;

    private ImageView delImageView;

    private String title;

    private String content;

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

        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30b4ff"));
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(title);
        saveTextView = findViewById(R.id.save_text_view);
        saveTextView.setVisibility(View.VISIBLE);
        saveTextView.setOnClickListener(this);
        backTextView = findViewById(R.id.back_text_view);
        backTextView.setOnClickListener(this);

        contentEditText = findViewById(R.id.content_edit_text);
        delImageView = findViewById(R.id.delete_image_view);
        delImageView.setOnClickListener(this);

        if (!content.isEmpty())
        {
            contentEditText.setText(content);
            contentEditText.setSelection(content.length());
        }
        contentListener();
    }

    private void contentListener()
    {
        contentEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Editable editable = contentEditText.getText();
                int len = editable.length();
                delImageView.setVisibility(len > 0 ? View.VISIBLE : View.GONE);
                switch (flag)
                {
                    // 昵称;
                    case 1:
                        if (len > 8)
                        {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String string = editable.toString();

                            String newStr = string.substring(0, 8);
                            contentEditText.setText(newStr);
                            editable = contentEditText.getText();

                            int newLen = editable.length();

                            if (selEndIndex > newLen)
                            {
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    // 签名;
                    case 2:
                        if (len > 16)
                        {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String string = editable.toString();

                            String newStr = string.substring(0, 16);
                            contentEditText.setText(newStr);
                            editable = contentEditText.getText();

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
            case R.id.delete_image_view:
                contentEditText.setText("");
                break;
            case R.id.save_text_view:
                saveUserInfo();
                break;
            default:
            {
                break;
            }
        }
    }

    private void saveUserInfo()
    {
        Intent data = new Intent();
        String value = contentEditText.getText().toString().trim();
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
            default:
            {
                break;
            }
        }
    }
}
