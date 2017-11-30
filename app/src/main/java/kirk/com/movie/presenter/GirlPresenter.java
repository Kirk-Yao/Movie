package kirk.com.movie.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import kirk.com.movie.api.GirlService;
import kirk.com.movie.base.BasePresenter;
import kirk.com.movie.base.contract.GirlContract;
import kirk.com.movie.model.entity.GirlListResponse;
import kirk.com.movie.util.RetrofitHelper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/11/21.
 */

public class GirlPresenter implements GirlContract.Presenter {

    private static final String BASE_URL = "http://gank.io/";

    private GirlContract.GirlView girlView;

    private int page = 1;
    private final int DEFAULT_SIZE = 10;

    @Override
    public void attachView(GirlContract.GirlView view) {
        if (view != null) {
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

    private void getGirl(final boolean isFirst) {
        createRetrofit().create(GirlService.class)
                .getGirlList(DEFAULT_SIZE, page)
                .compose(RetrofitHelper.<GirlListResponse>io_main())
                .subscribe(new Observer<GirlListResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GirlListResponse girlListResponse) {
                        Logger.d(girlListResponse);
                        if (isFirst) {
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

    private Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd hh:mm:ss")
                .create();

        // 日志拦截器，用于打印日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
