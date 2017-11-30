package kirk.com.movie.util;

import android.graphics.Movie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/11/21.
 */

public class RetrofitHelper {

    private static final String MOVIE_BASE_URL = "https://api.douban.com";

    private static RetrofitHelper instance;

    private static Retrofit retrofit;

    // TODO 可以再封装一个方法，直接传入apiService，在此对返回值进行处理

    private RetrofitHelper(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd hh:mm:ss")
                .create();

        // 日志拦截器，用于打印日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance(){
        if (instance == null){
            synchronized (RetrofitHelper.class){
                if (instance == null){
                    synchronized (RetrofitHelper.class){
                        instance = new RetrofitHelper();
                    }
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public static <T> ObservableTransformer<T,T> io_main() {
        return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource apply(@NonNull Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
