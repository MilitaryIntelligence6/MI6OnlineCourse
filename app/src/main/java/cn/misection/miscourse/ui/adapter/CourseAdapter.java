package cn.misection.miscourse.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.misection.miscourse.R;

import cn.misection.miscourse.ui.activity.VideoListActivity;
import cn.misection.miscourse.entity.CourseBean;
import cn.misection.miscourse.constant.ui.adapter.EnumCourseViewEle;
import cn.misection.miscourse.constant.ui.adapter.EnumImageResMapper;
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
            convertView = View.inflate(context, R.layout.course_list_item, null);
            viewHolder = buildViewHolder(convertView);
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
                viewHolder.getImgTitleTextViewArray()[i].setText(bean.getImgTitle());
                viewHolder.getTitleTextViewArray()[i].setText(bean.getTitle());
                putImage(bean.getId(), viewHolder.getImgViewArray()[i]);
                viewHolder.getImgViewArray()[i].setOnClickListener((View v) ->
                {
                    // 跳转到课程详情界面
                    Intent intent = new Intent(context, VideoListActivity.class);
                    intent.putExtra("id", bean.getId());
                    intent.putExtra("intro", bean.getIntro());
                    context.startActivity(intent);
                });
            }
        }
        return convertView;
    }

    /**
     * 设置左边图片;
     * @param id 
     * @param imageView
     */
    private void putImage(int id, ImageView imageView)
    {
        imageView.setImageResource(
                EnumImageResMapper.valueOf(id).getImgRes());
    }

    private CourseViewHolder buildViewHolder(View convertView)
    {
        int size = EnumCourseViewEle.count();
        ImageView[] imageViewArray = new ImageView[size];
        TextView[] titleTextViewArray = new TextView[size];
        TextView[] imgTitleTextViewArray = new TextView[size];

        for (int i = 0; i < size; i++)
        {
            EnumCourseViewEle layoutPos = EnumCourseViewEle.valueOf(i);
            imageViewArray[i] = convertView.findViewById(layoutPos.getImageViewRes());
            titleTextViewArray[i] = convertView.findViewById(layoutPos.getTitleTextViewRes());
            imgTitleTextViewArray[i] = convertView.findViewById(layoutPos.getImgTitleTextViewRes());
        }

        return new CourseViewHolder.Builder()
                .putImgViewArray(imageViewArray)
                .putTitleTextViewArray(titleTextViewArray)
                .putImgTitleTextViewArray(imgTitleTextViewArray)
                .build();
    }
}
