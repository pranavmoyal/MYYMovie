package com.example.niraj.mapplication;

import com.example.niraj.mapplication.Trailer.Ret1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterfaceActivity {



    @GET("movie/popular")
    Call<Ret> getPopularMovies(@Query("api_key") String API_KEY);

    @GET("movie/{id}/videos")
    Call<Ret1> getTrailerMovies(@Path("id") int id , @Query("api_key") String YT_KEY);

}

