package kirk.com.movie.base.contract;

import java.util.List;

import kirk.com.movie.base.BasePresenter;
import kirk.com.movie.base.BaseView;
import kirk.com.movie.model.entity.GirlEntity;

/**
 * Created by admin on 2017/11/21.
 */

public interface GirlContract {

    interface GirlView extends BaseView{

        void showGirls(List<GirlEntity> list);

        void showMoreGirls(List<GirlEntity> list);
    }

    interface Presenter extends BasePresenter<GirlView>{

        void getGirlData();

        void getMoreGirlData();
    }
}
