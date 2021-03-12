package cn.misection.miscourse.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.misection.miscourse.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlideBannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author Administrator
 */
public class SlideBannerFragment extends Fragment
{
    /**
     * 广告;
     */
    private String banner;

    /**
     * 图片;
     */
    private ImageView imageView;

    public SlideBannerFragment()
    {
        // Required empty public constructor
    }

    public static SlideBannerFragment newInstance(Bundle args)
    {
        SlideBannerFragment fragment = new SlideBannerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        // 获取广告图片名称
        banner = arg.getString("ad");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (banner != null)
        {
            switch (banner)
            {
                case "banner_1":
                    imageView.setImageResource(R.drawable.banner_1);
                    break;
                case "banner_2":
                    imageView.setImageResource(R.drawable.banner_2);
                    break;
                case "banner_3":
                    imageView.setImageResource(R.drawable.banner_3);
                    break;
                default:
                {
                    break;
                }
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (imageView != null)
        {
            imageView.setImageDrawable(null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // 创建广告图片控件
        imageView = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        imageView.setLayoutParams(lp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
