package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.virtualclinic.databinding.ActivityAdminLayoutBinding;

public class AdminLayoutActivity extends AppCompatActivity {
    ActivityAdminLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminLayoutActivity.this,AddNewNurseActivity.class);
                startActivity(i);
            }
        });
        binding.srdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminLayoutActivity.this,AddNewSrDocActivity.class);
                startActivity(i);
            }
        });
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminLayoutActivity.this,Login_Screen_Activity.class);
                startActivity(i);
            }
        });
    }
}