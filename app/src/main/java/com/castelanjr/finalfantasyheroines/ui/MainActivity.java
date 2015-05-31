package com.castelanjr.finalfantasyheroines.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;
import com.castelanjr.finalfantasyheroines.R;
import com.castelanjr.finalfantasyheroines.data.api.FinalFantasyHeroinesService;
import com.castelanjr.finalfantasyheroines.data.api.model.Heroine;
import com.castelanjr.finalfantasyheroines.data.api.model.HeroinesResponse;
import com.castelanjr.finalfantasyheroines.ui.heroines.HeroinesRecyclerAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {

    @Inject
    FinalFantasyHeroinesService service;

    @InjectView(R.id.grid)
    RecyclerView grid;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinalFantasyHeroinesApp.get(this).component().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        HeroinesRecyclerAdapter adapter = new HeroinesRecyclerAdapter(picasso, heroineSelectedListener);
        grid.setLayoutManager(new GridLayoutManager(this, 2));
        grid.setAdapter(adapter);

        subscriptions.add(service.getHeroines()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(actionError)
                .onErrorResumeNext(Observable.<HeroinesResponse>empty())
                .subscribe(adapter));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }

    private final Action1<Throwable> actionError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            Snackbar.make(grid, "Failed to get heroines", Snackbar.LENGTH_LONG).show();
        }
    };

    private final HeroinesRecyclerAdapter.OnHeroineSelectedListener heroineSelectedListener
            = new HeroinesRecyclerAdapter.OnHeroineSelectedListener() {
        @Override
        public void onHeroineSelected(Heroine heroine, ImageView avatar) {

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class)
                    .putExtra(DetailsActivity.HEROINE, heroine);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivity.this, avatar, "avatar");
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    };
}
