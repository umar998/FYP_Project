package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.virtualclinic.databinding.ActivityPatientsReportNextBinding;

public class PatientsReportNextActivity extends AppCompatActivity {
    ActivityPatientsReportNextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientsReportNextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}