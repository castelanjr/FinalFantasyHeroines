package com.castelanjr.finalfantasyheroines.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;
import com.castelanjr.finalfantasyheroines.R;
import com.castelanjr.finalfantasyheroines.data.api.model.Heroine;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by angelocastelanjr on 5/31/15.
 */
public class DetailsActivity extends BaseActivity {

    public static final String HEROINE = "heroine";

    @InjectView(R.id.appbarlayout)
    AppBarLayout appBarLayout;

    @InjectView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.text_description)
    TextView textDescription;

    @InjectView(R.id.image_heroine)
    ImageView imageAvatar;

    private Heroine heroine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinalFantasyHeroinesApp.get(this).component().inject(this);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout.setTargetElevation(getSupportActionBar().getElevation());

        heroine = getIntent().getParcelableExtra(HEROINE);
        if (heroine == null) {
            throw new IllegalStateException("Heroine is null");
        }

        bindData();
    }

    private void bindData() {
        collapsingToolbarLayout.setTitle(heroine.fullname());
        textDescription.setText(heroine.description());
        picasso.load(heroine.avatar())
                .into(imageAvatar);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
