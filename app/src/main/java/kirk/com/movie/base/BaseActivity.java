package kirk.com.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import kirk.com.movie.R;


/**
 * Created by admin on 2017/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    protected final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy()");
    }

    @Override
    public void onRefresh() {

    }
}
