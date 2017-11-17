package kirk.com.movie.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2017/11/17.
 */

public class ToastUtil {

    public static void showShort(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
