package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.virtualclinic.Models.GettingReports;
import com.example.virtualclinic.Models.PatientPrescriptionDetail;
import com.example.virtualclinic.Models.PatientsDetailedPrescripedModel;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.PrescriptionAndAppointmnet;
import com.example.virtualclinic.databinding.ActivityPatientsReportNextBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class PatientsReportNextActivity extends AppCompatActivity {
    ActivityPatientsReportNextBinding binding;
    List<PatientsDetailedPrescripedModel> prescriptionlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientsReportNextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent()!=null)
        {
            prescriptionlist=  new Gson().fromJson(getIntent().getStringExtra("listOfPres"), new TypeToken<ArrayList<PatientsDetailedPrescripedModel>>() {
        }.getType());
        }
        String prescriptionText = "";
        if (prescriptionlist != null) {
            for (PatientsDetailedPrescripedModel prescription : prescriptionlist) {
                prescriptionText += "Medicine: " + prescription.getMyPatientP().getMedicine_name()+ "  " +
                        "Duration: " + prescription.getMyPatientP().getDuration() + "  " +
                        "Timing: " + prescription.getMyPatientP().getTimings() + "\n";
            }
            binding.textviewPrescription.setText(prescriptionText);
        } else {
            // Handle the case when prescriptions is null
            // You can display an appropriate message or take alternative actions
        }
    }
}