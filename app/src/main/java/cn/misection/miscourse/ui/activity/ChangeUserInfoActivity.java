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
import cn.misection.miscourse.constant.global.EnumCommonString;
import cn.misection.miscourse.constant.ui.EnumDefaultValue;
import cn.misection.miscourse.constant.ui.EnumExtraParam;
import cn.misection.miscourse.constant.ui.EnumSaveState;
import cn.misection.miscourse.constant.ui.EnumUserInfo;
import cn.misection.miscourse.util.ToastUtil;

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
        init();
    }

    private void init()
    {
        initContentView();
        initRelaLayout();
        initTextView();
        initEditText();
        startListener();
    }

    private void initContentView()
    {
        this.setContentView(R.layout.activity_change_user_info);

        title = this.getIntent().getStringExtra(EnumExtraParam.TITLE.literal());
        content = this.getIntent().getStringExtra(EnumExtraParam.CONTENT.literal());
        flag = this.getIntent().getIntExtra(EnumExtraParam.FLAG.literal(),
                EnumDefaultValue.INT_EXTRA.value());
    }

    private void initTextView()
    {
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
    }

    private void initRelaLayout()
    {
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30b4ff"));
    }

    private void initEditText()
    {
        if (!content.isEmpty())
        {
            contentEditText.setText(content);
            contentEditText.setSelection(content.length());
        }
    }

    private void startListener()
    {
        startContentListener();
    }

    private void startContentListener()
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
                updateContent();
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
                contentEditText.setText(EnumCommonString.EMPTY.value());
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
        // 获得是签名还是昵称更改;
        EnumUserInfo option = EnumUserInfo.selectEnumByFlag(flag);
        if (value.isEmpty())
        {
            ToastUtil.show(this,
                    String.format(EnumSaveState.CAN_NOT_BE_EMPTY_WARNING.text(),
                            option.chineseText()));
        }
        else
        {
            data.putExtra(option.englishText(), value);
            setResult(RESULT_OK, data);
            // TODO 改成Res;
            ToastUtil.show(this, EnumSaveState.SUCCESSFULLY_SAVE.text());
            ChangeUserInfoActivity.this.finish();
        }
    }

    private void updateContent()
    {
        Editable editable = contentEditText.getText();
        int len = editable.length();
        delImageView.setVisibility(len > 0 ? View.VISIBLE : View.GONE);
        // 获得是签名还是昵称更改;
        EnumUserInfo option = EnumUserInfo.selectEnumByFlag(flag);
        if (len > option.lengthLimit())
        {
            int selEndIndex = Selection.getSelectionEnd(editable);
            String string = editable.toString();
            String newStr = string.substring(0, option.lengthLimit());
            contentEditText.setText(newStr);
            editable = contentEditText.getText();
            int newLen = editable.length();
            if (selEndIndex > newLen)
            {
                selEndIndex = editable.length();
            }
            Selection.setSelection(editable, selEndIndex);
        }
    }
}
