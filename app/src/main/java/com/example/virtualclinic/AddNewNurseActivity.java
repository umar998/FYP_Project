package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.Nurse;
import com.example.virtualclinic.Models.NurseSignup;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityAddNewNurseBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewNurseActivity extends AppCompatActivity {

    ActivityAddNewNurseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddNewNurseBinding.inflate(getLayoutInflater());
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
                NurseSignup n= new NurseSignup();
                n.full_name=fullname;
                n.email=username;
                n.password=password;
                api.Addnewnurse(n).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(AddNewNurseActivity.this,"Nurse Added", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AddNewNurseActivity.this,AdminLayoutActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(AddNewNurseActivity.this,"Not Added", Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(AddNewNurseActivity.this,AdminLayoutActivity.class);
                startActivity(i);
            }
        });

    }
}