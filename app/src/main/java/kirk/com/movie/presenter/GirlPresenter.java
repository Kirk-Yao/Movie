package kirk.com.movie.presenter;

import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import kirk.com.movie.api.GirlService;
import kirk.com.movie.base.BasePresenter;
import kirk.com.movie.base.contract.GirlContract;
import kirk.com.movie.model.entity.GirlListResponse;
import kirk.com.movie.util.RetrofitHelper;

/**
 * Created by admin on 2017/11/21.
 */

public class GirlPresenter implements GirlContract.Presenter {

    private GirlContract.GirlView girlView;

    private int page = 1;
    private final int DEFAULT_SIZE = 10;

    @Override
    public void attachView(GirlContract.GirlView view) {
        if (view != null){
            girlView = view;
            page = 1;
        }
    }

    @Override
    public void detachView() {
        girlView = null;
    }

    @Override
    public void getGirlData() {
        getGirl(true);
    }

    @Override
    public void getMoreGirlData() {
        getGirl(false);
    }

    private void getGirl(final boolean isFirst){
        RetrofitHelper.getInstance().getRetrofit().create(GirlService.class)
                .getGirlList(DEFAULT_SIZE,page)
                .compose(RetrofitHelper.<GirlListResponse>io_main())
                .subscribe(new Observer<GirlListResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GirlListResponse girlListResponse) {
                        Logger.d(girlListResponse);
                        if (isFirst){
                            girlView.showGirls(girlListResponse.getResults());
                        } else {
                            girlView.showMoreGirls(girlListResponse.getResults());
                        }
                        page++;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        girlView.showErrorMsg(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
