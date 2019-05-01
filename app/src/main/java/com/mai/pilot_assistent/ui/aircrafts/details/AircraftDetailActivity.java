package com.mai.pilot_assistent.ui.aircrafts.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.base.BaseActivity;

public class AircraftDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.image_aircraft)
    ImageView imageView;

    @BindView(R.id.aircraft_name)
    TextView aircraftNameTextView;

    @BindView(R.id.aircraft_year_textview)
    TextView aircraftYearTextView;

    @BindView(R.id.aircraft_length)
    TextView aircraftLengthTextView;

    @BindView(R.id.aircraft_height)
    TextView aircraftHeightTextView;

    @BindView(R.id.aircraft_wingspan)
    TextView aircraftWindspanTextView;

    @BindView(R.id.aircraft_cruisingSpeed)
    TextView aircraftCruisingSpeedTextView;

    @BindView(R.id.aircraft_maxSpeed)
    TextView aircraftMaxSpeedTextView;

    @BindView(R.id.aircraft_enginePower)
    TextView aircraftEnginePowerTextView;


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
        Aircraft aircraft = (Aircraft) getIntent().getSerializableExtra("aircraft");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setTitle(aircraft.getName());
            mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        }
        if(aircraft.getImageUrl() != null){
            Glide.with(getApplicationContext())
                    .load(aircraft.getImageUrl())
                    .error((R.drawable.empty_200))
                    .centerCrop()
                    .into(imageView);
        }
        aircraftNameTextView.setText(aircraft.getName());
        aircraftYearTextView.setText(aircraft.getYear() != null ? aircraft.getYear().toString() : "-") ;
        aircraftHeightTextView.setText(aircraft.getHeight()!= null ? aircraft.getHeight().toString() : "-");
        aircraftLengthTextView.setText(aircraft.getLength()!= null ? aircraft.getLength().toString() : "-");
        aircraftWindspanTextView.setText(aircraft.getWingspan()!= null ? aircraft.getWingspan().toString() : "-");
        aircraftCruisingSpeedTextView.setText(aircraft.getCruisingSpeed()!= null ? aircraft.getCruisingSpeed().toString() : "-");
        aircraftMaxSpeedTextView.setText(aircraft.getMaxSpeed()!= null ? aircraft.getMaxSpeed().toString() : "-");
        aircraftEnginePowerTextView.setText(aircraft.getEnginePower()!= null ? aircraft.getEnginePower().toString() : "-");

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, AircraftDetailActivity.class);
    }

}
