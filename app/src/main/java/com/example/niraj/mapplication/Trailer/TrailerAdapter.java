package com.example.niraj.mapplication.Trailer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.niraj.mapplication.R;

import java.util.List;

public class TrailerAdapter extends ArrayAdapter<String> {

    private static final String TAG = TrailerAdapter.class.getSimpleName();
    private Context context;
    private LayoutInflater inflater;
    private List<Result> results;


    public TrailerAdapter(Context context, List<Result> results1) {
        super(context, 0);

        this.context = context;
        this.results = results1;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") final View view = inflater.inflate(R.layout.movie_detail, parent, false);
        final Result result = results.get(position);

        final String imageUri = "http://img.youtube.com/vi" + result.getId();

       // final ImageView img = (ImageView) view.findViewById(R.id.Img);

        Log.d(TAG, "getView: "+ result.getId()+ "PATH > " + result.getId());


        return view;
    }

    @Override
    public int getCount()
    {
        return results.size();
    }
}
