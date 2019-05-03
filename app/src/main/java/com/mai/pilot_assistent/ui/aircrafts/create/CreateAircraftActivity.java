package com.mai.pilot_assistent.ui.aircrafts.create;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.network.model.CreateAircraftRequest;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsActivity;
import com.mai.pilot_assistent.ui.base.BaseActivity;

import javax.inject.Inject;
import java.io.File;

public class CreateAircraftActivity extends BaseActivity implements CreateAircraftMvpView{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PERMISSION_REQUEST_CODE = 1;

    @Inject
    CreateAircraftMvpPresenter<CreateAircraftMvpView> mPresenter;

    @BindView(R.id.aircraft_name)
    EditText nameEditText;

    @BindView(R.id.aircraft_year)
    EditText yearEditText;

    @BindView(R.id.aircraft_height)
    EditText heightEditText;

    @BindView(R.id.aircraft_length)
    EditText lengthEditText;

    @BindView(R.id.aircraft_wingspan)
    EditText wingspanEditText;

    @BindView(R.id.aircraft_cruisingSpeed)
    EditText cruisingSpeedEditText;

    @BindView(R.id.aircraft_maxSpeed)
    EditText maxSpeedEditText;

    @BindView(R.id.aircraft_enginePower)
    EditText enginePowerEditText;

    @BindView(R.id.image_background)
    ImageView imageView;

    Bitmap imageBitmap;
    File imageFolder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_aircraft);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(CreateAircraftActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageFolder = new File(picturePath);
            imageBitmap = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @OnClick(R.id.bt_save)
    @Override
    public void doCreateAircraftClick() {
        if (!nameEditText.getText().toString().isEmpty()){
            CreateAircraftRequest request = new CreateAircraftRequest();
            request.setName(nameEditText.getText().toString());
            request.setYear(yearEditText.getText().toString());
            request.setHeight(heightEditText.getText().toString());
            request.setLength(lengthEditText.getText().toString());
            request.setWingspan(wingspanEditText.getText().toString());
            request.setCruisingSpeed(cruisingSpeedEditText.getText().toString());
            request.setMaxSpeed(maxSpeedEditText.getText().toString());
            request.setEnginePower(enginePowerEditText.getText().toString());
            mPresenter.createAircraft(imageFolder, request);
        }else {
            this.onError("Поле Название обязательно для заполнения!");
        }

    }

    @OnClick(R.id.add_image)
    @Override
    public void addImageClick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.READ_SMS
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void openAircraftsActivity() {
        Intent intent = AircraftsActivity.getIntent(getApplicationContext());
        startActivity(intent);
    }

}
