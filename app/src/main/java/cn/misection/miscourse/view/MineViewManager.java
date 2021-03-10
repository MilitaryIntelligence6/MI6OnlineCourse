package cn.misection.miscourse.view;

import android.app.Activity;
import android.content.Context;
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
import cn.misection.miscourse.util.SharedPreferLoginInfo;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineViewManager
 * @Description TODO
 * @CreateTime 2021年03月10日 14:14:00
 */
public class MineViewManager extends AbstractView
        implements View.OnClickListener, IView
{
    private Context context;

    private TextView usernameTextView;

    private RelativeLayout playHistoryRelaLayout;

    private RelativeLayout settingRelaLayout;

    private LinearLayout loginLinearLayout;

    private SharedPreferLoginInfo sharePrefLoginInfo;

    private volatile static MineViewManager instance = null;

    public MineViewManager(Context context)
    {
        this.context = context;
        init();
    }

    private void init()
    {
        initView();
    }

    private void initView()
    {
        this.view = View.inflate(context, R.layout.main_view_mine, null);
        initComponent();
        this.view.setVisibility(View.VISIBLE);

        sharePrefLoginInfo = new SharedPreferLoginInfo(context);
        putLoginParams(sharePrefLoginInfo.hasLogin());
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
                if (sharePrefLoginInfo.hasLogin())
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
                if (sharePrefLoginInfo.hasLogin())
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
                if (sharePrefLoginInfo.hasLogin())
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

    public void putLoginParams(boolean isLogin)
    {
        usernameTextView.setText(isLogin ? sharePrefLoginInfo.getLoginUsername() : "点击登陆");
    }
}
