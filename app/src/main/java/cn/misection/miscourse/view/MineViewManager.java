package cn.misection.miscourse.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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
import cn.misection.miscourse.util.SharedPreferLoginInfo;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineViewManager
 * @Description TODO
 * @CreateTime 2021年03月10日 14:14:00
 */
public class MineViewManager implements View.OnClickListener
{
    private Context context;
    private View view;
    private TextView tvUsername;
    private RelativeLayout rlPlayHistory;
    private RelativeLayout rlSetting;
    private LinearLayout llLogin;
    private SharedPreferLoginInfo spLoginInfo;

    private volatile static MineViewManager instance = null;

    public MineViewManager(Context context)
    {
        initContextAndInflater(context);
    }

    public static MineViewManager requireInstance(Context context)
    {
        if (instance == null)
        {
            synchronized (MineViewManager.class)
            {
                if (instance == null)
                {
                    instance = new MineViewManager(context);
                }
            }
        }
        // 单一职责, 但是代码有点丑;
        if (!instance.context.equals(context))
        {
            instance.initContextAndInflater(context);
        }
        return instance;
    }

    private void initContextAndInflater(Context context)
    {
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
    }

    public View requireViewSingleton()
    {
        initViewInstance();
        return view;
    }

    private void createView()
    {
        initView();
    }

    public void showView()
    {
        initViewInstance();
        view.setVisibility(View.VISIBLE);
    }

    private void initViewInstance()
    {
        if (view == null)
        {
            synchronized (MineViewManager.class)
            {
                if (view == null)
                {
                    createView();
                }
            }
        }
    }

    private void initView()
    {
        view = MineView.requireInstance(context);
        tvUsername = view.findViewById(R.id.tv_username);
        rlPlayHistory = view.findViewById(R.id.rl_play_history);
        rlPlayHistory.setOnClickListener(this);
        rlSetting = view.findViewById(R.id.rl_setting);
        rlSetting.setOnClickListener(this);
        llLogin = view.findViewById(R.id.ll_login);
        llLogin.setOnClickListener(this);
        view.setVisibility(View.VISIBLE);

        spLoginInfo = new SharedPreferLoginInfo(context);
        setLoginParams(spLoginInfo.hasLogin());
    }

    public void setLoginParams(boolean isLogin)
    {
        tvUsername.setText(isLogin ? spLoginInfo.getLoginUsername() : "点击登陆");
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.ll_login:
                if (spLoginInfo.hasLogin())
                {
                    intent = new Intent(context, UserInfoActivity.class);
                }
                else
                {
                    intent = new Intent(context, LoginActivity.class);
                }
                ((Activity) context).startActivityForResult(intent, 1);
                break;
            case R.id.rl_play_history:
                if (spLoginInfo.hasLogin())
                {
                    intent = new Intent(context, PlayHistoryActivity.class);
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "你还未登陆，请先登陆", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (spLoginInfo.hasLogin())
                {
                    intent = new Intent(context, SettingActivity.class);
                    ((Activity) context).startActivityForResult(intent, 1);
                }
                else
                {
                    Toast.makeText(context, "你还未登陆，请先登陆", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
