package com.mai.pilot_assistent.ui.aircrafts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.base.BaseActivity;
import com.mai.pilot_assistent.ui.login.LoginActivity;

import javax.inject.Inject;
import java.util.List;

public class AircraftsActivity extends BaseActivity implements AircraftsMvpView{

    @Inject
    AircraftsMvpPresenter<AircraftsMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    AircraftsAdapter aircraftsAdapter;

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
}
