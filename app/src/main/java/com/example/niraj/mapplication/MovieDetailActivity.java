package com.example.niraj.mapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    TextView mTvSOverview;
    TextView mTvSTitle;
    TextView mTvSRel_date;
    TextView mTvSOriginal_title;
    TextView mTvSLanguage;
    ImageView mIvSivBigImage;
    String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        if (getIntent() != null) {
            mImageUrl = getIntent().getStringExtra("imageUri");
        }

        Button b=(Button) findViewById(R.id.txt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieDetailActivity.this, YouTubeVideoActivity.class);
                startActivity(intent);
            }
        });

        final Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar();

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareIt();
            }
        });

        final DetailsActivity detail = getIntent().getParcelableExtra("detail");

        mTvSLanguage = (TextView) findViewById(R.id.Language);

        mTvSOriginal_title = (TextView) findViewById(R.id.original_title);

        mIvSivBigImage = (ImageView) findViewById(R.id.ivBigImage);

        mTvSOverview = (TextView) findViewById(R.id.overview);

        mTvSTitle = (TextView) findViewById(R.id.title);

        mTvSRel_date = (TextView) findViewById(R.id.Rel_date);

        if (detail != null) {
            mTvSOverview.setText(String.format("Overview:%s", detail.mSOverview));
            mTvSTitle.setText(String.format("Title:%s", detail.mSTitle));
            mTvSRel_date.setText(String.format("Rel_date:%s", detail.mSRel_date));
            mTvSOriginal_title.setText(String.format("Original_title:%s", detail.mSOriginal_Title));
            mTvSLanguage.setText(String.format("Language:%s", detail.mSLanguage));

        }

        Picasso.with(this)
                .load(mImageUrl).resize(500,400)
                .into(mIvSivBigImage);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        toolbar.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                assert detail != null;
                toolbar.setTitle(detail.mSTitle);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//               if (scrollRange == -1) {
//                   scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    assert detail != null;
//                   collapsingToolbarLayout.setTitle(detail.mSTitle);
//                   isShow = true;
//                   } else if (isShow) {
//                       collapsingToolbarLayout.setTitle(" ");
//                        isShow = false;
//                   }
//               }
//        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Pranav Singh moyal");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void next(View view) {
    }

}







