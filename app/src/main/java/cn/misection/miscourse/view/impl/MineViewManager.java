package cn.misection.miscourse.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import cn.misection.miscourse.R;
import cn.misection.miscourse.activity.LoginActivity;
import cn.misection.miscourse.activity.PlayHistoryActivity;
import cn.misection.miscourse.activity.SettingActivity;
import cn.misection.miscourse.activity.UserInfoActivity;
import cn.misection.miscourse.presenter.IMinePresenter;
import cn.misection.miscourse.presenter.impl.MinePresenter;
import cn.misection.miscourse.view.IMineView;

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

    public MineViewManager(MinePresenter presenter)
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
        usernameTextView = this.view.findViewById(R.id.tv_username);

        playHistoryRelaLayout = this.view.findViewById(R.id.rl_play_history);
        playHistoryRelaLayout.setOnClickListener(this);

        settingRelaLayout = this.view.findViewById(R.id.rl_setting);
        settingRelaLayout.setOnClickListener(this);

        loginLinearLayout = this.view.findViewById(R.id.ll_login);
        loginLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ll_login:
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
            case R.id.rl_play_history:
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
            case R.id.rl_setting:
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
