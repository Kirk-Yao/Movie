package kirk.com.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.base.BaseActivity;

/**
 * Created by admin on 2017/11/20.
 */

public class MeizhiListActivity extends BaseActivity {

    @BindView(R.id.meizhi_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.meizhi_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_list);
        ButterKnife.bind(this);

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void refreshData() {
        Logger.d("refreshData()");
    }
}
