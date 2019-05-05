package com.mai.pilot_assistent.ui.flights;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.db.model.Airport;
import com.mai.pilot_assistent.data.network.model.CreateFlightRequest;
import com.mai.pilot_assistent.ui.base.BaseActivity;
import com.mai.pilot_assistent.ui.main.MainActivity;
import com.mai.pilot_assistent.utils.CommonUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class CreateFlightActivity extends BaseActivity implements CreateFlightMvpView {
    @Inject
    CreateFlightMvpPresenter<CreateFlightMvpView> mPresenter;

    @BindView(R.id.spn_from_date)
    Button fromDateButton;

    @BindView(R.id.spn_from_time)
    Button fromTimeButton;

    @BindView(R.id.spn_to_date)
    Button toDateButton;

    @BindView(R.id.spn_to_time)
    Button toTimeButton;

    @BindView(R.id.flight_number)
    EditText  flightNumberEditText;

    @BindView(R.id.flight_aircraft)
    AppCompatSpinner aircraftsSpinner;

    @BindView(R.id.flight_origin)
    AppCompatSpinner originSpinner;

    @BindView(R.id.flight_destination)
    AppCompatSpinner destinationSpinner;

    private Calendar fromCalender = Calendar.getInstance();
    private Calendar toCalender = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flight);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(CreateFlightActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        fromDateButton.setOnClickListener(v -> dialogDatePickerLight((Button) v, fromCalender));
        fromTimeButton.setOnClickListener(v -> dialogTimePickerLight((Button) v,fromCalender));
        toDateButton.setOnClickListener(v -> dialogDatePickerLight((Button) v,toCalender));
        toTimeButton.setOnClickListener(v -> dialogTimePickerLight((Button) v,toCalender));
        mPresenter.loadAircrafts();
        mPresenter.loadAirports();
    }

    @OnClick(R.id.bt_close)
    void onCloseClick() {
        onBackPressed();
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CreateFlightActivity.class);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.bt_save)
    @Override
    public void doCreateFlightClick() {
        CreateFlightRequest request = new CreateFlightRequest();
        request.setOriginId(mPresenter.loadAirportByName(originSpinner.getSelectedItem().toString()).getIdServer());
        request.setDestinationId(mPresenter.loadAirportByName(destinationSpinner.getSelectedItem().toString()).getIdServer());
        request.setArrivalDateTime(toLocalDateTime(toCalender).toString());
        request.setDepartureDateTime(toLocalDateTime(fromCalender).toString());
        request.setAircraftId(
                mPresenter.loadAircraftByRegNumber(
                        parseRegNumber(
                                aircraftsSpinner.getSelectedItem().toString()
                        )
                ).getIdServer());
        request.setFlightNumber(flightNumberEditText.getText().toString());
        mPresenter.createFlight(request);
    }

    @Override
    public void initSpinnerAircrafts(List<Aircraft> aircrafts) {
        if (aircrafts != null && !aircrafts.isEmpty()) {
            String[] aircraftsArray = aircrafts.stream()
                    .map(aircraft -> String.format("%s(%s)", aircraft.getName(), aircraft.getRegistrationName()))
                    .toArray(String[]::new);

            ArrayAdapter<String> array = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_spinner_item, aircraftsArray);
            array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            aircraftsSpinner.setAdapter(array);
            aircraftsSpinner.setSelection(0);
        }
    }

    @Override
    public void initSpinnerAirports(List<Airport> airports) {
        if (airports != null && !airports.isEmpty()) {
            String[] aircraftsArray = airports.stream()
                    .map(Airport::getNameAirport)
                    .toArray(String[]::new);

            ArrayAdapter<String> array = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_spinner_item, aircraftsArray);
            array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            originSpinner.setAdapter(array);
            originSpinner.setSelection(0);
            destinationSpinner.setAdapter(array);
            destinationSpinner.setSelection(0);
        }
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    private void dialogDatePickerLight(final Button bt, Calendar calendar) {
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    long date_ship_millis = calendar.getTimeInMillis();
                    bt.setText(CommonUtils.getFormattedDateEvent(date_ship_millis));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(calendar);
        datePicker.show(getFragmentManager(), "Datepickerdialog");
    }

    private void dialogTimePickerLight(final Button bt, Calendar cal) {
        TimePickerDialog datePicker = TimePickerDialog.newInstance((view, hourOfDay, minute, second) -> {
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.AM_PM, cal.get(Calendar.AM_PM));
            long time_millis = cal.getTimeInMillis();
            bt.setText(CommonUtils.getFormattedTimeEvent(time_millis));
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }

    private String parseRegNumber(String inString){
        return inString.substring(inString.lastIndexOf('(') + 1, inString.length()-1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zid);
    }
}
