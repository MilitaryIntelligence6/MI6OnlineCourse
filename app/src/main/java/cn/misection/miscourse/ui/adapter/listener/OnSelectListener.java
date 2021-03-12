package cn.misection.miscourse.ui.adapter.listener;

import android.widget.ImageView;

import cn.misection.miscourse.constant.EnumExercise;

/**
 * @author Administrator
 */
public interface OnSelectListener
{
    /**
     * 被选中时候;
     * @param position 位置;
     * @param userSelected 用户选中;
     * @param imageViewArray imageView;
     */
    void onSelect(int position,
                  EnumExercise userSelected,
                  ImageView... imageViewArray);
}