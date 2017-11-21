package kirk.com.movie.ui.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kirk.com.movie.R;
import kirk.com.movie.api.MeizhiService;
import kirk.com.movie.base.BaseActivity;
import kirk.com.movie.model.entity.GirlListResponse;
import kirk.com.movie.ui.adapter.MeizhiListAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/11/20.
 */

public class MeizhiListActivity extends BaseActivity {

    private static final String BASE_URL = "http://gank.io/";

    @BindView(R.id.meizhi_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.meizhi_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_list);
        ButterKnife.bind(this);

        refreshLayout.setOnRefreshListener(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
    }

    @Override
    protected void refreshData() {
        Logger.d("refreshData()");
    }

    private void loadData(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MeizhiService service = retrofit.create(MeizhiService.class);
        service.getGirlList(10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlListResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull GirlListResponse girlListResponse) {
                Logger.d(girlListResponse.toString());
                if (girlListResponse != null){
                    recyclerView.setAdapter(new MeizhiListAdapter(MeizhiListActivity.this,
                            girlListResponse.getResults()));
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
