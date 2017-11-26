package kirk.com.movie.api;

import java.util.List;

import io.reactivex.Observable;
import kirk.com.movie.model.entity.GirlEntity;
import kirk.com.movie.model.entity.GirlListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2017/11/21.
 */

public interface GirlService {

    @GET("api/data/福利/{pagesize}/{page}")
    Observable<GirlListResponse> getGirlList(@Path("pagesize") int pagesize,
                                       @Path("page") int page);

}
