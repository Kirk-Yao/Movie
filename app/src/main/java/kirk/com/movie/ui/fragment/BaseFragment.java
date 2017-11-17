package kirk.com.movie.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by admin on 2017/11/17.
 */

public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    protected abstract void refresh();
    
    @Override
    public void onRefresh() {
        refresh();
    }
}
