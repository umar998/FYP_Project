package com.example.virtualclinic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityNextPageofNurseAddNewPatientBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextPageofNurseAddNewPatientActivity extends AppCompatActivity {
    ActivityNextPageofNurseAddNewPatientBinding binding;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    final int Gallery_REQUEST_CODE=1001;
    Uri imageuri;
    private ImageView imageView;
    Bitmap photo;
    //rightclick kr k generate me ja k overridemethod click krna h phr ye  onActivityResult likhna h
    //ye pic get kr k show kry ga
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==CAMERA_REQUEST_CODE)
//        {
//            if(resultCode==RESULT_OK)
//            {
//                Bundle bundleobj= data.getExtras();
//                Bitmap bmpimg=(Bitmap) bundleobj.get("data");
//                binding.imagesss.setImageBitmap(bmpimg);
//                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
//                bmpimg.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//                byte[] imgArr=outputStream.toByteArray();
//            }
//        }

    }
    private byte[] convertImageToByteArray(Uri uri) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }
    private void sendImageDataToApi(byte[] imageData) {

//       mf
        // Create a RequestBody from the image data
        RequestBody requestBody = RequestBody.create(MediaType.parse("imageDir/jpg"), imageData);

        // Create a MultipartBody.Part from the RequestBody
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageDir", "imageDir.jpg", requestBody);

        // Send the image data to the API using Retrofit or any other HTTP library
        // and handle the response accordingly
    }
    private boolean shouldSaveImage() {
        // add your condition here to determine whether the image should be saved or not
        // for example, you can check if a flag is set, or if some data is present, etc.
        boolean saveImage = false;
        // set saveImage to true or false based on your condition
        return saveImage;
    }
   private String saveImage()
   {
       if(photo==null){
           return "";
       }
           ContextWrapper cw = new ContextWrapper(getApplicationContext());
           File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
           String fname = sdf.format(new Date());
           File file = new File(directory, fname + ".jpg");
           if (file.exists())
               file.delete();
           imageuri = Uri.parse(file.toString());
           Log.d("path", file.toString());
           FileOutputStream fos = null;
           try {
               fos = new FileOutputStream(file);
               photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
               fos.flush();
               fos.close();
           } catch (java.io.IOException e) {
               e.printStackTrace();
           }
           return file.toString();
   }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding= ActivityNextPageofNurseAddNewPatientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.imageView = (ImageView) binding.imagesss;

        binding.save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                saveImage();
               // RequestBody bp= RequestBody.create(MediaType.parse(binding.EdittextBP.getText().toString()));
                String systolic = binding.EdittextSystolic.getText().toString();
                String diastolic=binding.EdittextDiastolic.getText().toString();
                String bpResult=systolic+"  "+diastolic;
                String sugar = binding.EdittextSugar.getText().toString();
                String temp = binding.EdittextTemperature.getText().toString();
                String Symptoms=" ";
                if(binding.CheckBoxCough.isChecked())
                    Symptoms=Symptoms+"Cough, ";
                if(binding.CheckBoxBackpain.isChecked())
                    Symptoms=Symptoms+"Backpain, ";
                if(binding.CheckBoxHeadache.isChecked())
                    Symptoms=Symptoms+"Headache, ";
                if(binding.CheckBoxHighpulse.isChecked())
                    Symptoms=Symptoms+"HighPulse, ";
                if(binding.CheckBoxStomachInfection.isChecked())
                    Symptoms=Symptoms+"Stomach Infection,";
                if(binding.CheckBoxInjury.isChecked())
                    Symptoms=Symptoms+"Injury, ";
                String res=Symptoms;
                Intent i = getIntent();
                int Patients_id = i.getIntExtra("staticclass",0);
                int nurseID=i.getIntExtra("nurseID",0);
                RetrofitClient client=RetrofitClient.getInstance();
                Api api=client.getMyApi();
                MultipartBody.Part photo;
                if(imageuri != null) {
                    File file = new File(imageuri.toString());
                    // rest of the code
                    RequestBody requestBody = RequestBody.create(MediaType.parse("photo/*"), file);
                    photo = MultipartBody.Part.createFormData("photo", file.getName(), requestBody);
                }
                else {
                    RequestBody photoData = RequestBody.create(MediaType.parse("text/plain"), "");
                    photo = MultipartBody.Part.createFormData("photo", "", photoData);
                }
                    RequestBody PIdd = RequestBody.create(MediaType.parse("text/plain"), Patients_id + "");
                    RequestBody bbp = RequestBody.create(MediaType.parse("text/plain"), bpResult);
                    RequestBody sugarr = RequestBody.create(MediaType.parse("text/plain"), sugar);
                    RequestBody temperature = RequestBody.create(MediaType.parse("text/plain"), temp);
                    RequestBody symp = RequestBody.create(MediaType.parse("text/plain"), res);
                    api.Addvitals(photo, PIdd, bbp, sugarr, temperature, symp).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response)
                        {
                            if (response.isSuccessful()) {
                                Toast.makeText(NextPageofNurseAddNewPatientActivity.this,
                                        "Patient Added",
                                        Toast.LENGTH_SHORT).show();
//                                Intent i = new Intent(NextPageofNurseAddNewPatientActivity.this, MainBottomTabsActivity.class);
//                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(NextPageofNurseAddNewPatientActivity.this, "Failed" + response.code(), Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(NextPageofNurseAddNewPatientActivity.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                api.Visits(Patients_id,nurseID).enqueue(new Callback<StaticClass>()
                {
                    @Override
                    public void onResponse(Call<StaticClass> call, Response<StaticClass> response)
                    {
                        if(response.isSuccessful()) {
                            Toast.makeText(NextPageofNurseAddNewPatientActivity.this,
                                    "Visits also Added",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<StaticClass> call, Throwable t) {
                        //Toast.makeText(NextPageofNurseAddNewPatientActivity.this,t.toString(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(NextPageofNurseAddNewPatientActivity.this);
                //builder.setTitle("Select one Option");
                String []ChoiceArr={"Cature Using Camera"};
                builder.setItems(ChoiceArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                            {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                            }
                            else if(position==0)
                            {
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            }
//                            else if(position==1)
//                            {
//                                Intent intent = new Intent((Intent.ACTION_PICK),
//                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                startActivityForResult(intent, Gallery_REQUEST_CODE);
//                            }
                        }
//                        else
//                        {
//                            //For Gallery
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivityForResult(intent, Gallery_REQUEST_CODE);
//                        }
                    }
                });
                AlertDialog alert= builder.create();
                alert.show();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}