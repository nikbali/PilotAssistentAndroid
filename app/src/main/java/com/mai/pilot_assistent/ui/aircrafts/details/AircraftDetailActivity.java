package com.mai.pilot_assistent.ui.aircrafts.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsActivity;
import com.mai.pilot_assistent.ui.base.BaseActivity;

public class AircraftDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aircraft);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();

    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            String name = getIntent().getStringExtra("name");
            mToolbar.setTitle(name);
            mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        }

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, AircraftDetailActivity.class);
    }

}
