package kirk.com.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.base.BaseActivity;
import kirk.com.movie.base.contract.GirlContract;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.presenter.GirlPresenter;
import kirk.com.movie.ui.adapter.GirlListAdapter;
import kirk.com.movie.ui.customview.OnRecyclerViewScrollListener;

public class MainActivity extends BaseActivity implements GirlContract.GirlView{

    private static final String BASE_URL = "http://gank.io/";

    @BindView(R.id.meizhi_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.meizhi_recyclerView)
    RecyclerView recyclerView;

    private GirlPresenter presenter;
    private List<GirlEntity> girlList;
    private GirlListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    @Override
    protected void initData() {
        presenter = new GirlPresenter();
        presenter.attachView(this);

        refreshLayout.setOnRefreshListener(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        girlList = new ArrayList<>();
        adapter = new GirlListAdapter(this,girlList);
        recyclerView.setAdapter(adapter);
        adapter.setActivity(MainActivity.this);

        recyclerView.addOnScrollListener(scrollListener);

        presenter.getGirlData();
    }

    private OnRecyclerViewScrollListener scrollListener = new OnRecyclerViewScrollListener() {
        @Override
        public void onStart() {
            Logger.d("onStart()");
            presenter.getMoreGirlData();
        }

        @Override
        public void onLoadMore() {
            // TODO 在此需要设置，但是使用mvp似乎设置起来并不方便，仍需改善
        }

        @Override
        public void onFinish() {
            setLoading(false);
        }

    };

    @Override
    protected void refreshData() {
        Logger.d("refreshData()");
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showGirls(List<GirlEntity> list) {
        Logger.d(list.get(0).getUrl());
        girlList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreGirls(List<GirlEntity> list) {
        adapter.hideFooter();
        int oldSize = girlList.size();
        girlList.addAll(list);
        adapter.notifyItemRangeInserted(oldSize,list.size());
        scrollListener.onFinish();
    }
}
