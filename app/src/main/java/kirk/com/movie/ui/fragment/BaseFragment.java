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

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;

/**
 * Created by admin on 2017/11/17.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void setAdapter();

    @BindView(R.id.base_fragment_recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_base,container,false);
        ButterKnife.bind(view);
        setAdapter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
