package com.mai.pilot_assistent.ui.flights;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.ui.base.BaseActivity;
import com.mai.pilot_assistent.utils.CommonUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import javax.inject.Inject;
import java.util.Calendar;

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
        fromDateButton.setOnClickListener(v -> dialogDatePickerLight((Button) v));
        fromTimeButton.setOnClickListener(v -> dialogTimePickerLight((Button) v));
        toDateButton.setOnClickListener(v -> dialogDatePickerLight((Button) v));
        toTimeButton.setOnClickListener(v -> dialogTimePickerLight((Button) v));
    }

    @OnClick(R.id.bt_close)
    void onCloseClick() {
        onBackPressed();
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CreateFlightActivity.class);
    }


    @OnClick(R.id.bt_save)
    @Override
    public void doCreateFlightClick() {

    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    private void dialogDatePickerLight(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    long date_ship_millis = calendar.getTimeInMillis();
                    bt.setText(CommonUtils.getFormattedDateEvent(date_ship_millis));
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(getFragmentManager(), "Datepickerdialog");
    }

    private void dialogTimePickerLight(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance((view, hourOfDay, minute, second) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.AM_PM, calendar.get(Calendar.AM_PM));
            long time_millis = calendar.getTimeInMillis();
            bt.setText(CommonUtils.getFormattedTimeEvent(time_millis));
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }

}
