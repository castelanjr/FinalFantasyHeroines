package com.castelanjr.finalfantasyheroines.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;
import com.castelanjr.finalfantasyheroines.R;
import com.castelanjr.finalfantasyheroines.data.api.model.Heroine;
import com.castelanjr.finalfantasyheroines.util.CircleTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Transformation;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angelocastelanjr on 5/31/15.
 */
public class DetailsActivity extends BaseActivity {

    public static final String HEROINE = "heroine";

    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;

    @Bind(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.text_personality)
    TextView textDescription;

    @Bind(R.id.text_ability)
    TextView textAbility;

    @Bind(R.id.text_game)
    TextView textGame;

    @Bind(R.id.image_heroine)
    ImageView imageCover;

    @Bind(R.id.image_avatar)
    ImageView imageAvatar;

    private Heroine heroine;

    private final Transformation transformation = new CircleTransformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinalFantasyHeroinesApp.get(this).component().inject(this);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

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
        textGame.setText(getString(R.string.final_fantasy, heroine.game()));
        textDescription.setText(heroine.description());
        textAbility.setText(heroine.ability());
        picasso.load(heroine.avatar()).into(imageCover);
        picasso.load(heroine.avatar())
                .transform(transformation)
                .into(imageAvatar, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageAvatar.animate().alpha(1).start();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Transition bug
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
