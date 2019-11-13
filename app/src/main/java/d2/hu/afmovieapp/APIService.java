package d2.hu.afmovieapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {


//    @GET("76341?api_key=d243e02fe7382c503c90edea8d0dbc21")
//    Call<List<Movie>> getMyJSON();

    @GET("76341?api_key=d243e02fe7382c503c90edea8d0dbc21")
    Call<Movie> getMyJSON_MOVIE();

}
