package com.mai.pilot_assistent.ui.aircrafts.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.ui.base.BaseActivity;

public class CreateAircraftActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_aircraft);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void setUp() {

    }

    @OnClick(R.id.bt_close)
    void onCloseClick() {
        onBackPressed();
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CreateAircraftActivity.class);
    }
}
