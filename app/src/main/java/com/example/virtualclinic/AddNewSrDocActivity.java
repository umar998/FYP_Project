package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.SrDocSignup;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityAddNewSrDocBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewSrDocActivity extends AppCompatActivity {

    ActivityAddNewSrDocBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddNewSrDocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname=binding.edittextFullname.getText().toString();
                String username=binding.edittextUsername.getText().toString();
                String password=binding.edittextPassword.getText().toString();
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();
                SrDocSignup s= new SrDocSignup();
                s.full_name=fullname;
                s.email=username;
                s.password=password;
                api.Addnewsrdoc(s).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(AddNewSrDocActivity.this,"Senior Doctor Added", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AddNewSrDocActivity.this,AdminLayoutActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(AddNewSrDocActivity.this,"Not Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNewSrDocActivity.this,AdminLayoutActivity.class);
                startActivity(i);
            }
        });
    }
}