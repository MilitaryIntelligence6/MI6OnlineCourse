package cn.misection.miscourse.ui.adapter;

import android.widget.ImageView;

import cn.misection.miscourse.constant.EnumExercise;

public interface OnSelectListener
{
    void onSelect(int position,
                  EnumExercise correctChoice,
                  ImageView... imageViewArray);
}