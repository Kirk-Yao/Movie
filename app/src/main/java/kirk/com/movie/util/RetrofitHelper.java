package kirk.com.movie.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/11/21.
 */

public class RetrofitHelper {
    private static final String BASE_URL = "http://gank.io/";

    private static RetrofitHelper instance;

    private static Retrofit retrofit;

    private RetrofitHelper(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd hh:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
}
