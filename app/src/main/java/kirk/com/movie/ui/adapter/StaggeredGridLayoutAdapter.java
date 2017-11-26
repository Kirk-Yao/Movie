package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 2017/11/25.
 */

public abstract class StaggeredGridLayoutAdapter<T extends BaseRecyclerViewAdapter.Item> extends BaseRecyclerViewAdapter {

    protected StaggeredGridLayoutAdapter() {
    }

    public StaggeredGridLayoutAdapter(Context context,List<T> list) {
        super(context,list);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        // 实现至此
        if (isStaggeredGirdLayout(holder)){
            handleLayout(holder,holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGirdLayout(RecyclerView.ViewHolder holder){
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
            return true;
        }
        return false;
    }

    private void handleLayout(RecyclerView.ViewHolder holder,int position){
        if (isFooter(position)){
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            // 此方法可以将其设置为单独占据整行
            params.setFullSpan(true);
        }
    }


}
