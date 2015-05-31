package com.castelanjr.finalfantasyheroines.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;
import com.castelanjr.finalfantasyheroines.R;
import com.castelanjr.finalfantasyheroines.data.api.FinalFantasyHeroinesService;
import com.castelanjr.finalfantasyheroines.data.api.model.HeroinesResponse;
import com.castelanjr.finalfantasyheroines.ui.heroines.HeroinesRecyclerAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    Picasso picasso;

    @Inject
    FinalFantasyHeroinesService service;

    @InjectView(R.id.grid)
    RecyclerView grid;

    private HeroinesRecyclerAdapter adapter;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinalFantasyHeroinesApp.get(this).component().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        adapter = new HeroinesRecyclerAdapter(picasso);
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
            Timber.e(throwable, "Failed to get heroines");
            Snackbar.make(grid, "Failed to get heroines", Snackbar.LENGTH_LONG).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
