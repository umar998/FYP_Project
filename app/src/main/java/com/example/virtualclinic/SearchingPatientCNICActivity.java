package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.virtualclinic.databinding.ActivitySearchingPatientCnicactivityBinding;

public class SearchingPatientCNICActivity extends AppCompatActivity {
    ActivitySearchingPatientCnicactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchingPatientCnicactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchingPatientCNICActivity.this,
                        Login_Screen_Activity.class);
                startActivity(i);
            }
        });
    }
}