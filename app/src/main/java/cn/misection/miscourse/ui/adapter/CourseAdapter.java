package cn.misection.miscourse.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.misection.miscourse.R;

import cn.misection.miscourse.ui.activity.VideoListActivity;
import cn.misection.miscourse.entity.CourseBean;
import cn.misection.miscourse.ui.adapter.holder.CourseViewHolder;

import java.util.List;

/**
 * @author Administrator
 */
public class CourseAdapter extends BaseAdapter
{
    private Context context;

    private List<List<CourseBean>> courseListList;

    public CourseAdapter(Context context)
    {
        this.context = context;
    }

    /**
     * 设置数据更新页面;
     * @param courseListList
     */
    public void putCourseListList(List<List<CourseBean>> courseListList)
    {
        this.courseListList = courseListList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return courseListList == null ? 0 : courseListList.size();
    }

    @Override
    public List<CourseBean> getItem(int position)
    {
        return courseListList == null ? null : courseListList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final CourseViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new CourseViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.course_list_item, null);
            viewHolder.setLeftImageView((ImageView) convertView.findViewById(R.id.left_img_image_view));
            viewHolder.setRightImageView((ImageView) convertView.findViewById(R.id.right_img_image_view));
            viewHolder.setLeftImgTitleTextView((TextView) convertView.findViewById(R.id.left_img_title_text_view));
            viewHolder.setRightImgTitleTextView((TextView) convertView.findViewById(R.id.right_img_title_text_view));
            viewHolder.setLeftTitleTextView((TextView) convertView.findViewById(R.id.left_title_text_view));
            viewHolder.setRightTitleTextView((TextView) convertView.findViewById(R.id.right_title_text_view));
            convertView.setTag(viewHolder);
        }
        else
        {
            // 复用 convertView
            viewHolder = ((CourseViewHolder) convertView.getTag());
        }

        final List<CourseBean> list = getItem(position);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                final CourseBean bean = list.get(i);
                switch (i)
                {
                    // 设置左边图片与标题信息;
                    case 0:
                    {
                        viewHolder.getLeftImgTitleTextView().setText(bean.getImgTitle());
                        viewHolder.getLeftTitleTextView().setText(bean.getTitle());
                        setLeftImg(bean.getId(), viewHolder.getLeftImageView());
                        viewHolder.getLeftImageView().setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                // 跳转到课程详情界面
                                Intent intent = new Intent(context, VideoListActivity.class);
                                intent.putExtra("id", bean.getId());
                                intent.putExtra("intro", bean.getIntro());
                                context.startActivity(intent);
                            }
                        });
                        break;
                    }
                    // 设置右边图片与标题信息;
                    case 1:
                    {
                        viewHolder.getRightImgTitleTextView().setText(bean.getImgTitle());
                        viewHolder.getRightTitleTextView().setText(bean.getTitle());
                        setRightImg(bean.getId(), viewHolder.getRightImageView());
                        viewHolder.getRightImageView().setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                // 跳转到课程详情界面
                                Intent intent = new Intent(context, VideoListActivity.class);
                                intent.putExtra("id", bean.getId());
                                intent.putExtra("intro", bean.getIntro());
                                context.startActivity(intent);
                            }
                        });
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        }
        return convertView;
    }

    /**
     * 设置右边图片;
     * @param id
     * @param rightImageView
     */
    private void setRightImg(int id, ImageView rightImageView)
    {
        switch (id)
        {
            case 2:
                rightImageView.setImageResource(R.drawable.chapter_2_icon);
                break;
            case 4:
                rightImageView.setImageResource(R.drawable.chapter_4_icon);
                break;
            case 6:
                rightImageView.setImageResource(R.drawable.chapter_6_icon);
                break;
            case 8:
                rightImageView.setImageResource(R.drawable.chapter_8_icon);
                break;
            case 10:
                rightImageView.setImageResource(R.drawable.chapter_10_icon);
                break;
            default:
            {
                break;
            }
        }
    }

    // 设置左边图片
    private void setLeftImg(int id, ImageView leftImageView)
    {
        switch (id)
        {
            case 1:
                leftImageView.setImageResource(R.drawable.chapter_1_icon);
                break;
            case 3:
                leftImageView.setImageResource(R.drawable.chapter_3_icon);
                break;
            case 5:
                leftImageView.setImageResource(R.drawable.chapter_5_icon);
                break;
            case 7:
                leftImageView.setImageResource(R.drawable.chapter_7_icon);
                break;
            case 9:
                leftImageView.setImageResource(R.drawable.chapter_9_icon);
                break;
            default:
            {
                break;
            }
        }
    }
}
