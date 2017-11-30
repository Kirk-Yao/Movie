package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.model.entity.OnShowListResponse;
import kirk.com.movie.util.ImageLoader;

/**
 * Created by admin on 2017/11/27.
 */

public class MovieListAdapter<T extends BaseRecyclerViewAdapter.Item> extends BaseRecyclerViewAdapter {

    public MovieListAdapter(Context context,List<T> list){
        super(context,list);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new MovieItemHolder(view);
    }

    @Override
    protected void onBindFooterView(View footerView) {

    }

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, Item item,int position) {
        MovieItemHolder movieHolder = (MovieItemHolder) holder;
        if (item == null){
            Logger.e("item is null,return");
        }
        OnShowListResponse.SubjectsBean subjectsBean = (OnShowListResponse.SubjectsBean) item;
        ImageLoader.load(context,movieHolder.poster,subjectsBean.getImages().getMedium());
        movieHolder.nameTV.setText(subjectsBean.getTitle());
    }

    class MovieItemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_movie_poster)
        ImageView poster;
        @BindView(R.id.movie_name_tv)
        TextView nameTV;
        @BindView(R.id.moviei_info_tv)
        TextView infoTV;

        public MovieItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
