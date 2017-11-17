package kirk.com.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;

/**
 * Created by admin on 2017/11/17.
 */

public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.base_fragment_refreshLayout)
    private SwipeRefreshLayout refreshLayout;

    protected abstract void refresh();

    // 如何要求子类必须实现该方法？
    protected abstract void setAdapter();

    @Override
    public void onRefresh() {
        refresh();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_base,container,false);
        ButterKnife.bind(view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
