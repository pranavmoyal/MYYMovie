package com.example.niraj.mapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.niraj.mapplication.Trailer.Result;
import com.example.niraj.mapplication.Trailer.Ret1;
import com.example.niraj.mapplication.Trailer.TrailerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<ResultActivity> resultList=new ArrayList<>();
    List<Result> resultList1=new ArrayList<>();


    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "534ba520f1a0491c82219461bdd10cc9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Popular Movies");

        final MovieAdapterActivity adapter = new MovieAdapterActivity(this, resultList);
        final TrailerAdapter adapter1 = new TrailerAdapter(this, resultList1);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        if (gridView != null) {
            gridView.setAdapter(adapter);
        }

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org",
                    Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterfaceActivity apiService =
                ApiClientActivity.getClient().create(ApiInterfaceActivity.class);
        retrofit2.Call<Ret> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<Ret>() {
            @Override
            public void onResponse(retrofit2.Call<Ret> call, Response<Ret> response) {
                Ret ret = response.body();
                List<ResultActivity> results = ret.getResults();
                Log.d(TAG, "Number of movies received: " + results.size());
                resultList.clear();
                resultList.addAll(results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<Ret> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });

        String YT_KEY = "534ba520f1a0491c82219461bdd10cc9";
        Call<Ret1> ret1Call = apiService.getTrailerMovies(550, YT_KEY);
        ret1Call.enqueue(new Callback<Ret1>() {
            @Override
            public void onResponse(Call<Ret1> call, Response<Ret1> response) {
                Ret1 ret = response.body();
                List<Result> results = ret.getResults();
                Log.d(TAG, "Number of movies received: " + results.size());
                resultList1.clear();
                resultList1.addAll(results);
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Ret1> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }


}

