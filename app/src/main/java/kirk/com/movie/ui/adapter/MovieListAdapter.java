package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 2017/11/27.
 */

public class MovieListAdapter<T extends BaseRecyclerViewAdapter.Item> extends BaseRecyclerViewAdapter {

    public MovieListAdapter(Context context,List<T> list){
        super(context,list);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindFooterView(View footerView) {

    }

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, Item item) {

    }
}
