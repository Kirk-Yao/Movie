package kirk.com.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.ui.adapter.BaseRecyclerViewAdapter;
import kirk.com.movie.ui.adapter.MovieListAdapter;
import kirk.com.movie.ui.customview.OnRecyclerViewScrollListener;

/**
 * Created by admin on 2017/11/17.
 */

public class BaseFragment<T extends BaseRecyclerViewAdapter.Item> extends Fragment {

    @BindView(R.id.base_fragment_recyclerView)
    RecyclerView recyclerView;

    private List<T> data;
    private BaseRecyclerViewAdapter adapter;

    public BaseFragment(List<T> list){
        this.data = list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_base,container,false);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView(){
        adapter = new MovieListAdapter(getActivity(),data);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new OnRecyclerViewScrollListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoadMore() {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void initData(){

    }

}
