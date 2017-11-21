package kirk.com.movie.presenter;

import kirk.com.movie.base.BasePresenter;
import kirk.com.movie.base.contract.GirlContract;

/**
 * Created by admin on 2017/11/21.
 */

public class GirlPresenter implements GirlContract.Presenter {

    private GirlContract.GirlView girlView;

    @Override
    public void attachView(GirlContract.GirlView view) {
        if (view != null){
            girlView = view;
        }
    }

    @Override
    public void detachView() {
        girlView = null;
    }

    @Override
    public void getGirlData() {

    }

    @Override
    public void getMoreGirlData() {

    }
}
