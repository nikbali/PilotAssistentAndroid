package com.mai.pilot_assistent.ui.aircrafts.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.aircrafts.create.CreateAircraftActivity;
import com.mai.pilot_assistent.ui.aircrafts.details.AircraftDetailActivity;
import com.mai.pilot_assistent.ui.base.BaseActivity;
import com.mai.pilot_assistent.ui.main.MainActivity;

import javax.inject.Inject;
import java.util.List;

public class AircraftsActivity extends BaseActivity implements AircraftsMvpView{

    @Inject
    AircraftsMvpPresenter<AircraftsMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private AircraftsAdapter aircraftsAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_layout_recycler_view)
    SwipeRefreshLayout swipeRefreshLayout;

    public static Intent getIntent(Context context) {
        return new Intent(context, AircraftsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aircraft);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        aircraftsAdapter = new AircraftsAdapter((aircraft) -> {
            Intent intent = AircraftDetailActivity.getIntent(getApplicationContext());
            intent.putExtra("aircraft", aircraft);
            startActivity(intent);
        });
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(aircraftsAdapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.refreshAircrafts();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void refreshAircraftList(List<Aircraft> aircrafts) {
        aircraftsAdapter.setData(aircrafts);
    }

    @Override
    public void backToMainActivity() {
        Intent intent = MainActivity.getStartIntent(getApplicationContext());
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                backToMainActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.add_button)
    void addAircraftClick() {
        startActivity(CreateAircraftActivity.getIntent(getApplicationContext()));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
