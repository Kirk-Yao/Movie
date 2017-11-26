package kirk.com.movie.ui.customview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.orhanobut.logger.Logger;

import kirk.com.movie.ui.adapter.BaseRecyclerViewAdapter;

/**
 * Created by admin on 2017/11/25.
 * 用于RecyclerView设置监听，从而实现OnLoadMoreListener接口
 */

public abstract class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener implements OnLoadMoreListener{

    private int[] lastPositions;
    private int lastVisibleItemPos;
    private int currentScrollState = 0;
    private boolean isLoading = false;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            lastVisibleItemPos = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager){
            lastVisibleItemPos = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if (lastPositions == null){
                lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPos = findMax(lastPositions);
            }
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        // 可见item个数
        int visibleItemCount = layoutManager.getChildCount();
        // 总的item个数
        int totalItemCount = layoutManager.getItemCount();
        if (totalItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItemPos >= visibleItemCount - 1){
            // 滑动到底部,开始加载更多
            if (!isLoading()){
                Logger.d("开始加载更多");
                BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
                adapter.showFooter();
                isLoading = true;
                onStart();
                onLoadMore();
            }
        }
    }

    private int findMax(int[] array){
        if (array.length == 0){
            return 0;
        }
        int max = array[0];
        for (int i = 0; i < array.length; i++){
            if (max < array[i]){
                max = array[i];
            }
        }
        return max;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
