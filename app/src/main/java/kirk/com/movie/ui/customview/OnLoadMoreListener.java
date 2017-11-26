package kirk.com.movie.ui.customview;

/**
 * Created by admin on 2017/11/25.
 * 加载更多接口
 */

public interface OnLoadMoreListener<T> {

    void onStart();

    void onLoadMore();

    void onFinish();
}
