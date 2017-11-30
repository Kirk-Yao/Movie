package kirk.com.movie.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

    private ViewGroup viewGroup;

    private ViewPager viewPager;
    private TextView title;

    private Dialog dialog;

    public GirlListAdapter(Context context,List<GirlEntity> girls){
        super(context,girls);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        this.viewGroup = parent;
        View view = LayoutInflater.from(context).inflate(R.layout.item_meizhi,parent,false);
        GirlHolder holder = new GirlHolder(view);
        return holder;
    }

    @Override
    protected void onBindFooterView(View footerView) {

    }

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, Item item, int position) {
        GirlHolder girlHolder = (GirlHolder) holder;
        if (item != null){
            GirlEntity girl = (GirlEntity) item;
            ImageLoader.load(context,girlHolder.meizhiImage,girl.getUrl());
            girlHolder.dateTV.setText(girl.getDesc());

            girlHolder.meizhiImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog == null){
                        dialog = createDialog();
                    }
                    dialog.show();
                }
            });
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
    }

    private Dialog createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createContentView());
//        dialog.setContentView(createContentView());
        return builder.create();
    }

    private View createContentView(){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_check_image,viewGroup,false);
        viewPager = view.findViewById(R.id.dialog_viewPager);
        DialogPagerAdapter adapter = new DialogPagerAdapter(context,list);
        viewPager.setAdapter(adapter);
        return view;
    }
}
