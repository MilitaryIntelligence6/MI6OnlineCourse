package com.example.miscourse.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.miscourse.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdBannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdBannerFragment extends Fragment {
    private String ab;  // 广告
    private ImageView imageView;  // 图片

    public AdBannerFragment() {
        // Required empty public constructor
    }

    public static AdBannerFragment newInstance(Bundle args) {
        AdBannerFragment fragment = new AdBannerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        // 获取广告图片名称
        ab = arg.getString("ad");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ab != null) {
            if ("banner_1".equals(ab)) {
                imageView.setImageResource(R.drawable.banner_1);
            }else if ("banner_2".equals(ab)){
                imageView.setImageResource(R.drawable.banner_2);
            }else if ("banner_3".equals(ab)){
                imageView.setImageResource(R.drawable.banner_3);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageView != null) {
            imageView.setImageDrawable(null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 创建广告图片控件
        imageView = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        imageView.setLayoutParams(lp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
