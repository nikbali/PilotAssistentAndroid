package com.mai.pilot_assistent.ui.registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.annotations.SerializedName;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.network.model.RegistrationRequest;
import com.mai.pilot_assistent.ui.base.BaseActivity;
import com.mai.pilot_assistent.ui.login.LoginActivity;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationActivity extends BaseActivity implements RegistrationMvpView {

    @Inject
    RegistrationMvpPresenter<RegistrationMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.editTextDate)
    EditText editTextDate;

    @BindView(R.id.text_name)
    AutoCompleteTextView editTextName;

    @BindView(R.id.text_username)
    AutoCompleteTextView editTextUsername;

    @BindView(R.id.text_email)
    AutoCompleteTextView editTextEmail;

    @BindView(R.id.text_password)
    EditText editTextPassword;

    @BindView(R.id.text_repeat_password)
    EditText editTextRepeatPassword;

    @BindView(R.id.gender)
    RadioGroup radioGroupGender;


    private RegistrationRequest registrationRequest = new RegistrationRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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

    }

    @Override
    public void backToLoginActivity() {
        Intent intent =  new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                backToLoginActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.editTextDate)
    void onEditDateClick() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this,
                (datePicker, year1, month1, day) -> {
                    String myFormat = "dd/MM/yyyy";
                    SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.US);
                    calendar.set(Calendar.YEAR, year1);
                    calendar.set(Calendar.MONTH, month1);
                    calendar.set(Calendar.DAY_OF_MONTH, day);

                    editTextDate.setText(format.format(calendar.getTime()));
                }, year, month, dayOfMonth);


        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }


    @OnClick(R.id.btn_server_registration)
    void onServerRegistrationClick(View v) {
        if(editTextPassword.getText().toString().equals(editTextRepeatPassword.getText().toString())){
            registrationRequest.setName(editTextName.getText().toString());
            registrationRequest.setUsername(editTextUsername.getText().toString());
            registrationRequest.setEmail(editTextEmail.getText().toString());
            registrationRequest.setBirth(editTextDate.getText().toString());
            registrationRequest.setPassword(editTextPassword.getText().toString());

            RadioButton radioButton = (RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId());
            if(radioButton.getText().equals("Мужской")){
                registrationRequest.setGender(1);
            }else{
                registrationRequest.setGender(0);
            }
            mPresenter.onRegistrationClick(registrationRequest);
        }else{
            this.onError(R.string.password_not_same);
        }
    }

}
