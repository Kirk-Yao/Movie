package kirk.com.movie.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by admin on 2017/11/20.
 */

public class ImageLoader {

    public static void load(Context context, ImageView imageView,Object object){
        Glide.with(context)
                .load(object)
                .into(imageView);
    }
}
