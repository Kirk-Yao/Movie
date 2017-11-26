package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kirk.com.movie.R;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.util.ImageLoader;

/**
 * Created by admin on 2017/11/20.
 */

public class GirlListAdapter extends StaggeredGridLayoutAdapter {

    public GirlListAdapter(Context context,List<GirlEntity> girls){
        super(context,girls);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meizhi,parent,false);
        GirlHolder holder = new GirlHolder(view);
        return holder;
    }

    @Override
    protected void onBindFooterView(View footerView) {

    }

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, Item item) {
        GirlHolder girlHolder = (GirlHolder) holder;
        if (item != null){
            GirlEntity girl = (GirlEntity) item;
            ImageLoader.load(context,girlHolder.meizhiImage,girl.getUrl());
            girlHolder.dateTV.setText(girl.getDesc());
        }
    }

    class GirlHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_meizhi_image)
        ImageView meizhiImage;
        @BindView(R.id.item_meizhi_nameTV)
        TextView dateTV;

        public GirlHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.item_meizhi_image)
        public void checkDetail(){
            // TODO 查看大图
        }
    }
}
