package kirk.com.movie.base.contract;

import java.util.List;

import kirk.com.movie.base.BasePresenter;
import kirk.com.movie.base.BaseView;
import kirk.com.movie.model.entity.OnShowMovieEntity;
import kirk.com.movie.ui.adapter.BaseRecyclerViewAdapter;

/**
 * Created by admin on 2017/11/28.
 */

public interface CommonContract {

    interface CommonView<T extends BaseRecyclerViewAdapter.Item> extends BaseView{
        void showData(List<T> list);

        void showMoreData(List<T> list);
    }

    interface CommonPresenter extends BasePresenter{
        void getData();

        void getMoreData();
    }
}
