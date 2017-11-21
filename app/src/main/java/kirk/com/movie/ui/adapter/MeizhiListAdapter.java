package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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
import butterknife.OnClick;
import kirk.com.movie.R;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.util.ImageLoader;

/**
 * Created by admin on 2017/11/20.
 */

public class MeizhiListAdapter extends RecyclerView.Adapter<MeizhiListAdapter.MeizhiHolder> {

    private Context context;
    private List<GirlEntity> girls;

    public MeizhiListAdapter(Context context, List<GirlEntity> girls){
        this.context = context;
        this.girls = girls;
    }

    @Override
    public MeizhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meizhi,parent,false);
        MeizhiHolder holder = new MeizhiHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MeizhiHolder holder, int position) {
        if (girls != null && position >= 0 && position < girls.size()){
            GirlEntity girl = girls.get(position);
            ImageLoader.load(context,holder.meizhiImage,girl.getUrl());
            holder.dateTV.setText(girl.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return girls.size();
    }

    class MeizhiHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_meizhi_image)
        ImageView meizhiImage;
        @BindView(R.id.item_meizhi_nameTV)
        TextView dateTV;

        public MeizhiHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.item_meizhi_image)
        public void checkDetail(){
            // TODO 查看大图
        }
    }
}
