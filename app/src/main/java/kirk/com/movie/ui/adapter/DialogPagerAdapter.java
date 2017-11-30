package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    private List<GirlEntity> list;
    private Context context;

    private List<View> viewList;

    public DialogPagerAdapter(Context context,List<GirlEntity> list){
        Logger.d("pager adapter init");
        this.list = list;
        this.context = context;
        viewList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        Logger.d("list size: " + list.size());
        return list == null ? 0 : list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Logger.d("instantiate item");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pager_item,container,false);
        PhotoView photoView = view.findViewById(R.id.pager_photo);
        TextView title = view.findViewById(R.id.dialog_item_title);
        GirlEntity girlEntity = list.get(position);
        ImageLoader.load(context,photoView,girlEntity.getUrl());
        title.setText(girlEntity.getDesc());

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d("photoView onClick()");
            }
        });
        viewList.add(position,view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        // TODO 实现此方法
        container.removeView(viewList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
