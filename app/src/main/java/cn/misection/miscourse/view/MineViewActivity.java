package cn.misection.miscourse.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.misection.miscourse.presenter.MineViewPresenter;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineViewActivity
 * @Description TODO
 * @CreateTime 2021年03月10日 13:26:00
// */
//public class MineViewActivity extends AppCompatActivity
//    implements IMineView
//{
//    private MineViewPresenter mineViewPresenter;
//
//    private View view;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        initView();
//    }
//
//    private void initView()
//    {
//        if (mineViewPresenter == null)
//        {
//            mineViewPresenter = MineViewPresenter.requireInstance(this);
//            view = mineViewPresenter.requireViewSingleton();
//        }
//        mineViewPresenter.showView();
//    }
//
//    public View getView()
//    {
//        return view;
//    }
//}
