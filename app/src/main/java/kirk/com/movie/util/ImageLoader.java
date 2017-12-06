package kirk.com.movie.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by admin on 2017/11/20.
 */

public class ImageLoader {

    public static void load(Context context, ImageView imageView,Object object){
        Glide.with(context)
                .load(object)
                .apply(new RequestOptions()
                .centerInside())
                .into(imageView);
    }
}
