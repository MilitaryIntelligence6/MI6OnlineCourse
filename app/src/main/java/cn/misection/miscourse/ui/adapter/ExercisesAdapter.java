package cn.misection.miscourse.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.ui.activity.ExercisesDetailActivity;
import cn.misection.miscourse.entity.ExerciseBean;
import cn.misection.miscourse.ui.adapter.holder.ExerciseViewHolder;

import java.util.List;

public class ExercisesAdapter extends BaseAdapter
{
    private Context context;
    private List<ExerciseBean> eb1;

    public ExercisesAdapter(Context context)
    {
        this.context = context;
    }

    /**
     * 设置数据更新界面;
     *
     * @param eb1
     */
    public void setData(List<ExerciseBean> eb1)
    {
        this.eb1 = eb1;
        notifyDataSetChanged();
    }

    /**
     * 获取 Item 的总数;
     *
     * @return
     */
    @Override
    public int getCount()
    {
        return eb1 == null ? 0 : eb1.size();
    }

    /**
     * 根据 position 的到对应 Item 的对象;
     *
     * @param position
     * @return
     */
    @Override
    public ExerciseBean getItem(int position)
    {
        return eb1 == null ? null : eb1.get(position);
    }

    /**
     * 根据 position 得到对应 Item 的 id;
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * 得到相应 position 对应的 Item 视图，position 是当前 Item 的位置，
     * convertView 参数就是滑出屏幕的 Item 的 View
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ExerciseViewHolder viewHolder;
        // 复用 convertView
        if (convertView == null)
        {
            viewHolder = new ExerciseViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_list_item, null);
            viewHolder.setTitle((TextView) convertView.findViewById(R.id.tv_title));
            viewHolder.setContent((TextView) convertView.findViewById(R.id.tv_content));
            viewHolder.setOrder((TextView) convertView.findViewById(R.id.tv_order));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((ExerciseViewHolder) convertView.getTag());
        }
        // 获取 position 对应的 Item 的数据对象
        final ExerciseBean exercise = getItem(position);
        if (exercise != null)
        {
            viewHolder.getOrder().setText(String.valueOf(position + 1));
            viewHolder.getTitle().setText(exercise.getTitle());
            viewHolder.getContent().setText(exercise.getContent());
            viewHolder.getOrder().setBackgroundResource(exercise.getBackground());
        }
        // 每个 Item 的点击事件
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (exercise == null)
                {
                    return;
                }
                // 跳转到习题详情页面
                Intent intent = new Intent(context, ExercisesDetailActivity.class);
                // 把章节 ID 传递到习题详情页面
                intent.putExtra("id", exercise.getId());
                // 把标题传递到习题详情页面
                intent.putExtra("title", exercise.getTitle());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
