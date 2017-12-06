package kirk.com.movie.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kirk.com.movie.R;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.ui.activity.CheckImageActivity;
import kirk.com.movie.util.ImageLoader;

/**
 * Created by admin on 2017/11/20.
 */

public class GirlListAdapter extends StaggeredGridLayoutAdapter {

    private Activity activity;

    public static final String GIRL_LIST = "girlList";
    public static final String POSITION = "position";
    public static final String SMALL_PIC_SIZE = "smallSize";

    public GirlListAdapter(Context context,List<GirlEntity> girls){
        super(context,girls);
    }

    public void setActivity(Activity activity){
        this.activity = activity;
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
    protected void onBindItemView(final RecyclerView.ViewHolder holder, Item item, final int position) {
        final GirlHolder girlHolder = (GirlHolder) holder;
        if (item != null){
            GirlEntity girl = (GirlEntity) item;
            ImageLoader.load(context,girlHolder.meizhiImage,girl.getUrl());
            girlHolder.dateTV.setText(girl.getDesc());

            girlHolder.meizhiImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jumpToChcekActivity(position,girlHolder.meizhiImage);
                }
            });
        }
    }

    private void jumpToChcekActivity(int position,View view){
        Intent intent = new Intent(context, CheckImageActivity.class);
        intent.putParcelableArrayListExtra(GIRL_LIST, (ArrayList<? extends Parcelable>) list);
        intent.putExtra(POSITION,position);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        intent.putExtra(SMALL_PIC_SIZE,new int[]{layoutParams.width,layoutParams.height});

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,view,CheckImageActivity.VIEW_NAME);
        ActivityCompat.startActivity(context,intent,optionsCompat.toBundle());
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
    }
}
