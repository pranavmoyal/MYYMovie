package com.example.niraj.mapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieAdapterActivity extends ArrayAdapter<String>  {
    TextView tv;

    private static final String TAG = MovieAdapterActivity.class.getSimpleName();
    private Context context;
    private LayoutInflater inflater;
    private List<ResultActivity> results;

    public MovieAdapterActivity(Context context, List<ResultActivity> results) {
        super(context, 0);

        this.context = context;
        this.results = results;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") final View view = inflater.inflate(R.layout.item_grid, parent, false);
        final ResultActivity result = results.get(position);

        final String imageUri = "http://image.tmdb.org/t/p/w185" + result.getPosterPath();

        TextView textView=(TextView) view.findViewById(R.id.title1);
        textView.setText(result.getTitle());

        CardView card=(CardView)view.findViewById(R.id.view);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity detail = new DetailsActivity(result.getOverview(),
                        result.getTitle(),
                        result.getReleaseDate(),
                        result.getOriginalTitle(),
                        result.getOriginalLanguage());
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("detail",detail);
                intent.putExtra("imageUri", imageUri);
                context.startActivity(intent);
            }
        });
        final ImageView img = (ImageView) view.findViewById(R.id.img);

//        img.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                img.setVisibility(View.VISIBLE);
//                DetailsActivity detail = new DetailsActivity(result.getOverview(),
//                        result.getTitle(),
//                       result.getReleaseDate(),
//                        result.getOriginalTitle(),
//                        result.getOriginalLanguage(),
//                        result.getBackdropPath());
//                Intent intent = new Intent(context, MovieDetailActivity.class);
//                intent.putExtra("detail",detail);
//                intent.putExtra("imageUri", imageUri);
//                context.startActivity(intent);
//            }
//        });

        Log.d(TAG, "getView: "+ result.getId()+ "PATH > " + result.getPosterPath());
        Picasso.with(getContext()).load(imageUri).
                fit().into(img, new Callback() {
            @Override
            public void onSuccess() {

            }
            @Override
            public void onError() {

            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return results.size();
    }


}

