package kirk.com.movie.api;

import io.reactivex.Observable;
import kirk.com.movie.model.entity.OnShowListResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/11/28.
 */

public interface MovieService {

    // 正在上映
    @GET("/v2/movie/in_theaters")
    Observable<OnShowListResponse> getOnShowList(@Query("apikey") String apikey,
                                                 @Query("city") String city,
                                                 @Query("start") int start,
                                                 @Query("count") int count);
}
