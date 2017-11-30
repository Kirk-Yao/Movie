package kirk.com.movie.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.base.BaseActivity;
import kirk.com.movie.base.Constants;
import kirk.com.movie.base.MovieType;
import kirk.com.movie.presenter.MoviePresenter;
import kirk.com.movie.ui.fragment.BaseFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_swipeRefresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private MyPagerAdapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    private int curPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MeizhiListActivity.class);
                startActivity(intent);
            }
        });

        initData();
    }

    @Override
    protected void refreshData() {
        Logger.d("refreshing data");
    }

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        if (pagerAdapter == null){
            pagerAdapter = new MyPagerAdapter(manager);
            viewPager.setAdapter(pagerAdapter);
        }

        for (int i = 0; i < 1; i++){
            MoviePresenter presenter = new MoviePresenter(i);
            fragmentList.add(new BaseFragment<>(presenter));
        }
    }

    private BaseFragment getCurFragment(){
        return (BaseFragment) fragmentList.get(curPos);
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            Logger.d("init pager");
        }

        @Override
        public Fragment getItem(int position) {
            Logger.d("getItem():" + position);
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
//            return Constants.CATEGORYS.length;
            return 1;
        }
    }

}
