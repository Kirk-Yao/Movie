package kirk.com.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.base.BaseView;
import kirk.com.movie.base.contract.CommonContract;
import kirk.com.movie.presenter.MoviePresenter;
import kirk.com.movie.ui.adapter.BaseRecyclerViewAdapter;
import kirk.com.movie.ui.adapter.MovieListAdapter;
import kirk.com.movie.ui.customview.OnRecyclerViewScrollListener;

/**
 * Created by admin on 2017/11/17.
 */

public class BaseFragment<T extends BaseRecyclerViewAdapter.Item> extends Fragment implements CommonContract.CommonView{

    @BindView(R.id.base_fragment_recyclerView)
    RecyclerView recyclerView;

    private List<T> data = new ArrayList<>();
    private MoviePresenter presenter;
    private BaseRecyclerViewAdapter adapter;

    public BaseFragment(MoviePresenter presenter){
        this.presenter = presenter;
        presenter.attachView(this);
        Logger.d("init");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_base,container,false);
        ButterKnife.bind(this,view);
        Logger.d("onCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d("onViewCreated()");

        initView();
        presenter.getData();
    }

    private void initView(){
        adapter = new MovieListAdapter(getActivity(),data);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnScrollListener(scrollListener);
    }

    private OnRecyclerViewScrollListener scrollListener = new OnRecyclerViewScrollListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onLoadMore() {
            Logger.d("onLoadMore()");
            presenter.getMoreData();
        }

        @Override
        public void onFinish() {

        }
    };

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
    public void showData(List list) {
        Logger.d("get data success,show data()");
        if (list != null){
            data.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showMoreData(List list) {
        if (list != null){
            data.addAll(list);
            adapter.notifyDataSetChanged();
            scrollListener.setLoading(false);
        }
    }
}
