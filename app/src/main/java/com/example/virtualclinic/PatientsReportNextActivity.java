package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.virtualclinic.Models.PatientPrescriptionDetail;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.PrescriptionAndAppointmnet;
import com.example.virtualclinic.databinding.ActivityPatientsReportNextBinding;

import java.util.List;

public class PatientsReportNextActivity extends AppCompatActivity {
    ActivityPatientsReportNextBinding binding;
    List<PatientPrescriptionDetail> prescriptionlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientsReportNextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent()!=null)
        {
            prescriptionlist= (List<PatientPrescriptionDetail>) getIntent().getSerializableExtra("listOfPres");
        }
        String prescriptionText = "";
        if (prescriptionlist != null) {
            for (PatientPrescriptionDetail prescription : prescriptionlist) {
                prescriptionText += "Medicine: " + prescription.getPrescription().getMedicine()+ "  " +
                        "Duration: " + prescription.getPrescription().getDuration() + "  " +
                        "Timing: " + prescription.getPrescription().getTiming() + "\n";
            }
            binding.textviewPrescription.setText(prescriptionText);
        } else {
            // Handle the case when prescriptions is null
            // You can display an appropriate message or take alternative actions
        }
    }
}