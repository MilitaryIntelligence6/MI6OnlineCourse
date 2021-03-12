package cn.misection.miscourse.mvp.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import cn.misection.miscourse.R;
import cn.misection.miscourse.ui.activity.LoginActivity;
import cn.misection.miscourse.ui.activity.PlayHistoryActivity;
import cn.misection.miscourse.ui.activity.SettingActivity;
import cn.misection.miscourse.ui.activity.UserInfoActivity;
import cn.misection.miscourse.mvp.presenter.IMinePresenter;
import cn.misection.miscourse.mvp.presenter.impl.MinePresenterImpl;
import cn.misection.miscourse.mvp.view.IMineView;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineViewManager
 * @Description TODO
 * @CreateTime 2021年03月10日 14:14:00
 */
public class MineViewManager implements IMineView, View.OnClickListener
{
    private IMinePresenter presenter;

    private View view;

    private Activity context;

    private TextView usernameTextView;

    private RelativeLayout playHistoryRelaLayout;

    private RelativeLayout settingRelaLayout;

    private LinearLayout loginLinearLayout;

    private volatile static MineViewManager instance = null;

    public MineViewManager(MinePresenterImpl presenter)
    {
        this.presenter = presenter;
        init();
    }

    private void init()
    {
        this.context = presenter.context();
        initView();
    }

    private void initView()
    {
        this.view = View.inflate(context, R.layout.main_view_mine, null);
        initComponent();
        this.view.setVisibility(View.VISIBLE);
    }

    private void initComponent()
    {
        usernameTextView = this.view.findViewById(R.id.username_text_view);

        playHistoryRelaLayout = this.view.findViewById(R.id.play_history_rela_layout);
        playHistoryRelaLayout.setOnClickListener(this);

        settingRelaLayout = this.view.findViewById(R.id.setting_rela_layout);
        settingRelaLayout.setOnClickListener(this);

        loginLinearLayout = this.view.findViewById(R.id.login_linear_layout);
        loginLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.login_linear_layout:
            {
                if (presenter.hasLogin())
                {
                    ((Activity) context).startActivityForResult(
                            new Intent(context, UserInfoActivity.class),
                            1
                    );
                }
                else
                {
                    ((Activity) context).startActivityForResult(
                            new Intent(context, LoginActivity.class),
                            1
                    );
                }
                break;
            }
            case R.id.play_history_rela_layout:
            {
                if (presenter.hasLogin())
                {
                    context.startActivity(
                            new Intent(context, PlayHistoryActivity.class)
                    );
                }
                else
                {
                    Toast.makeText(context, "你还未登陆，请先登陆", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.setting_rela_layout:
            {
                if (presenter.hasLogin())
                {
                    ((Activity) context).startActivityForResult(
                            new Intent(context, SettingActivity.class),
                            1
                    );
                }
                else
                {
                    Toast.makeText(context, "你还未登陆，请先登陆", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:
            {
                break;
            }
        }
    }

    public void showLoginState(String msg)
    {
        usernameTextView.setText(msg);
    }

    @Override
    public void show()
    {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public View view()
    {
        return view;
    }
}
