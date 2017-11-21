package kirk.com.movie.base;

/**
 * Created by admin on 2017/11/21.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
