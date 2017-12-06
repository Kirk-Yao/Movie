package kirk.com.movie.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import kirk.com.movie.R;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.util.ImageLoader;

/**
 * Created by admin on 2017/11/29.
 */

public class DialogPagerAdapter extends PagerAdapter {

    public interface OnImageClickListener{
        void onClick();
    }

    private List<GirlEntity> list;
    private Context context;
    private Activity activity;

    private int[] smallSize;

    private List<View> viewList;
    private OnImageClickListener onImageClickListener;

    public DialogPagerAdapter(Context context,List<GirlEntity> list){
        Logger.d("pager adapter init");
        this.list = list;
        this.context = context;
        viewList = new ArrayList<>();
        View view = new View(context);
        for (int i = 0; i < list.size(); i++){
            viewList.add(view);
        }
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        if (onImageClickListener != null){
            this.onImageClickListener = onImageClickListener;
        }
    }

    public void setSmallSize(int[] size){
        this.smallSize = size;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pager_item,container,false);
        PhotoView photoView = view.findViewById(R.id.pager_photo);
        TextView title = view.findViewById(R.id.dialog_item_title);
        GirlEntity girlEntity = list.get(position);
        ImageLoader.load(context,photoView,girlEntity.getUrl());

        title.setText(girlEntity.getDesc());

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClickListener.onClick();
            }
        });

        viewList.set(position,view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
        // TODO 实现此方法
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
