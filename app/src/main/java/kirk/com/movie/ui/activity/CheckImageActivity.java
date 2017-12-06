package kirk.com.movie.ui.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.widget.Button;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirk.com.movie.R;
import kirk.com.movie.base.BaseActivity;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.ui.adapter.DialogPagerAdapter;
import kirk.com.movie.ui.adapter.GirlListAdapter;

/**
 * Created by admin on 2017/11/30.
 */

public class CheckImageActivity extends AppCompatActivity {

    @BindView(R.id.activity_check_image_pager)
    ViewPager viewPager;

    public static final String VIEW_NAME = "activity_check_image_pager";

    private ArrayList<GirlEntity> list;
    private int position;
    private DialogPagerAdapter pagerAdapter;
    private int[] smallPicSize;

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_image);

        ButterKnife.bind(this);

        ViewCompat.setTransitionName(viewPager,VIEW_NAME);
        initData();
    }

    private void initData(){
        list = getIntent().getParcelableArrayListExtra(GirlListAdapter.GIRL_LIST);
        position = getIntent().getIntExtra(GirlListAdapter.POSITION,0);
        smallPicSize = getIntent().getIntArrayExtra(GirlListAdapter.SMALL_PIC_SIZE);

        pagerAdapter = new DialogPagerAdapter(this,list);
        pagerAdapter.setActivity(CheckImageActivity.this);
        pagerAdapter.setSmallSize(smallPicSize);
        pagerAdapter.setOnImageClickListener(onImageClickListener);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
    }

    private DialogPagerAdapter.OnImageClickListener onImageClickListener = new DialogPagerAdapter.OnImageClickListener() {
        @Override
        public void onClick() {
            finish();
        }
    };
}

