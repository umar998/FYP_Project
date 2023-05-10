package com.example.virtualclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.MediaType;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityMain2Binding;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private  Uri imageuri;
    Bitmap photo;
    Button upload,getimagbtn;
    ActivityMain2Binding binding;

    private String saveImage(){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String fname = sdf.format(new Date());
        File file = new File(directory, fname + ".jpg");
        if(file.exists())
            file.delete();


        imageuri=Uri.parse(file.toString());
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
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        this.imageView = (ImageView) binding.imageView1;//(ImageView)this.findViewById(R.id.imageView1);
       // Button photoButton =binding.button1;// (Button) this.findViewById(R.id.button1);
        getimagbtn=binding.btngetImage;//findViewById(R.id.btngetImage);

        binding.btntakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }

            }

            });



//        binding.button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str="";
//            }
//        });

//        binding.button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//                    {
//                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                    }
//                    else
//                    {
//                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                    }
//                }
//
//            }
//        });

//        binding.button1.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//                {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                }
//                else
//                {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }
//            }
//        });
        //upload=binding.btnupload;//findViewById(R.id.btnupload);
        binding.btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();


                File file= new File(imageuri.toString());
                RequestBody photoContent= RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(),photoContent);
                RequestBody PId= RequestBody.create(MediaType.parse("text/plain"),"7");
              RequestBody bp= RequestBody.create(MediaType.parse("text/plain"),"120/110");
                RequestBody sugar= RequestBody.create(MediaType.parse("text/plain"),"100");
              RequestBody temperature= RequestBody.create(MediaType.parse("text/plain"),"95");
                RequestBody symp= RequestBody.create(MediaType.parse("text/plain"),"Itching");

                Api uploadService = RetrofitClient.getInstance().getMyApi();


                uploadService.Upload(photo,PId,bp,sugar,temperature,symp).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful())
                            Toast.makeText(MainActivity2.this,  "Added Successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity2.this, " "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        getimagbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Hardcoded URL.
// Till uploads folder is name path where image
// can be. You can fetch image name from DB and contact it with URL
                String
                        imagepath="http://192.168.8.103/testAPI/Content/Uploads/cs.jpg";
              //  new DownloadImageTask(imageView).execute(imagepath);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");



            imageView.setImageBitmap(photo);
        }
    }

}
//Toast.makeText(NextPageofNurseAddNewPatientActivity.this,t.toString(),Toast.LENGTH_LONG).show();
//if(response.isSuccessful()) {
//        Toast.makeText(NextPageofNurseAddNewPatientActivity.this,
//        "Patient Added",
//        Toast.LENGTH_SHORT).show();
//        }
//        else {
//        Toast.makeText(NextPageofNurseAddNewPatientActivity.this, "Failed", Toast.LENGTH_LONG).show();
//        }
