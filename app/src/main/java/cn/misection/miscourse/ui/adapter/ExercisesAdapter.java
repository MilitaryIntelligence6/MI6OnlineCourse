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

import java.util.List;

public class ExercisesAdapter extends BaseAdapter {
    private Context context;
    private List<ExerciseBean> eb1;

    public ExercisesAdapter(Context context) {
        this.context = context;
    }

    // 设置数据更新界面
    public void setData(List<ExerciseBean> eb1) {
        this.eb1 = eb1;
        notifyDataSetChanged();
    }

    // 获取 Item 的总数
    @Override
    public int getCount() {
        return eb1 == null ? 0 : eb1.size();
    }

    // 根据 position 的到对应 Item 的对象
    @Override
    public ExerciseBean getItem(int position) {
        return eb1 == null ? null : eb1.get(position);
    }

    // 根据 position 得到对应 Item 的 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 得到相应 position 对应的 Item 视图，position 是当前 Item 的位置，
    // convertView 参数就是滑出屏幕的 Item 的 View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        // 复用 convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_list_item, null);
            vh.title = convertView.findViewById(R.id.tv_title);
            vh.content = convertView.findViewById(R.id.tv_content);
            vh.order = convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        } else {
            vh = ((ViewHolder) convertView.getTag());
        }
        // 获取 position 对应的 Item 的数据对象
        final ExerciseBean bean = getItem(position);
        if (bean != null) {
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.getTitle());
            vh.content.setText(bean.getContent());
            vh.order.setBackgroundResource(bean.getBackground());
        }
        // 每个 Item 的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) {
                    return;
                }
                // 跳转到习题详情页面
                Intent intent = new Intent(context, ExercisesDetailActivity.class);
                // 把章节 ID 传递到习题详情页面
                intent.putExtra("id", bean.getId());
                // 把标题传递到习题详情页面
                intent.putExtra("title", bean.getTitle());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        public TextView title, content, order;
    }
}
