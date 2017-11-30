package kirk.com.movie.presenter;

import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import kirk.com.movie.api.MovieService;
import kirk.com.movie.base.BaseView;
import kirk.com.movie.base.Constants;
import kirk.com.movie.base.MovieType;
import kirk.com.movie.base.contract.CommonContract;
import kirk.com.movie.model.entity.GirlListResponse;
import kirk.com.movie.model.entity.OnShowListResponse;
import kirk.com.movie.ui.adapter.BaseRecyclerViewAdapter;
import kirk.com.movie.util.RetrofitHelper;

/**
 * Created by admin on 2017/11/28.
 */

public class MoviePresenter implements CommonContract.CommonPresenter {

    private final String API_KEY = "0b2bdeda43b5688921839c8ecb20399b";
    private String city = "西安";

    private CommonContract.CommonView view;
    private int type;
    private int page;
    private int count;
    private boolean isFirst = true;

    public MoviePresenter(int type){
        this.type = type;
    }

    @Override
    public void attachView(BaseView view) {
        if (view != null){
            this.view = (CommonContract.CommonView) view;
            page = 0;
            count = 15;
        }
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getData() {
        if (getObservable() == null){
            return;
        }
        getObservable().compose(RetrofitHelper.io_main())
                .subscribe(new Observer<OnShowListResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OnShowListResponse onShowListResponse) {
                        if (onShowListResponse != null){
                            if (isFirst){
                                isFirst = false;
                                view.showData(onShowListResponse.getSubjects());
                            } else {
                                view.showMoreData(onShowListResponse.getSubjects());
                            }
                            page++;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getMoreData() {
        getData();
    }

    private Observable getObservable(){
        MovieService service = RetrofitHelper.getInstance().getRetrofit()
                .create(MovieService.class);
        Observable observable = null;
        switch (type){
            case 0:
                observable = service.getOnShowList(API_KEY,city,page,count);
                break;
            default:
                Logger.e("illegal parameter type:" + type);
                break;
        }
        return observable;
    }
}
