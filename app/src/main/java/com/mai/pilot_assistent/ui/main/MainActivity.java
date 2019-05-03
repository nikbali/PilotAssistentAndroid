package com.mai.pilot_assistent.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.User;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsActivity;
import com.mai.pilot_assistent.ui.base.BaseActivity;
import com.mai.pilot_assistent.ui.login.LoginActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.activity_main)
    DrawerLayout drawerLayout;

    private TextView nameTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        initNavigationMenu();
    }

    private void initNavigationMenu() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View headerLayout = navigationView.getHeaderView(0);
        nameTextView = headerLayout.findViewById(R.id.full_name);
        emailTextView = headerLayout.findViewById(R.id.email);
        mPresenter.getCurrentUser();
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.nav_aircrafts:
                    mPresenter.onAircraftOpenClick();
                    return true;
                case R.id.nav_exit:
                    mPresenter.onLogoutClick();
                    return true;
                default:
                    return false;
            }
        });
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


    @Override
    public void setCurrentUser(User currentUser) {
        nameTextView.setText(currentUser.getName());
        emailTextView.setText(currentUser.getEmail());
    }

    @Override
    public void closeNavigationDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(Gravity.START);
        }
    }

    @Override
    public void openAircraftsActivity() {
        startActivity(AircraftsActivity.getIntent(getApplicationContext()));
        finish();
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getIntent(getApplicationContext()));
        finish();
    }
}
