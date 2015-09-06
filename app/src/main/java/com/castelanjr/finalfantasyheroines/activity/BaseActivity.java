package com.castelanjr.finalfantasyheroines.activity;

import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * Created by angelocastelanjr on 5/31/15.
 */
public class BaseActivity extends RxAppCompatActivity {
    @Inject
    Picasso picasso;
}
